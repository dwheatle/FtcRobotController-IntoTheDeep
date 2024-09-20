package org.firstinspires.ftc.teamcode.OpModes_TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.Cogintilities.DataLogger;
import org.firstinspires.ftc.teamcode.RobotConfiguration;
import org.firstinspires.ftc.teamcode.TeamConstants;

//@Disabled
@TeleOp(name="DataLogExample", group="Development")
public class TeleOpDataLogger extends RobotConfiguration implements TeamConstants {

    DataLogger log = new DataLogger.Builder("DataLogEx")
            .addParam("LJSTKX", "*")
            .addParam("LJSTKY", "*")
            .addParam("RJSTKX", "*")
            .addParam("RJSTKX", "*")
            .build();

    int SAMPLE_RATE = 10; // Hz
    ElapsedTime acquisitionTime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        //initializeRobot();
        telemetry.addData("What is this? ", this.getClass().toString());
        telemetry.addData("OpMode: ", this.toString());
        telemetry.addData("USB Path: ", AppUtil.getInstance().getUsbFileSystemRoot());
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            if(acquisitionTime.milliseconds() >= (1000.0 / SAMPLE_RATE)) {
                updateDataLog();
            }

        }

        log.closeDataLogger();
    }

    /**
     * Send parameters to the data logger object. Values must be in the same order they are declared
     * in the DataLogger constructor.
     */
    void updateDataLog() {
        acquisitionTime.reset();
        log.addValue(gamepad1.left_stick_x);
        log.addValue(gamepad1.left_stick_y);
        log.addValue(gamepad1.right_stick_x);
        log.addValue(gamepad1.right_stick_y);
        log.acquire();
    }
}
