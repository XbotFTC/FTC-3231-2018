package org.xbot.ftc.robotcode.teleop.operator_1;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.xbot.ftc.robotcore.RobotConstants;
import org.xbot.ftc.robotcore.XbotOpMode;

@TeleOp(name="TankDrive", group="OpMode")
public class TankDrive extends XbotOpMode {

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

    @Override
    public void loop() {
        final double leftPower = gamepad1.left_stick_y;
        final double rightPower = gamepad1.right_stick_y;

        leftFrontDrive.setPower(leftPower);
        leftRearDrive.setPower(leftPower);
        rightFrontDrive.setPower(rightPower);
        rightRearDrive.setPower(rightPower);

        updateTelemetry("Status", "Run Time: " + runtime.toString());
        updateTelemetry("Motors", "RightPower (%.2f), LeftPower (%.2f)", leftPower, rightPower);
    }
}
