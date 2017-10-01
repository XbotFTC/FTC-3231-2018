package org.xbot.ftc.robotcode.teleop.operator_1;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.xbot.ftc.robotcore.RobotConstants;
import org.xbot.ftc.robotcore.XbotOpMode;

@TeleOp(name="MecanumDrive", group="Linear Opmode")
@Disabled
public class MecanumDrive extends XbotOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftRearDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightRearDrive = null;

    @Override
    public void runOpMode() {
        xbotInit();
        waitForStart();

        while (opModeIsActive()) {
            xbotLoop();
        }
    }

    @Override
    public void xbotInit() {
        super.xbotInit();

        leftFrontDrive  = hardwareMap.get(DcMotor.class, RobotConstants.FRONT_LEFT_DRIVE_MOTOR);
        leftRearDrive = hardwareMap.get(DcMotor.class, RobotConstants.REAR_LEFT_DRIVE_MOTOR);
        rightFrontDrive = hardwareMap.get(DcMotor.class, RobotConstants.FRONT_RIGHT_DRIVE_MOTOR);
        rightRearDrive = hardwareMap.get(DcMotor.class, RobotConstants.REAR_RIGHT_DRIVE_MOTOR);

        if (leftFrontDrive == null || leftRearDrive == null ||
                rightFrontDrive == null || rightRearDrive == null) {
            xbotUpdateTelemetry("Status", "Motor Failed To Initialize");
            return;
        }

        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftRearDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightRearDrive.setDirection(DcMotor.Direction.REVERSE);

        xbotUpdateTelemetry("Status", "Initialized");
    }

    /**
     * This method needs to be optimized to get full power out of the robot
     */
    @Override
    public void xbotLoop() {
        double r = Math.hypot(-gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = -gamepad1.right_stick_x;
        final double frontLeftPower = r * Math.cos(robotAngle) + rightX;
        final double frontRightPower = r * Math.sin(robotAngle) - rightX;
        final double rearLeftPower = r * Math.sin(robotAngle) + rightX;
        final double rearRightPower = r * Math.cos(robotAngle) - rightX;

        leftFrontDrive.setPower(frontLeftPower);
        rightFrontDrive.setPower(frontRightPower);
        leftRearDrive.setPower(rearLeftPower);
        rightRearDrive.setPower(rearRightPower);

        super.xbotUpdateTelemetry("Status", "Run Time: " + runtime.toString());
        super.xbotUpdateTelemetry("Motors",
                "FrontRight (%.2f), FrontLeft (%.2f), RearRight (%.2f), RearLeft",
                frontRightPower, frontLeftPower, rearRightPower, rearLeftPower);
    }
}
