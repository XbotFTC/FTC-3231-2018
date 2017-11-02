package org.xbot.ftc.operatingcode.teleop.operator_1;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.operatingcode.teleop.XbotOperatorSubHandler;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.drive.TankDrive;

public class TeleOpTankDrive extends XbotOperatorSubHandler {

    private Drive drive;
    private TankDrive tankDrive;

    @Override
    public void start(HardwareMap hardwareMap, Telemetry telemetry) {
        super.start(hardwareMap, telemetry);
        drive = (Drive) robotSystemsManager.getSubsystem(Drive.CLASS_NAME);
        tankDrive = drive.getTankDrive();
    }

    @Override
    public void handle(Gamepad gamepad1, Gamepad gamepad2) {
        tankDrive.drive(Range.clip(gamepad1.left_stick_y, -1, 1),
                        Range.clip(gamepad1.right_stick_y, -1, 1));
    }

    @Override
    public void stop() {
        tankDrive.stop();
    }

    @Override
    public void updateTelemetry() {
        telemetry.addData("LeftPower: ", drive.getLeftFrontDrive().getPower());
        telemetry.addData("RightPower: ", drive.getRightFrontDrive().getPower());
        telemetry.update();
    }
}
