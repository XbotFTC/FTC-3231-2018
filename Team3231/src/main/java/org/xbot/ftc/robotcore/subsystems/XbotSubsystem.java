package org.xbot.ftc.robotcore.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class XbotSubsystem {

    protected static XbotSubsystem instance = null;
    protected HardwareMap hardwareMap;
    protected Telemetry telemetry;

    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
    }

    public String getClassName() {
        return null;
    }
}
