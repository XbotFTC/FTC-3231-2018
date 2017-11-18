package org.xbot.ftc.operatingcode.teleop.operator_2;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.operatingcode.teleop.XbotOperatorSubHandler;
import org.xbot.ftc.robotcore.subsystems.cube.CubeGripper;

public class TeleOpGlyphGripper extends XbotOperatorSubHandler {

    private CubeGripper cubeGripper;

    @Override
    public void start() {
        cubeGripper = (CubeGripper) robotSystemsManager.getSubsystem(CubeGripper.class.getName());
    }

    @Override
    public void handle(Gamepad gamepad1, Gamepad gamepad2) {
        double leftTriggerPos = Range.clip(gamepad2.left_trigger, 0.0, 1.0);
        double rightTriggerPos = Range.clip(gamepad2.right_trigger, 0.0, 1.0);

        if (leftTriggerPos > 0)
            cubeGripper.setLeftServoPosition(0);
        else
            cubeGripper.setLeftServoPosition(1);
        if (rightTriggerPos > 0)
            cubeGripper.setRightServoPosition(0);
        else
            cubeGripper.setRightServoPosition(1);

    }

    @Override
    public void stop() {
        cubeGripper.setServoPositions(0, 0);
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addData("Left Servo Position: ", cubeGripper.getLeftServoPosition());
        telemetry.addData("Right Servo Position: ", cubeGripper.getRightServoPosition());
        telemetry.update();
    }
}
