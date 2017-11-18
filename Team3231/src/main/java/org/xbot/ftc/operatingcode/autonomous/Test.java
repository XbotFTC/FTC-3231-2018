package org.xbot.ftc.operatingcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.cube.CubeElevator;
import org.xbot.ftc.robotcore.subsystems.cube.CubeGripper;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;

@Autonomous(name="Main: TEST", group="Main")
public class Test extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        BaseRobot.initOpMode(this, hardwareMap, telemetry);
        RobotSubsystemManager robotSubsystemManager = RobotSubsystemManager.getInstance();
        Drive drive = (Drive) robotSubsystemManager.getSubsystem(Drive.class.getName());
        CubeElevator cubeElevator =
                (CubeElevator) robotSubsystemManager.getSubsystem(CubeElevator.class.getName());
        CubeGripper cubeGripper =
                (CubeGripper) robotSubsystemManager.getSubsystem(CubeGripper.class.getName());

        waitForStart();
        robotSubsystemManager.getGameClock().resetClock();
        drive.encoderDrive(1, 20, 20, 10);
    }
}
