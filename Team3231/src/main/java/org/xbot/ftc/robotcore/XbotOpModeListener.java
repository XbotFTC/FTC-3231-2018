package org.xbot.ftc.robotcore;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public interface XbotOpModeListener {
    void start(HardwareMap hardwareMap, Telemetry telemetry);
    void handler(Gamepad gamepad1, Gamepad gamepad2);
    void stop();
    void updateTelemetry();
}
