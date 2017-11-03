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

    private double motorPowerMultiplier = 1.0;

    private MecanumDrive mecanumDrive;
    private TankDrive tankDrive;
    private ArcadeDrive arcadeDrive;

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
        arcadeDrive = new ArcadeDrive(this);

        initialized = true;
    }

    public void setMotorPowerMultiplier(double motorPowerMultiplier) {
        this.motorPowerMultiplier = motorPowerMultiplier;
    }


    public void setMotorPowers(double leftFrontDrivePower, double rightFrontDrivePower) {
        leftFrontDrivePower *= motorPowerMultiplier;
        rightFrontDrivePower *= motorPowerMultiplier;
        leftFrontDrive.setPower(leftFrontDrivePower);
        rightFrontDrive.setPower(rightFrontDrivePower);
    }

    public void setMotorPowers(double power) {
        setMotorPowers(power, power);
    }

    public void stop() {
        setMotorPowers(0);
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

    public ArcadeDrive getArcadeDrive() {
        return arcadeDrive;
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
