package org.xbot.ftc.robotcore.subsystems.drive;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

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
    public void drive(Gamepad gamepad) {
        double r = Math.hypot(Range.clip(-gamepad.left_stick_x, -1.0, 1.0),
                                Range.clip(gamepad.left_stick_y, -1.0, 1.0));
        double robotAngle = Math.atan2(Range.clip(gamepad.left_stick_y, -1.0, 1.0),
                                        Range.clip(-gamepad.left_stick_x, -1.0, 1.0)) - Math.PI / 4;
        double rightX = Range.clip(-gamepad.right_stick_x, -1.0, 1.0);
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
