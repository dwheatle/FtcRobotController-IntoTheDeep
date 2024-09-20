package org.firstinspires.ftc.teamcode.Cogintilities.LogData;

public abstract class GenericParameter {

    private String mnemonic;
    private String units;
    private String curVal;


    public void log(String s) {
        curVal = s;
    }

    public void log(byte b) {
        log(Byte.toString(b));
    }

    public void log(short s) {
        log(Short.toString(s));
    }

    public void log(long l) {
        log(Long.toString(l));
    }

    public void log(float f) {
        log(Float.toString(f));
    }

    public void log(double d) {
        log(Double.toString(d));
    }

    public String value() {
        return curVal;
    }
}
