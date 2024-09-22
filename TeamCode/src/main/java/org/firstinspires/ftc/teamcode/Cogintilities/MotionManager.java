package org.firstinspires.ftc.teamcode.Cogintilities;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.ArrayList;
import java.util.List;

public class MotionManager {

    DcMotorEx motor;

    public double maxTravel, minTravel;


    public MotionManager(Builder builder) {

    }

    /** Builder Class **/
    public static class Builder {


        public Builder(String opMode) {
        }

        public MotionManager.Builder addParam() {
            return this;
        }

        public MotionManager build() {
            return new MotionManager(this);
        }
    }


    public void jog() {

    }

    public void moveToPostion() {

    }

    public void clampSetpoint() {

    }

}
