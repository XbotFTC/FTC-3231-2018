package org.xbot.ftc.robotcore.robot_systems.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.xbot.ftc.robotcore.XbotRobotConstants;

class DriveManager {

    private static DriveManager instance = null;
    private static boolean initialized = false;

    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftRearDrive = null;
    private DcMotor rightRearDrive = null;

    private DriveManager() {
    }

    public void init(HardwareMap hardwareMap, Direction leftFrontMotorDirection,
                                                Direction leftRearMotorDirection,
                                                Direction rightFrontMotorDirection,
                                                Direction rightRearMotorDirection) {
        if (initialized) return;
        leftFrontDrive  = hardwareMap.get(DcMotor.class, XbotRobotConstants.FRONT_LEFT_DRIVE_MOTOR);
        rightFrontDrive = hardwareMap.get(DcMotor.class, XbotRobotConstants.FRONT_RIGHT_DRIVE_MOTOR);
        leftRearDrive = hardwareMap.get(DcMotor.class, XbotRobotConstants.REAR_LEFT_DRIVE_MOTOR);
        rightRearDrive = hardwareMap.get(DcMotor.class, XbotRobotConstants.REAR_RIGHT_DRIVE_MOTOR);
        leftFrontDrive.setDirection(leftFrontMotorDirection);
        leftRearDrive.setDirection(leftRearMotorDirection);
        rightFrontDrive.setDirection(rightFrontMotorDirection);
        rightRearDrive.setDirection(rightRearMotorDirection);
        initialized = true;
    }

    public void init(HardwareMap hardwareMap) {
       init(hardwareMap, Direction.FORWARD, Direction.FORWARD, Direction.REVERSE, Direction.REVERSE);
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

    public DcMotor getLeftFrontDrive() {
        return leftFrontDrive;
    }

    public DcMotor getRightFrontDrive() {
        return rightFrontDrive;
    }

    public DcMotor getLeftRearDrive() {
        return leftRearDrive;
    }

    public DcMotor getRightRearDrive() {
        return rightRearDrive;
    }

    public synchronized static DriveManager getInstance() {
        if (instance == null) {
            instance = new DriveManager();
        }
        return instance;
    }
}
