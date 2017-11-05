package org.xbot.ftc.robotcore.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.Utils.GameClock;
import org.xbot.ftc.robotcore.XbotSubsystemRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RobotSubsystemManager {

    private static RobotSubsystemManager instance = null;
    private static boolean initialized = false;

    private static List<XbotSubsystem> registeredSubsystems = new ArrayList<>();
    private static Map<String, XbotSubsystem> registeredSubsystemsMap = new HashMap<>();

    public GameClock gameClock;

    private RobotSubsystemManager() {
    }

    public static void registerSubsystem(XbotSubsystem subsystem) {
        registeredSubsystems.add(subsystem);
    }

    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;
        XbotSubsystemRegister.registerListeners();
        gameClock = GameClock.getInstance();
        for (XbotSubsystem subsystem : registeredSubsystems) {
            subsystem.init(hardwareMap, telemetry);
            registeredSubsystemsMap.put(subsystem.getClassName(), subsystem);
        }

        initialized = true;
    }

    public XbotSubsystem getSubsystem(String className) {
        return registeredSubsystemsMap.get(className);
    }

    public synchronized static RobotSubsystemManager getInstance() {
        if (instance == null) {
            instance = new RobotSubsystemManager();
        }
        return instance;
    }
}
