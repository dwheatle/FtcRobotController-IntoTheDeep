package org.firstinspires.ftc.teamcode.Cogintilities;

public class MotionManager {

    private int minSetpt, maxSetpt, currentPos;
    private double maxTravel, minTravel, minSetpoint, maxSetpoint;

    enum MotionActions { INCR, DCRS }
    enum MotionStates  { IDLE, MOVING, NEG_LIMIT, POS_LIMIT }

    public MotionManager() {

    }

    public void jog() { }
    public void moveToPosition(int setPoint, double time) { }

    public double clampSetpoint(double setpoint) { return 0.0; }

    public int clampSetpoint(int setpoint) { return 0; }


    public boolean minPositionLimitExceeded() { return currentPos < minTravel; }
    public boolean maxPositionLimitExceeded() { return currentPos > maxTravel; }

    public void update() {
        int curPos = 0;
        if(minPositionLimitExceeded()) moveToPosition(minSetpt + 10, 3);
    }
}
