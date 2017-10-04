package org.xbot.ftc.operatingcode.teleop.operator_1;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.xbot.ftc.robotcore.XbotOpMode;
import org.xbot.ftc.robotcore.robot_systems.drive.TankDrive;

@TeleOp(name="TeleOpTankDrive", group="OpMode")
public class TeleOpTankDrive extends XbotOpMode {

    private TankDrive tankDrive;

    @Override
    public void init() {
        super.init();
        updateTelemetry("Status", "Initializing");
        tankDrive = new TankDrive(hardwareMap);
        updateTelemetry("Status", "Initialized");
    }

    @Override
    public void loop() {
        final double leftPower = gamepad1.left_stick_y;
        final double rightPower = gamepad1.right_stick_y;

        tankDrive.drive(leftPower, rightPower);
        updateTelemetry("Status", "Run Time: " + runtime.toString());
        updateTelemetry("Motors", "RightPower (%.2f), LeftPower (%.2f)", leftPower, rightPower);
    }

    @Override
    public void stop() {
        super.stop();
        updateTelemetry("Status", "Stopping TankDrive");
        tankDrive.stop();
        updateTelemetry("Status", "Stopped TankDrive");
    }
}
