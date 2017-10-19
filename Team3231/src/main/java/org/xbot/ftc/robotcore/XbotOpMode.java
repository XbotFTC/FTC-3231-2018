package org.xbot.ftc.robotcore;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class XbotOpMode extends OpMode {

    public ElapsedTime runtime = new ElapsedTime();

    ColorSensor sensorRGB;

    @Override
    public void start() {
        super.start();
        runtime.reset();
    }

    @Override
    public void init() {
        updateTelemetry("Status", "Initializing");

        sensorRGB = hardwareMap.colorSensor.get("sensor_color");
    }

    public void updateTelemetry(String caption, Object... value) {
        for (Object v : value) {
            telemetry.addData(caption, v);
        }

        telemetry.addData("Clear", sensorRGB.alpha());
        telemetry.addData("Red  ", sensorRGB.red());
        telemetry.addData("Green", sensorRGB.green());
        telemetry.addData("Blue ", sensorRGB.blue());

        telemetry.update();
    }
}
