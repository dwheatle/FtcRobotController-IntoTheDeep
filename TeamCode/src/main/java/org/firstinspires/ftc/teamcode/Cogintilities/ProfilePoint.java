package org.firstinspires.ftc.teamcode.Cogintilities;

public class ProfilePoint {

    double displacement;
    double velocity;
    double acceleration;

    public ProfilePoint() {
        displacement = 0;
        velocity = 0;
        acceleration = 0;
    }

    public ProfilePoint(double displacement, double velocity, double acceleration){
        this.displacement = displacement;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public void setValues(double disp, double vel, double acc){
        displacement = disp;
        velocity = vel;
        acceleration = acc;
    }
    public void setDisp(double disp) { displacement = disp; }
    public void setVel(double vel)   { velocity = vel; }
    public void setAcc(double acc)   { acceleration = acc; }

    public double disp() { return displacement; }
    public double vel()  { return velocity;     }
    public double acc()  { return acceleration; }

}
