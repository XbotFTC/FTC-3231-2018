package org.xbot.ftc.robotcore;

import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.arm.JewelArm;
import org.xbot.ftc.robotcore.subsystems.cube.CubeGripper;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.cube.CubeElevator;
import org.xbot.ftc.robotcore.subsystems.vision.PictographIdentifier;
import org.xbot.ftc.robotcore.subsystems.vision.XbotColorSensor;

public class XbotSubsystemRegister {

    public void registerListeners(RobotSubsystemManager robotSubsystemManager) {
        robotSubsystemManager.registerSubsystem(Drive.getInstance(),
                CubeElevator.getInstance(),
                CubeGripper.getInstance(),
                XbotColorSensor.getInstance(),
                PictographIdentifier.getInstance(),
                JewelArm.getInstance());

    }
}
