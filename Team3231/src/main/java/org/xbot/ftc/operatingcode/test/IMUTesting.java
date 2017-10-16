package org.xbot.ftc.operatingcode.test;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.xbot.ftc.robotcore.XbotOpMode;
import org.xbot.ftc.robotcore.data_systems.imu.BoschIMU;

@TeleOp(name="IMUTesting", group="Testing")
public class IMUTesting extends XbotOpMode {

    private BoschIMU imu;

    @Override
    public void init() {
        super.init();
        imu = BoschIMU.getInstance();
        imu.init(hardwareMap);
    }

    @Override
    public void loop() {
        updateTelemetry("Pitch", imu.getPitch());
    }
}
