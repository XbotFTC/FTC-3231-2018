package org.xbot.ftc.operatingcode.test;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.xbot.ftc.robotcore.data_systems.imu.BoschIMU;

@TeleOp(name="Testing: IMU", group="Testing")
public class IMUTesting extends OpMode {

    private BoschIMU imu;

    @Override
    public void init() {
        imu = BoschIMU.getInstance();
        imu.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Pitch", imu.getPitch());
        telemetry.update();
    }
}
