package org.xbot.ftc.robotcore.subsystems.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.utils.GameClock;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;

public class Drive extends XbotSubsystem {

    private static XbotSubsystem instance = null;
    private static boolean initialized = false;

    private DcMotor leftDriveMotor = null;
    private DcMotor rightDriveMotor = null;

    private double motorPowerMultiplier = 1.0;

    private static final double COUNTS_PER_MOTOR_REV = 280;
    private static final double DRIVE_GEAR_REDUCTION = 1.0;
    private static final double WHEEL_DIAMETER_INCHES = 4.0 ;
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                                                    (WHEEL_DIAMETER_INCHES * Math.PI);

    private TankDrive tankDrive;
    private ArcadeDrive arcadeDrive;

    public enum DriveMode {
        TANK, ARCADE, MECANUM
    }

    public enum DrivePower {
        FULL(1.0), HALF(0.5), QUARTER(0.25);

        public final double POWER;

        DrivePower(double power) {
            this.POWER = power;
        }
    }

    public enum TurnDirection {
        LEFT(1, -1), RIGHT(-1, 1);

        public final double LEFT_POWER;
        public final double RIGHT_POWER;

        TurnDirection(double leftPower, double rightPower) {
            this.LEFT_POWER = leftPower;
            this.RIGHT_POWER = rightPower;
        }
    }

    private Drive() {
    }

    @Override
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;

        super.init(hardwareMap, telemetry);
        leftDriveMotor = hardwareMap.get(DcMotor.class, XbotRobotConstants.FRONT_LEFT_DRIVE_MOTOR);
        rightDriveMotor = hardwareMap.get(DcMotor.class, XbotRobotConstants.FRONT_RIGHT_DRIVE_MOTOR);
        leftDriveMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDriveMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        tankDrive = new TankDrive(this);
        arcadeDrive = new ArcadeDrive(this);

        initialized = true;
    }

    public void setMotorPowerMultiplier(double motorPowerMultiplier) {
        this.motorPowerMultiplier = Range.clip(motorPowerMultiplier, 0.0, 1.0);
    }

    public void setMotorPowers(double leftFrontDrivePower, double rightFrontDrivePower) {
        leftFrontDrivePower *= motorPowerMultiplier;
        rightFrontDrivePower *= motorPowerMultiplier;
        leftDriveMotor.setPower(leftFrontDrivePower);
        rightDriveMotor.setPower(rightFrontDrivePower);
    }

    public void setMotorPowers(double power) {
        setMotorPowers(power, power);
    }

    public void turn(TurnDirection direction, DrivePower drivePower) {
        setMotorPowers(direction.LEFT_POWER * drivePower.POWER,
                direction.RIGHT_POWER * drivePower.POWER);
    }

    public void stop() {
        setMotorPowers(0);
    }

    public void encoderDrive(double power, double leftInches, double rightInches, double timeout) {
        int leftTarget = leftDriveMotor.getCurrentPosition() +
                (int)(leftInches * COUNTS_PER_INCH);
        int rightTarget = rightDriveMotor.getCurrentPosition() +
                (int)(rightInches * COUNTS_PER_INCH);
        leftDriveMotor.setTargetPosition(leftTarget);
        rightDriveMotor.setTargetPosition(rightTarget);

        leftDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double previousMultiplier = motorPowerMultiplier;
        setMotorPowerMultiplier(1.0);
        setMotorPowers(power);

        double startTime = GameClock.getInstance().getTimeElapsed();
        while (opMode.opModeIsActive() && GameClock.getInstance().getTimeElapsed() - startTime < timeout &&
                leftDriveMotor.isBusy() && rightDriveMotor.isBusy()) {
            telemetry.addData("Left Target: ", leftTarget)
                    .addData("Left Motor Current Position", leftDriveMotor.getCurrentPosition());
            telemetry.addData("Right Target: ", rightTarget)
                    .addData("Right Motor Current Position", rightDriveMotor.getCurrentPosition());
            telemetry.update();
        }

        setMotorPowers(0);
        setMotorPowerMultiplier(previousMultiplier);

        leftDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public DcMotor getLeftDriveMotor() {
        return leftDriveMotor;
    }

    public DcMotor getRightDriveMotor() {
        return rightDriveMotor;
    }

    public TankDrive getTankDrive() {
        return tankDrive;
    }

    public ArcadeDrive getArcadeDrive() {
        return arcadeDrive;
    }

    @Override
    public String getClassName() {
        return Drive.class.getName();
    }

    public static XbotSubsystem getInstance() {
        if (instance == null)
            instance = new Drive();
        return instance;
    }
}
