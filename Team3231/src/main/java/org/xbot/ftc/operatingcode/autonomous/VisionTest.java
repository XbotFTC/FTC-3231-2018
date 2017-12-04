package org.xbot.ftc.operatingcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.cube.CubeElevator;
import org.xbot.ftc.robotcore.subsystems.cube.CubeGripper;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.vision.PictographIdentifier;
import org.xbot.ftc.robotcore.subsystems.vision.XbotColorSensor;

@Autonomous(name="Main: Vision", group="Main")
@Disabled
public class VisionTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        BaseRobot.initOpMode(this, hardwareMap, telemetry);
        RobotSubsystemManager robotSubsystemManager = RobotSubsystemManager.getInstance();
        PictographIdentifier pictographIdentifier = (PictographIdentifier)robotSubsystemManager.getSubsystem(PictographIdentifier.class.getName());
        XbotColorSensor colorSensor = (XbotColorSensor) robotSubsystemManager.getSubsystem(XbotColorSensor.class.getName()) ;
        waitForStart();
        System.out.println("TEST: " + colorSensor);
        robotSubsystemManager.getGameClock().resetClock();
        while (opModeIsActive()) {
            telemetry.addData("Color", colorSensor.getCurrentColorSeen());
            telemetry.addData("Color Int", colorSensor.colorTest());
            telemetry.addData("Where To Place", pictographIdentifier.whereDoesTheRobotPutThisBox());
            telemetry.update();
        }
    }
}
