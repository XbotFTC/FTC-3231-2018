package org.xbot.ftc.operatingcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.cube.CubeElevator;
import org.xbot.ftc.robotcore.subsystems.cube.CubeGripper;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;

@Autonomous(name="Main: Auto", group="Main")
public class AutoProgram extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        BaseRobot.initOpMode(this, hardwareMap, telemetry);
        RobotSubsystemManager robotSubsystemManager = RobotSubsystemManager.getInstance();
        Drive drive = (Drive) robotSubsystemManager.getSubsystem(Drive.CLASS_NAME);
        CubeElevator cubeElevator =
                (CubeElevator) robotSubsystemManager.getSubsystem(CubeElevator.CLASS_NAME);
        CubeGripper cubeGripper =
                (CubeGripper) robotSubsystemManager.getSubsystem(CubeGripper.CLASS_NAME);

        waitForStart();
        robotSubsystemManager.getGameClock().resetClock();

        cubeGripper.grip();
        Thread.sleep(300);
        cubeElevator.lift();
        Thread.sleep(400);
        cubeElevator.stop();
        drive.encoderDrive(0.6, 10, 10, 5);
        drive.encoderDrive(1, 5, -5, 2);
        drive.encoderDrive(0.4, 8, 8, 4);
        cubeElevator.down();
        Thread.sleep(200);
        cubeElevator.stop();
        cubeGripper.letItGo();
    }
}
