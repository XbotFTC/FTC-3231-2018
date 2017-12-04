package org.xbot.ftc.robotcore.subsystems.vision;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;

public class XbotColorSensor extends XbotSubsystem {

    private static XbotSubsystem instance = null;
    private static boolean initialized = false;

    private ColorSensor colorSensor = null;

    public enum Color {
        RED, BLUE, OTHER
    }

    private XbotColorSensor() {
    }

    @Override
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;
        super.init(hardwareMap, telemetry);
        colorSensor = hardwareMap.get(ColorSensor.class, XbotRobotConstants.MODERN_ROBOTICS_COLOR_SENSOR);
        initialized = true;
    }

    public int colorTest() {
        return colorSensor.argb();
    }

    public Color getCurrentColorSeen() {
        if (colorSensor.red() > colorSensor.blue()) {
            return Color.RED;
        } else if (colorSensor.blue() > colorSensor.red()) {
            return Color.BLUE;
        }
        return Color.OTHER;
    }

    @Override
    public String getClassName() {
        return XbotColorSensor.class.getName();
    }

    public static XbotSubsystem getInstance() {
        if (instance == null) {
            instance = new XbotColorSensor();
        }
        return instance;
    }
}
