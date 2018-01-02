package org.xbot.ftc.operatingcode.teleop.operator_2;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.operatingcode.teleop.XbotOperatorSubHandler;
import org.xbot.ftc.robotcore.subsystems.cube.CubeElevator;

public class TeleOpElevator extends XbotOperatorSubHandler {

    private CubeElevator cubeElevator;

    @Override
    public void start() {
        cubeElevator = (CubeElevator) robotSystemsManager.getSubsystem(CubeElevator.class.getName());
    }

    @Override
    public void handle(Gamepad gamepad1, Gamepad gamepad2) {
        cubeElevator.setPower(Range.clip(gamepad2.right_stick_y, -1.0, 1.0));
    }

    @Override
    public void stop() {
        cubeElevator.stop();
    }

    @Override
    public void updateTelemetry() {
    }
}
