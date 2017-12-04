package org.xbot.ftc.operatingcode.autonomous.jewel_smacker;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.arm.JewelArm;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.vision.XbotColorSensor;
import org.xbot.ftc.robotcore.utils.GameClock;

public class BaseJewelAuto {

    private XbotColorSensor.Color colorToTakeDown;

    private LinearOpMode opMode;
    private Telemetry telemetry;

    private XbotColorSensor xbotColorSensor;
    private Drive drive;
    private JewelArm jewelArm;

    public BaseJewelAuto(XbotColorSensor.Color teamColor, LinearOpMode opMode, HardwareMap hardwareMap, Telemetry telemetry) {
        if (teamColor == XbotColorSensor.Color.BLUE)
            colorToTakeDown = XbotColorSensor.Color.RED;
        else
            colorToTakeDown = XbotColorSensor.Color.BLUE;

        BaseRobot.initOpMode(opMode, hardwareMap, telemetry);
        RobotSubsystemManager robotSubsystemManager = RobotSubsystemManager.getInstance();
        this.opMode = opMode;
        this.telemetry = telemetry;

        xbotColorSensor = (XbotColorSensor) robotSubsystemManager.getSubsystem(XbotColorSensor.class.getName());
        drive = (Drive) robotSubsystemManager.getSubsystem(Drive.class.getName());
        jewelArm = (JewelArm) robotSubsystemManager.getSubsystem(JewelArm.class.getName());
    }

    public void run() {
        GameClock gameClock = RobotSubsystemManager.getInstance().getGameClock();
        jewelArm.setPosition(JewelArm.ArmPosition.DOWN);
        gameClock.delay(0.7);
        XbotColorSensor.Color colorDetected = keepDetectingUntilColorFound(xbotColorSensor);
        telemetry.addData("Color Detected: ", colorDetected);
        telemetry.update();

        if (colorToTakeDown == colorDetected)
            drive.turn(Drive.TurnDirection.RIGHT, Drive.DrivePower.FULL);
        else
            drive.turn(Drive.TurnDirection.LEFT, Drive.DrivePower.FULL);

        gameClock.delay(0.7);

        drive.stop();
        jewelArm.setPosition(JewelArm.ArmPosition.UP);

        gameClock.delay(1.0);
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

    private XbotColorSensor.Color keepDetectingUntilColorFound(XbotColorSensor colorSensor) {
        XbotColorSensor.Color currentColor = colorSensor.getCurrentColorSeen();
        while (currentColor == XbotColorSensor.Color.OTHER && opMode.opModeIsActive()) {
            currentColor = colorSensor.getCurrentColorSeen();
            if (currentColor != XbotColorSensor.Color.OTHER)
                break;
        }
        return currentColor;
    }
}
