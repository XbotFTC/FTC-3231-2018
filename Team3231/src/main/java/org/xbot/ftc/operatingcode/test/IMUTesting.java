package org.xbot.ftc.operatingcode.test;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.xbot.ftc.robotcore.XbotOpMode;
import org.xbot.ftc.robotcore.data_systems.imu.AdafruitIMU;

@TeleOp(name="IMUTesting", group="Testing")
public class IMUTesting extends XbotOpMode {

    private AdafruitIMU imu;

    @Override
    public void init() {
        super.init();
        imu = AdafruitIMU.getInstance();
        imu.init(hardwareMap);
    }

    @Override
    public void loop() {
        updateTelemetry("Pitch", imu.getPitch());
    }
}
