package org.xbot.ftc.robotcore;

import com.qualcomm.robotcore.hardware.DcMotor;

public class XbotDcMotor {

    private DcMotor motor;
    private boolean inverted = false;

    public XbotDcMotor(DcMotor motor) {
        this.motor = motor;
    }

    public DcMotor getMotor() {
        return motor;
    }

    public void setPower(double power) {
        if (inverted) {
            power = -power;
        }
        motor.setPower(power);
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public boolean isInverted() {
        return inverted;
    }
}
