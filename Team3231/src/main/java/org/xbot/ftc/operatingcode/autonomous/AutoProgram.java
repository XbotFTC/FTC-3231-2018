package org.xbot.ftc.operatingcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.cube.CubeElevator;
import org.xbot.ftc.robotcore.subsystems.cube.CubeGripper;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;

@Autonomous(name="Main: Auto", group="Main")
@Disabled
public class AutoProgram extends LinearOpMode {

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

        while (opModeIsActive()) {
            cubeGripper.setMotorPower(1);
            Thread.sleep(300);
            cubeGripper.setMotorPower(0);
            cubeElevator.lift();
            Thread.sleep(400);
            cubeElevator.stop();
            drive.encoderDrive(1, 10, 10, 5);
            drive.encoderDrive(1, 5, -5, 2);
            drive.encoderDrive(1, 8, 8, 4);
            cubeElevator.down();
            Thread.sleep(200);
            cubeElevator.stop();
            cubeGripper.setMotorPower(-1);
            Thread.sleep(300);
            cubeElevator.setPower(0);
        }
    }
}
