package org.xbot.ftc.operatingcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.cube.CubeElevator;
import org.xbot.ftc.robotcore.subsystems.cube.CubeGripper;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.vision.PictographIdentifier;

@Autonomous(name="Main: Vision", group="Main")
public class VisionTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        BaseRobot.initOpMode(this, hardwareMap, telemetry);
        RobotSubsystemManager robotSubsystemManager = RobotSubsystemManager.getInstance();
        PictographIdentifier pictographIdentifier = (PictographIdentifier)robotSubsystemManager.getSubsystem(PictographIdentifier.class.getName());
        waitForStart();

        while (opModeIsActive()) {
            robotSubsystemManager.getGameClock().resetClock();
            RelicRecoveryVuMark v = pictographIdentifier.whereDoesTheRobotPutThisBox();
            telemetry.addData("Where To Place", v);
            telemetry.update();
        }
    }
}
