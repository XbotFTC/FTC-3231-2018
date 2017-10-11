package org.xbot.ftc.robotcore.robot_systems.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.XbotDcMotor;

public class DriveManager {

    private HardwareMap hardwareMap;

    private XbotDcMotor leftFrontDrive = null;
    private XbotDcMotor rightFrontDrive = null;
    private XbotDcMotor leftRearDrive = null;
    private XbotDcMotor rightRearDrive = null;

    public DriveManager(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        init();
    }

    private void init() {
        leftFrontDrive  = new XbotDcMotor(
                hardwareMap.get(DcMotor.class, XbotRobotConstants.FRONT_LEFT_DRIVE_MOTOR));
        rightFrontDrive = new XbotDcMotor(
                hardwareMap.get(DcMotor.class, XbotRobotConstants.FRONT_RIGHT_DRIVE_MOTOR));
        leftRearDrive = new XbotDcMotor(
                hardwareMap.get(DcMotor.class, XbotRobotConstants.REAR_LEFT_DRIVE_MOTOR));
        rightRearDrive = new XbotDcMotor(
                hardwareMap.get(DcMotor.class, XbotRobotConstants.REAR_RIGHT_DRIVE_MOTOR));
        leftFrontDrive.getMotor().setDirection(DcMotor.Direction.FORWARD);
        leftRearDrive.getMotor().setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.getMotor().setDirection(DcMotor.Direction.REVERSE);
        rightRearDrive.getMotor().setDirection(DcMotor.Direction.REVERSE);
    }

    protected void setMotorPowers(double power) {
        leftFrontDrive.setPower(power);
        leftRearDrive.setPower(power);
        rightFrontDrive.setPower(power);
        rightRearDrive.setPower(power);
    }

    protected void setMotorPowers(double leftPower, double rightPower) {
        leftFrontDrive.setPower(leftPower);
        leftRearDrive.setPower(leftPower);
        rightFrontDrive.setPower(rightPower);
        rightRearDrive.setPower(rightPower);
    }

    protected void setMotorPowers(double leftFrontDrivePower, double leftRearDrivePower,
                               double rightFrontDrivePower, double rightRearDrivePower) {
        leftFrontDrive.setPower(leftFrontDrivePower);
        leftRearDrive.setPower(leftRearDrivePower);
        rightFrontDrive.setPower(rightFrontDrivePower);
        rightRearDrive.setPower(rightRearDrivePower);
    }

    public XbotDcMotor getLeftFrontDrive() {
        return leftFrontDrive;
    }

    public XbotDcMotor getRightFrontDrive() {
        return rightFrontDrive;
    }

    public XbotDcMotor getLeftRearDrive() {
        return leftRearDrive;
    }

    public XbotDcMotor getRightRearDrive() {
        return rightRearDrive;
    }
}
