package org.xbot.ftc.operatingcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.cube.CubeElevator;
import org.xbot.ftc.robotcore.subsystems.cube.CubeGripper;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;

@Autonomous(name="Main: Auto", group="Main")
public class AutoProgram extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        RobotSubsystemManager robotSubsystemManager = RobotSubsystemManager.getInstance();
        robotSubsystemManager.init(hardwareMap);

        Drive drive = (Drive) robotSubsystemManager.getSubsystem(Drive.CLASS_NAME);
        CubeElevator cubeElevator =
                (CubeElevator) robotSubsystemManager.getSubsystem(CubeElevator.CLASS_NAME);
        CubeGripper cubeGripper =
                (CubeGripper) robotSubsystemManager.getSubsystem(CubeGripper.CLASS_NAME);

        waitForStart();

        cubeGripper.setServoPositions(1, 1);
        Thread.sleep(300);
        cubeElevator.setPower(-1);
        Thread.sleep(200);
        drive.setMotorPowers(-1);
        Thread.sleep(700);
        drive.setMotorPowers(-1, 1);
        Thread.sleep(500);
        drive.setMotorPowers(-1);
        Thread.sleep(100);
        cubeGripper.setServoPositions(0, 0);
        Thread.sleep(100);
    }
}
