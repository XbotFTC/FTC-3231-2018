package org.xbot.ftc.robotcore.subsystems.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;

public class Drive extends XbotSubsystem {

    public static final String CLASS_NAME = Drive.class.getName();
    private static boolean initialized = false;

    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftRearDrive = null;
    private DcMotor rightRearDrive = null;

    private MecanumDrive mecanumDrive;

    private Drive() {
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        if (initialized) return;
        leftFrontDrive  = hardwareMap.get(DcMotor.class, XbotRobotConstants.FRONT_LEFT_DRIVE_MOTOR);
        rightFrontDrive = hardwareMap.get(DcMotor.class, XbotRobotConstants.FRONT_RIGHT_DRIVE_MOTOR);
        leftRearDrive = hardwareMap.get(DcMotor.class, XbotRobotConstants.REAR_LEFT_DRIVE_MOTOR);
        rightRearDrive = hardwareMap.get(DcMotor.class, XbotRobotConstants.REAR_RIGHT_DRIVE_MOTOR);
        leftFrontDrive.setDirection(Direction.REVERSE);
        leftRearDrive.setDirection(Direction.FORWARD);
        rightFrontDrive.setDirection(Direction.REVERSE);
        rightRearDrive.setDirection(Direction.FORWARD);

        mecanumDrive = new MecanumDrive(this);

        initialized = true;
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

    public MecanumDrive getMecanumDrive() {
        return mecanumDrive;
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    public static XbotSubsystem getInstance() {
        if (instance == null) {
            instance = new Drive();
        }
        return instance;
    }
}
