package org.firstinspires.ftc.teamcode.Cogintilities;

public class DigitalInput {

    private boolean currentState;
    private boolean lastState;
    private boolean controlState, risingEdge, fallingEdge, toggleState;
    private final boolean invertState;


    /**
     * CONSTRUCTOR
     * Create a button object having the initialValue and set the output behavior for
     * normal or inverted.
     *
     * @param initialValue Initial boolean value of the button
     * @param invertOutput - Invert the output value of the button
     **/
    public DigitalInput(boolean initialValue, boolean invertOutput) {
        currentState = initialValue;
        invertState = invertOutput;
        lastState = currentState;
    }


    /**
     * This function will return true when ever the physical button changes states.
     *
     * @param buttonValue Value of the physical button being monitored
     */
    private boolean buttonStateChanged(boolean buttonValue) {

        return currentState ^ lastState;
        /* XOR function, only returns true when the currentState does not equal the lastState.  */
    }


    /**
     * This function needs to be overridden to handle the typo of button action.  It should be
     * called once every time the runOpMode loop executes
     *
     * @param currentValue Value of the button being monitored
     */
    public void update(boolean currentValue) {

        /* Update States */
        lastState = currentState;
        currentState = currentValue;

        /* Rising Edge - One Shot Pulse */
        risingEdge = currentState && !lastState;

        /* Falling Edge - One Shot Pulse */
        fallingEdge = !currentState && lastState;

        if (buttonStateChanged(currentState)) {
            /* Momentary Button */
            controlState = currentValue;

            /* Toggle Button */
            if (currentValue) toggleState = !toggleState;
        }

    }


    /* Return methods for buttons */
    public boolean pressed()      { return risingEdge ^ invertState;   }
    public boolean released()     { return fallingEdge ^ invertState;  }
    public boolean whilePressed() { return controlState ^ invertState; }
    public boolean toggle()       { return toggleState ^ invertState;  }

    /* Return methods for digital Inputs - just changed method verbage */
    public boolean risingEdge()   { return risingEdge ^ invertState;  }
    public boolean fallingEdge()  { return fallingEdge ^ invertState; }
    public boolean state()        { return controlState ^ invertState; }

}

/*
    Button Types
        whenPressed  - true on rising edge (Inverted: when released - true on falling edge)
        whilePressed - true on rising edge (Inverted: whileNOtPressed)
        toggle - true when pressed and stays true until pressed again (Inverted: states reversed)
        timed - true when pressed and remains true for a specified amount of time

 */

