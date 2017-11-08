package org.xbot.ftc.robotcore.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.utils.GameClock;
import org.xbot.ftc.robotcore.XbotSubsystemRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RobotSubsystemManager {

    private static RobotSubsystemManager instance = null;
    private boolean initialized = false;

    private Map<String, XbotSubsystem> registeredSubsystemsMap = new HashMap<>();

    public GameClock gameClock;

    private RobotSubsystemManager() {
    }

    public void registerSubsystem(XbotSubsystem subsystem) {
        registeredSubsystemsMap.put(subsystem.getClassName(), subsystem);
    }

    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;
        XbotSubsystemRegister.registerListeners();
        gameClock = GameClock.getInstance();
        for (XbotSubsystem subsystem : registeredSubsystemsMap.values()) {
            subsystem.init(hardwareMap, telemetry);
        }
        gameClock.resetClock();


        initialized = true;
    }

    public void setActiveOpMode(LinearOpMode opMode) {
        for (XbotSubsystem subsystem : registeredSubsystemsMap.values()) {
            subsystem.setActiveOpMode(opMode);
        }
    }

    public XbotSubsystem getSubsystem(String className) {
        return registeredSubsystemsMap.get(className);
    }

    public static RobotSubsystemManager getInstance() {
        if (instance == null) {
            instance = new RobotSubsystemManager();
        }
        return instance;
    }
}
