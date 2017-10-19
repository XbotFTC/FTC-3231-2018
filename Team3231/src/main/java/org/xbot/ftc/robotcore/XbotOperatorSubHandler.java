package org.xbot.ftc.robotcore;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class XbotOperatorSubHandler implements XbotTeleOpListener {
    protected RobotSystemsManager robotSystemsManager = RobotSystemsManager.getInstance();

    @Override
    public void start(HardwareMap hardwareMap, Telemetry telemetry) {
        robotSystemsManager.init(hardwareMap);
    }
}
