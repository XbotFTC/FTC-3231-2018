package org.xbot.ftc.operatingcode.teleop.operator_2;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.operatingcode.teleop.XbotOperatorSubHandler;
import org.xbot.ftc.robotcore.subsystems.arm.JewelArm;

public class TeleOpJewelSmacker extends XbotOperatorSubHandler {

    private JewelArm jewelArm;

    @Override
    public void start() {
        jewelArm = (JewelArm) robotSystemsManager.getSubsystem(JewelArm.CLASS_NAME);
    }

    @Override
    public void handle(Gamepad gamepad1, Gamepad gamepad2) {
        if (gamepad2.dpad_up)
            jewelArm.toggleArm();
    }

    @Override
    public void stop() {
        jewelArm.setPosition(0);
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {

    }
}
