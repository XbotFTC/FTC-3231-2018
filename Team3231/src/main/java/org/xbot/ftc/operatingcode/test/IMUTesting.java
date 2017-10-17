package org.xbot.ftc.operatingcode.test;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.xbot.ftc.robotcore.XbotOpMode;
import org.xbot.ftc.robotcore.data_systems.imu.BoschIMU;

@TeleOp(name="Testing: IMU", group="Testing")
public class IMUTesting extends XbotOpMode {

    private BoschIMU imu;

    @Override
    public void init() {
        super.init();
        imu = new BoschIMU(hardwareMap);
    }

    @Override
    public void loop() {
        imu.updateData();
        updateTelemetry("Pitch", imu.getPitch());
    }
}
