package org.xbot.ftc.operatingcode.teleop.operator_1;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.operatingcode.teleop.XbotOperatorSubHandler;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.drive.TankDrive;

public class TeleOpDrive extends XbotOperatorSubHandler {

    private Drive drive;
    private TankDrive tankDrive;

    @Override
    public void start() {
        drive = (Drive) robotSystemsManager.getSubsystem(Drive.class.getName());
        tankDrive = drive.getTankDrive();
        drive.setMotorPowerMultiplier(1.0);
    }

    @Override
    public void handle(Gamepad gamepad1, Gamepad gamepad2) {
        if (gamepad1.dpad_up)
            drive.setMotorPowerMultiplier(1.0);

        tankDrive.drive(gamepad1);
    }

    @Override
    public void stop() {
        drive.stop();
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addData("LeftPower: ", drive.getLeftDriveMotor().getPower());
        telemetry.addData("RightPower: ", drive.getRightDriveMotor().getPower());
        telemetry.update();
    }
}
