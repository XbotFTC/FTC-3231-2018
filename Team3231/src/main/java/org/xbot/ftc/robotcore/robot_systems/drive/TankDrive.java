package org.xbot.ftc.robotcore.robot_systems.drive;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class TankDrive {

    private DriveManager driveManager;

    protected TankDrive(HardwareMap hardwareMap) {
        driveManager = DriveManager.getInstance();
        driveManager.init(hardwareMap);
    }

    public void drive(double leftPower, double rightPower) {
        driveManager.setMotorPowers(leftPower, rightPower);
    }

    public void stop() {
        driveManager.setMotorPowers(0);
    }
}
