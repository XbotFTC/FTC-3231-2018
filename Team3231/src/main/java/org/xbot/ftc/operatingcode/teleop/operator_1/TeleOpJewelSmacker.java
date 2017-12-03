package org.xbot.ftc.operatingcode.teleop.operator_1;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.operatingcode.teleop.XbotOperatorSubHandler;
import org.xbot.ftc.robotcore.subsystems.arm.JewelArm;

public class TeleOpJewelSmacker extends XbotOperatorSubHandler {

    private JewelArm jewelArm;

    @Override
    public void start() {
        jewelArm = (JewelArm) robotSystemsManager.getSubsystem(JewelArm.class.getName());
        jewelArm.setPosition(JewelArm.ArmPosition.UP);
    }

    @Override
    public void handle(Gamepad gamepad1, Gamepad gamepad2) {
        if (gamepad1.x)
            jewelArm.setPosition(JewelArm.ArmPosition.UP);
        else if (gamepad1.b)
            jewelArm.setPosition(JewelArm.ArmPosition.DOWN);
    }

    @Override
    public void stop() {
        jewelArm.setPosition(JewelArm.ArmPosition.UP);
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {

    }
}
