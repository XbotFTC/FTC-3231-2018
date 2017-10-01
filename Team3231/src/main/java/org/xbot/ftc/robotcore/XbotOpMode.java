package org.xbot.ftc.robotcore;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class XbotOpMode extends OpMode {

    public ElapsedTime runtime = new ElapsedTime();

    @Override
    public void start() {
        super.start();
        runtime.reset();
    }

    @Override
    public void init() {
        updateTelemetry("Status", "Initializing");
    }

    public void updateTelemetry(String caption, Object... value) {
        for (Object v : value) {
            telemetry.addData(caption, v);
        }
        telemetry.update();
    }
}
