package org.firstinspires.ftc.teamcode.Cogintilities;


import android.os.Environment;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.RobotConfiguration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DataLogger {

    private final String opMode;
    private String directoryPath; // = "/home/dw/Documents/Java Scratch/Data";

    private Writer fileWriter;
    private List<String> params = new ArrayList<>();
    private List<String> units = new ArrayList<>();
    private StringBuffer lineBuffer = new StringBuffer(128);
    private StringBuffer timeBuffer = new StringBuffer(8);
    private StringBuffer paramBuffer = new StringBuffer(128);

    private Date now;
    private long timeBase;              // time of instantiation (milliseconds)
    private long nsBase;                // time of reset (nanoseconds)


    /**
     * CONSTRUCTOR using the builder pattern framework
     * @param builder
     */
    private DataLogger(Builder builder) {

        lineBuffer.setLength(0);

        /* opMode passed in as this.getClass().toString()) */
        this.opMode = (builder.opMode).substring(((builder.opMode).lastIndexOf(".")+1));

        this.params = builder.params;
        this.units  = builder.units;

        /* Add Default Parameters */
        this.params.add(0,"TIME");
        this.units.add(0,"s");

        this.params.add(1,"dt");
        this.units.add(1, "ms");

        this.params.add("CTRLHUBV");
        this.units.add("V");

        this.params.add("EXPHUBV");
        this.units.add("V");

        now = new Date();

        openDataFile();
        writeHeader();
    }


    /** Builder Class **/
    public static class Builder {

        private List<String> params = new ArrayList<>();
        private List<String> units  = new ArrayList<>();
        private String opMode;

        public Builder(String opMode) {
            this.opMode = opMode;
        }

        public Builder addParam(String mnemonic, String units) {
            this.params.add(mnemonic);
            this.units.add(units);
            return this;
        }

        public DataLogger build() { return new DataLogger(this); }
    }
    /************************** END CONSTRUCTOR **************************/


    /**
     * Write the header information to the data file.
     */
    private void writeHeader(){

        lineBuffer.append("Opmode: ");
        lineBuffer.append(opMode);
        lineBuffer.append("\nAcquisition Started: ");

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyyLLLdd", Locale.US);
        String formattedDate = sdf.format(now);
        lineBuffer.append(formattedDate);
        lineBuffer.append("\n\n");

        for(int i = 0; i < params.size(); i++) {
            lineBuffer.append(params.get(i));

            if(i < params.size() - 1)
                lineBuffer.append(',');
            else lineBuffer.append('\n');
        }

        for(int i = 0; i < units.size(); i++) {
            lineBuffer.append(units.get(i));

            if(i < params.size() - 1)
                lineBuffer.append(',');
        }

        timeBase = System.currentTimeMillis();
        nsBase = System.nanoTime();

        writeToFile();

    }


    /**
     * Tries to open a data file for writing
     */
    private void openDataFile() {
;
        directoryPath =  Environment.getExternalStorageDirectory().getPath() + "/Datalogs";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US);
        String formattedDate = sdf.format(now);

        // File file = AppUtil.getInstance().getSettingsFile(formattedDate + " " + opMode);
        String filePath = directoryPath + "/" + formattedDate + " " + opMode + ".txt";

        new File(directoryPath).mkdir();  // create Datalogs folder if needed

        // Set up the file writer and line buffer.
        try {
            fileWriter = new FileWriter(filePath);
        }
        catch (IOException e) {
//            System.out.println("uh oh... trouble in the openDataFile method");
//            System.out.println(e);
        }

    }


    // These two (overloaded) methods add a text field to the line (row),
    // proceded by a comma.  This creates the comma-separated values (CSV).

    /**
     * Add a value to be written to disk. Multiple Overrides to accommodate various data types.
     * @param s data value
     */
    public void addValue(String s) {
        if (paramBuffer.length()>0) {
            paramBuffer.append(',');
        }
        paramBuffer.append(s);
    }

    public void addValue(char c) {
        if (paramBuffer.length()>0) {
            paramBuffer.append(',');
        }
        paramBuffer.append(c);
    }

    public void addValue(byte b) {
        addValue(Byte.toString(b));
    }
    public void addValue(short s) {
        addValue(Short.toString(s));
    }
    public void addValue(long l) {
        addValue(Long.toString(l));
    }
    public void addValue(float f) {
        addValue(Float.toString(f));
    }
    public void addValue(double d) {
        addValue(Double.toString(d));
    }
    /*************************** END OVERRIDES ***************************/


    /**
     * Must be called to commit the values added using the addValue functions. Builds the output
     * line consisting of the time stamp, followed by the parameter data and lastly the battery
     * voltages of the control hubs.
     */
    public void acquire() {

        /** Build output line to write to the file **/
        /* Insert Time */
        getTimeStamp();
        lineBuffer.append(timeBuffer);
        lineBuffer.append(",");

        /* Append parameter data */
        lineBuffer.append(paramBuffer);

        /* Append control hub battery voltages */
        lineBuffer.append(',');
        lineBuffer.append(RobotConfiguration.ctrlHubV());
        lineBuffer.append(',');
        lineBuffer.append(RobotConfiguration.expHubV());

        writeToFile();
    }


    /**
     * Function to time stamp the data line.
     */
    private void getTimeStamp() {

        long milliTime, nanoTime;

        // Update time for first two columns (cumulative and incremental time).
        milliTime   = System.currentTimeMillis();
        nanoTime    = System.nanoTime();

        timeBuffer.setLength(0);
        timeBuffer.append(String.format(Locale.US,"%.3f", (milliTime - timeBase) / 1000.0));
        timeBuffer.append(',' );
        timeBuffer.append(String.format(Locale.US, "%.1f",(nanoTime - nsBase) / 1.0E6));

        nsBase = nanoTime;         // reset for incremental time delta
    }


    /**
     * Start a new data row. Clears all data buffers.
     */
    private void startNewRow() {
        lineBuffer.setLength(0);                // clear the line (row)
        paramBuffer.setLength(0);
        timeBuffer.setLength(0);
    }


    /**
     * Write data contained in the lineBuffer to the file.  Should be one line of common sererated
     * values.
     */
    private void writeToFile(){

        try {
            lineBuffer.append('\n');                // end-of-line character
            fileWriter.write(lineBuffer.toString());    // add line (row) to file

            startNewRow();

        }
        catch (IOException e) {
//            System.out.println("Uh oh..., trouble in the wirteToFile method");
//            System.out.println(e);
        }

    }


    /**
     * Close the file.  File must be closed in the teleOp in order for the file to be saved.
     */
    public void closeDataLogger() {
        try {
            fileWriter.close();             // close the file
        }
        catch (IOException e) {
        }
    }

}