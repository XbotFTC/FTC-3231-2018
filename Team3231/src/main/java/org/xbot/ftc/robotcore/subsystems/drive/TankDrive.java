package org.xbot.ftc.robotcore.subsystems.drive;

class TankDrive {

    private Drive drive;

    private TankDrive(Drive drive) {
        this.drive = drive;
    }

    public void drive(double leftPower, double rightPower) {
        drive.setMotorPowers(leftPower, rightPower);
    }

    public void stop() {
        drive.setMotorPowers(0);
    }
}
