package org.firstinspires.ftc.teamcode.Cogintilities;

/**
 * Brainstorming a class to manage subsystems that use motors.  The code for these functions is
 * often duplicated in all the subsystems using motors.
 */
public class MotionManager {

    private int minSetpt, maxSetpt, currentPos;
    private double maxTravel, minTravel, minSetpoint, maxSetpoint;

    enum MotionActions { INCR, DCRS }
    enum MotionStates  { IDLE, MOVING, NEG_LIMIT, POS_LIMIT }

    public MotionManager() {

    }


    /**
     * Manually move a motor using a joystick or other input
     */
    public void jog() { }


    /**
     * Use SDK controller to move a motor to a given encoder count
     * @param setPoint
     * @param time
     */
    public void moveToPosition(int setPoint, double time) { }


    /**
     * These functions ensure an input is withing the physical limits of the robot
     * @param setpoint
     * @return
     */
    public double clampSetpoint(double setpoint) { return 0.0; }
    public int clampSetpoint(int setpoint) { return 0; }


    /**
     * These two functions check to ensure that any moving components do exceed and travel limits
     * protecting them from damage.
     * @return
     */
    public boolean minPositionLimitExceeded() { return currentPos < minTravel; }
    public boolean maxPositionLimitExceeded() { return currentPos > maxTravel; }


    /**
     * This should be called once per while(opModeActive) loop in the teleOp.  This is the only
     * way for the subsystem to check that all parameters are within motion limits.  If not, this
     * function must remove power from motors or set them to a state that is within limits.  Perhaps
     * it should also limit which directions of motion are possible until the limit infraction is
     * resolved?
     */
    public void update() {
        int curPos = 0;
        if(minPositionLimitExceeded()) moveToPosition(minSetpt + 10, 3);
    }
}
