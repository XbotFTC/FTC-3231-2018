package org.xbot.ftc.robotcore.subsystems.drive;

import com.qualcomm.robotcore.util.Range;

public class TankDrive {

    private Drive drive;

    protected TankDrive(Drive drive) {
        this.drive = drive;
    }

    public void drive(double leftStickY, double rightStickyY) {
        drive.setMotorPowers(leftStickY, rightStickyY);
    }

    public void stop() {
        drive.setMotorPowers(0);
    }
}
