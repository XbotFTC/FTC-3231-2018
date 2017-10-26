package org.xbot.ftc.robotcore.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

public abstract class XbotSubsystem {

    protected static XbotSubsystem instance = null;

    public void init(HardwareMap hardwareMap) {
    }

    public String getClassName() {
        return null;
    }
}
