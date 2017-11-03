package org.xbot.ftc.operatingcode.teleop.operator_2;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.operatingcode.teleop.XbotOperatorSubHandler;
import org.xbot.ftc.robotcore.subsystems.cube.CubeGripper;

public class TeleOpGlyphGripper extends XbotOperatorSubHandler {

    private CubeGripper cubeGripper;

    @Override
    public void start(HardwareMap hardwareMap, Telemetry telemetry) {
        super.start(hardwareMap, telemetry);
        cubeGripper = (CubeGripper) robotSystemsManager.getSubsystem(CubeGripper.CLASS_NAME);
    }

    @Override
    public void handle(Gamepad gamepad1, Gamepad gamepad2) {
        if (gamepad2.left_trigger > 0.1) {
            cubeGripper.setLeftServoPosition(1);
        } else {
            cubeGripper.setLeftServoPosition(0);
        }

        if (gamepad2.right_trigger > 0.1) {
            cubeGripper.setRightServoPosition(0);
        } else {
            cubeGripper.setRightServoPosition(1);
        }
    }

    @Override
    public void stop() {
        cubeGripper.setServoPositions(0, 1);
    }

    @Override
    public void updateTelemetry() {

    }
}
