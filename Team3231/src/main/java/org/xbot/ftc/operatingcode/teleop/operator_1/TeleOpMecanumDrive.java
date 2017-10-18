package org.xbot.ftc.operatingcode.teleop.operator_1;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.XbotOpModeListener;
import org.xbot.ftc.robotcore.robot_systems.drive.MecanumDrive;

public class TeleOpMecanumDrive implements XbotOpModeListener {

    private MecanumDrive mecanumDrive;
    private Telemetry telemetry;

    @Override
    public void start(HardwareMap hardwareMap, Telemetry telemetry) {
        mecanumDrive = new MecanumDrive(hardwareMap);
        this.telemetry = telemetry;
    }

    @Override
    public void handle(Gamepad gamepad1, Gamepad gamepad2) {
        mecanumDrive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
    }

    @Override
    public void stop() {
        mecanumDrive.stop();
    }

    @Override
    public void updateTelemetry() {
        telemetry.addData("Motors",
                "FrontLeft (%.2f), FrontRight (%.2f), RearLeft (%.2f), RearRight",
                mecanumDrive.getFrontLeftPower(),
                mecanumDrive.getFrontRightPower(),
                mecanumDrive.getRearLeftPower(),
                mecanumDrive.getRearRightPower());
        telemetry.update();
    }
}
