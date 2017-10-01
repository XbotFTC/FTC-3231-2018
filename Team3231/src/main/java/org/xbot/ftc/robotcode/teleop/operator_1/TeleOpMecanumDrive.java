package org.xbot.ftc.robotcode.teleop.operator_1;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.xbot.ftc.robotcore.XbotOpMode;
import org.xbot.ftc.robotcore.drive.MecanumDrive;

@TeleOp(name="TeleOpMecanumDrive", group="OpMode")
@Disabled
public class TeleOpMecanumDrive extends XbotOpMode {

    private MecanumDrive mecanumDrive;

    @Override
    public void init() {
        super.init();
        updateTelemetry("Status", "Initializing");
        mecanumDrive = new MecanumDrive(hardwareMap);
        updateTelemetry("Status", "Initialized");
    }

    @Override
    public void loop() {
        mecanumDrive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

        updateTelemetry("Status", "Run Time: " + runtime.toString());
        updateTelemetry("Motors",
                "FrontLeft (%.2f), FrontRight (%.2f), RearLeft (%.2f), RearRight",
                mecanumDrive.getFrontLeftPower(),
                mecanumDrive.getFrontRightPower(),
                mecanumDrive.getRearLeftPower(),
                mecanumDrive.getRearRightPower());
    }
}
