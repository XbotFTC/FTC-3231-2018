package org.xbot.ftc.robotcore.drive;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class TankDrive extends DriveManager {

    public TankDrive(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public void drive(double leftPower, double rightPower) {
        super.setMotorPowers(leftPower, rightPower);
    }
}
