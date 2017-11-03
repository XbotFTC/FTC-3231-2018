package org.xbot.ftc.operatingcode.teleop;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;

public abstract class XbotOperatorSubHandler implements XbotTeleOpHandler {

    protected RobotSubsystemManager robotSystemsManager = RobotSubsystemManager.getInstance();
    protected Telemetry telemetry;

    @Override
    public void start(HardwareMap hardwareMap, Telemetry telemetry) {
        robotSystemsManager.init(hardwareMap);
        this.telemetry = telemetry;
    }
}
