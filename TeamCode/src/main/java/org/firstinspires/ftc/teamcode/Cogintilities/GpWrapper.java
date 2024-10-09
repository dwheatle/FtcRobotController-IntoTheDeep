package org.firstinspires.ftc.teamcode.Cogintilities;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.List;

public class GpWrapper {

    public DigitalInput a = new DigitalInput(false, false);
    public DigitalInput b = new DigitalInput(false, false);
    public DigitalInput x = new DigitalInput(false, false);
    public DigitalInput y = new DigitalInput(false, false);

    public DigitalInput start = new DigitalInput(false, false);
    public DigitalInput back  = new DigitalInput(false, false);
    public DigitalInput guide = new DigitalInput(false, false);

    public DigitalInput dpadUp    = new DigitalInput(false, false);
    public DigitalInput dpadDown  = new DigitalInput(false, false);
    public DigitalInput dpadLeft  = new DigitalInput(false, false);
    public DigitalInput dpadRight = new DigitalInput(false, false);

    public DigitalInput leftBumper  = new DigitalInput(false, false);
    public DigitalInput rightBumper = new DigitalInput(false, false);

    public double leftStick_X, leftStick_Y, rightStick_X, rightStick_Y, leftTrigger, rightTrigger;

    private Gamepad gamepad;


    public GpWrapper(Gamepad gamepad) { this.gamepad = gamepad; }

    public void update() {

        leftStick_X  = gamepad.left_stick_x;
        leftStick_Y  = gamepad.left_stick_y;
        rightStick_X = gamepad.right_stick_x;
        rightStick_Y = gamepad.right_stick_y;
        leftTrigger  = gamepad.left_trigger;
        rightTrigger = gamepad.right_trigger;

        a.update(gamepad.a);
        b.update(gamepad.b);
        x.update(gamepad.x);
        y.update(gamepad.y);
        dpadUp.update(gamepad.dpad_up);
        dpadDown.update(gamepad.dpad_down);
        dpadLeft.update(gamepad.dpad_left);
        dpadRight.update(gamepad.dpad_right);
        leftBumper.update(gamepad.left_bumper);
        rightBumper.update(gamepad.right_bumper);
        start.update(gamepad.start);
        back.update(gamepad.back);
        guide.update(gamepad.guide);
    }

}
