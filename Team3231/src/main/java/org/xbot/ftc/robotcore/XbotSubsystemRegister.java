package org.xbot.ftc.robotcore;

import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.cube.CubeGripper;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.cube.CubeElevator;
import org.xbot.ftc.robotcore.subsystems.vision.PictographIdentifier;

public class XbotSubsystemRegister {

    public void registerListeners(RobotSubsystemManager robotSubsystemManager) {
        //robotSubsystemManager.registerSubsystem(JewelArm.getInstance());
        robotSubsystemManager.registerSubsystem(Drive.getInstance(),
                                                CubeElevator.getInstance(),
                                                CubeGripper.getInstance());
        //robotSubsystemManager.registerSubsystem(CubeElevator.getInstance());
        //robotSubsystemManager.registerSubsystem(CubeGripper.getInstance());
        //robotSubsystemManager.registerSubsystem(BoschIMU.getInstance());
        //robotSubsystemManager.registerSubsystem(PictographIdentifier.getInstance());

    }
}
