package org.xbot.ftc.operatingcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.arm.JewelArm;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.vision.XbotColorSensor;

public class BaseJewelAuto {

    public enum AllianceColor {
        BLUE, RED
    }

    private AllianceColor colorToTakeDown;

    private final LinearOpMode opMode;

    private XbotColorSensor xbotColorSensor;
    private Drive drive;
    private JewelArm jewelArm;

    public BaseJewelAuto(AllianceColor teamColor, LinearOpMode opMode, HardwareMap hardwareMap, Telemetry telemetry) {
        if (teamColor == AllianceColor.BLUE)
            colorToTakeDown = AllianceColor.RED;
        else
            colorToTakeDown = AllianceColor.BLUE;

        BaseRobot.initOpMode(opMode, hardwareMap, telemetry);
        RobotSubsystemManager robotSubsystemManager = RobotSubsystemManager.getInstance();
        this.opMode = opMode;

        xbotColorSensor = (XbotColorSensor) robotSubsystemManager.getSubsystem(XbotColorSensor.class.getName());
        drive = (Drive) robotSubsystemManager.getSubsystem(Drive.class.getName());
        jewelArm = (JewelArm) robotSubsystemManager.getSubsystem(JewelArm.class.getName());
    }

    public XbotColorSensor getXbotColorSensor() {
        return xbotColorSensor;
    }

    public Drive getDrive() {
        return drive;
    }

    public JewelArm getJewelArm() {
        return jewelArm;
    }

    public XbotColorSensor.Color keepDetecting(XbotColorSensor colorSensor) {
        XbotColorSensor.Color currentColor = colorSensor.getCurrentColorSeen();
        while (currentColor == XbotColorSensor.Color.OTHER && opMode.opModeIsActive()) {
            currentColor = colorSensor.getCurrentColorSeen();
            if (currentColor != XbotColorSensor.Color.OTHER)
                break;
        }
        return currentColor;
    }
}
