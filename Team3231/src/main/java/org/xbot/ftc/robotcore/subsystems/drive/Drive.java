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

    private MecanumDrive mecanumDrive;
    private TankDrive tankDrive;

    private Drive() {
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        if (initialized) return;
        leftFrontDrive  = hardwareMap.get(DcMotor.class, XbotRobotConstants.FRONT_LEFT_DRIVE_MOTOR);
        rightFrontDrive = hardwareMap.get(DcMotor.class, XbotRobotConstants.FRONT_RIGHT_DRIVE_MOTOR);
        leftFrontDrive.setDirection(Direction.FORWARD);
        rightFrontDrive.setDirection(Direction.FORWARD);

        tankDrive = new TankDrive(this);

        initialized = true;
    }

    public void setMotorPowers(double power) {
        leftFrontDrive.setPower(power);
        rightFrontDrive.setPower(power);
    }

    public void setMotorPowers(double leftFrontDrivePower, double rightFrontDrivePower) {
        leftFrontDrive.setPower(leftFrontDrivePower);
        rightFrontDrive.setPower(rightFrontDrivePower);
    }

    public DcMotor getLeftFrontDrive() {
        return leftFrontDrive;
    }

    public DcMotor getRightFrontDrive() {
        return rightFrontDrive;
    }

    public MecanumDrive getMecanumDrive() {
        return mecanumDrive;
    }

    public TankDrive getTankDrive() {
        return tankDrive;
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
