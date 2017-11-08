package org.xbot.ftc.robotcore;

import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;
import org.xbot.ftc.robotcore.subsystems.arm.JewelArm;
import org.xbot.ftc.robotcore.subsystems.cube.CubeGripper;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.cube.CubeElevator;
import org.xbot.ftc.robotcore.subsystems.imu.BoschIMU;
import org.xbot.ftc.robotcore.subsystems.vision.PictographIdentifier;

import java.util.ArrayList;
import java.util.List;

public abstract class XbotSubsystemRegister {

    public static void registerListeners() {
        List<XbotSubsystem> subsystems = new ArrayList<>();
        //subsystems.add(JewelArm.getInstance());
        subsystems.add(Drive.getInstance());
        subsystems.add(CubeElevator.getInstance());
        subsystems.add(CubeGripper.getInstance());
        //subsystems.add(BoschIMU.getInstance());
        subsystems.add(PictographIdentifier.getInstance());

        RobotSubsystemManager robotSubsystemManager = RobotSubsystemManager.getInstance();
        for (XbotSubsystem subsystem : subsystems) {
            robotSubsystemManager.registerSubsystem(subsystem);
        }
    }
}
