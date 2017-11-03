package org.xbot.ftc.operatingcode.teleop;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

interface XbotTeleOpHandler {
    void start(HardwareMap hardwareMap, Telemetry telemetry);
    void handle(Gamepad gamepad1, Gamepad gamepad2);
    void stop();
    void updateTelemetry();
}
