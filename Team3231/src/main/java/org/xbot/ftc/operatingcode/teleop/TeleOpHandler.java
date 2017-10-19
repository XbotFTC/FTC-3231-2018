package org.xbot.ftc.operatingcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.xbot.ftc.robotcore.XbotTeleOpListener;

import java.util.ArrayList;
import java.util.List;


@TeleOp(name="Main: TeleOp", group="Main")
public class TeleOpHandler extends LinearOpMode {

    private static List<XbotTeleOpListener> listeners = new ArrayList<>();

    public static void registerListener(XbotTeleOpListener listener) {
        listeners.add(listener);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Listeners:", "Registering");
        TeleOpSubHandlerRegister.registerListeners();
        telemetry.addData("Listeners:", "Registered");

        waitForStart();

        for (XbotTeleOpListener listener : listeners) {
            listener.start(hardwareMap, telemetry);
        }

        while (opModeIsActive()) {
            for (XbotTeleOpListener listener : listeners) {
                listener.handle(gamepad1, gamepad2);
                listener.updateTelemetry();
            }
        }

        for (XbotTeleOpListener listener : listeners) {
            listener.stop();
        }
    }
}
