package org.xbot.ftc.robotcode.teleop.operator_1;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.xbot.ftc.robotcore.RobotConstants;
import org.xbot.ftc.robotcore.XbotOpMode;

@TeleOp(name="MecanumDrive", group="OpMode")
@Disabled
public class MecanumDrive extends XbotOpMode {

    private DcMotor leftFrontDrive = null;
    private DcMotor leftRearDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightRearDrive = null;

    @Override
    public void init() {
        super.init();

        leftFrontDrive  = hardwareMap.get(DcMotor.class, RobotConstants.FRONT_LEFT_DRIVE_MOTOR);
        leftRearDrive = hardwareMap.get(DcMotor.class, RobotConstants.REAR_LEFT_DRIVE_MOTOR);
        rightFrontDrive = hardwareMap.get(DcMotor.class, RobotConstants.FRONT_RIGHT_DRIVE_MOTOR);
        rightRearDrive = hardwareMap.get(DcMotor.class, RobotConstants.REAR_RIGHT_DRIVE_MOTOR);

        if (leftFrontDrive == null || leftRearDrive == null ||
                rightFrontDrive == null || rightRearDrive == null) {
            updateTelemetry("Status", "Motor Failed To Initialize");
            return;
        }

        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftRearDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightRearDrive.setDirection(DcMotor.Direction.REVERSE);

        updateTelemetry("Status", "Initialized");
    }

    /**
     * This method needs to be optimized to get full power out of the robot
     */
    @Override
    public void loop() {
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

        updateTelemetry("Status", "Run Time: " + runtime.toString());
        updateTelemetry("Motors",
                "FrontRight (%.2f), FrontLeft (%.2f), RearRight (%.2f), RearLeft",
                frontRightPower, frontLeftPower, rearRightPower, rearLeftPower);
    }
}
