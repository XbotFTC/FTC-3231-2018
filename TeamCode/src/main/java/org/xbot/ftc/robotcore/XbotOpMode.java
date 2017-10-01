package org.xbot.ftc.robotcore;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class XbotOpMode extends LinearOpMode implements XbotOpModeMethods {

    @Override
    public void xbotInit() {
        xbotUpdateTelemetry("Status", "Initializing");
    }

    public void xbotUpdateTelemetry(String caption, Object... value) {
        for (Object v : value) {
            telemetry.addData(caption, v);
        }
        telemetry.update();
    }
}
