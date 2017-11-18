package org.xbot.ftc.robotcore;

import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.cube.CubeGripper;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.cube.CubeElevator;
import org.xbot.ftc.robotcore.subsystems.vision.PictographIdentifier;

public class XbotSubsystemRegister {

    public static void registerListeners() {
        //robotSubsystemManager.registerSubsystem(JewelArm.getInstance());
        RobotSubsystemManager.registerSubsystem(Drive.getInstance());
        RobotSubsystemManager.registerSubsystem(CubeElevator.getInstance());
        RobotSubsystemManager.registerSubsystem(CubeGripper.getInstance());
        //robotSubsystemManager.registerSubsystem(BoschIMU.getInstance());
        //robotSubsystemManager.registerSubsystem(PictographIdentifier.getInstance());

    }
}
