package org.firstinspires.ftc.teamcode.OpModes_TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotConfiguration;
import org.firstinspires.ftc.teamcode.TeamConstants;

@Disabled
@TeleOp(name="TeleOpMode", group="Competition")
public class TeleOpTemplate extends RobotConfiguration implements TeamConstants {

    @Override
    public void runOpMode() throws InterruptedException {

        initializeRobot();

        telemetry.addData("OpMode: ", this.toString());
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

        }
    }
}
