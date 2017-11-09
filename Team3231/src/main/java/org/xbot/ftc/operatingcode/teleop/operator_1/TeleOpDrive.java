package org.xbot.ftc.operatingcode.teleop.operator_1;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.operatingcode.teleop.XbotOperatorSubHandler;
import org.xbot.ftc.robotcore.subsystems.drive.ArcadeDrive;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.drive.TankDrive;

public class TeleOpDrive extends XbotOperatorSubHandler {

    private Drive drive;
    private TankDrive tankDrive;
    private ArcadeDrive arcadeDrive;

    private Drive.DriveMode driveMode = Drive.DriveMode.TANK;

    @Override
    public void start() {
        drive = (Drive) robotSystemsManager.getSubsystem(Drive.CLASS_NAME);
        tankDrive = drive.getTankDrive();
        arcadeDrive = drive.getArcadeDrive();
    }

    @Override
    public void handle(Gamepad gamepad1, Gamepad gamepad2) {
        if (gamepad1.dpad_up)
            drive.setMotorPowerMultiplier(1.0);
        else if (gamepad1.dpad_down)
            drive.setMotorPowerMultiplier(0.5);

        if (gamepad1.dpad_left)
            driveMode = Drive.DriveMode.TANK;
        else if (gamepad1.dpad_right)
            driveMode = Drive.DriveMode.ARCADE;

        if (driveMode == Drive.DriveMode.ARCADE)
            arcadeDrive.drive(gamepad1);
        else
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
