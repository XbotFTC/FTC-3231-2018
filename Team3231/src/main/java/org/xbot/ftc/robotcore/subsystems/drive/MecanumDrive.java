package org.xbot.ftc.robotcore.subsystems.drive;

/**
 * CLASS IS NOT USED
 */
public class MecanumDrive {

    private Drive drive;

    private double frontLeftPower;
    private double frontRightPower;
    private double rearLeftPower;
    private double rearRightPower;

    protected MecanumDrive(Drive drive) {
        this.drive = drive;
    }

    /**
     * This method needs to be optimized to get full power out of the robot
     */
    public void drive(double left_stick_x, double left_stick_y, double right_stick_x) {
        double r = Math.hypot(-left_stick_x, left_stick_y);
        double robotAngle = Math.atan2(left_stick_y, -left_stick_x) - Math.PI / 4;
        double rightX = -right_stick_x;
        frontLeftPower = r * Math.cos(robotAngle) + rightX;
        frontRightPower = r * Math.sin(robotAngle) - rightX;
        rearLeftPower = r * Math.sin(robotAngle) + rightX;
        rearRightPower = r * Math.cos(robotAngle) - rightX;

        //drive.setMotorPowers(frontLeftPower, rearLeftPower, frontRightPower, rearRightPower);
    }

    public void stop() {
        drive.setMotorPowers(0);
    }

    public double getFrontLeftPower() {
        return frontLeftPower;
    }

    public double getFrontRightPower() {
        return frontRightPower;
    }

    public double getRearLeftPower() {
        return rearLeftPower;
    }

    public double getRearRightPower() {
        return rearRightPower;
    }
}
