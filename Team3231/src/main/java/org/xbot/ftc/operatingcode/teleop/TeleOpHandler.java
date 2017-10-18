package org.xbot.ftc.operatingcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.xbot.ftc.operatingcode.TeleOpRegister;
import org.xbot.ftc.robotcore.XbotOpModeListener;

import java.util.ArrayList;
import java.util.List;


@TeleOp(name="Main: TeleOp", group="Main")
public class TeleOpHandler extends LinearOpMode {

    private static List<XbotOpModeListener> listeners = new ArrayList<>();

    public static void registerListener(XbotOpModeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Listeners:", "Registering");
        TeleOpRegister.registerListeners();
        telemetry.addData("Listeners:", "Registered");

        waitForStart();

        for (XbotOpModeListener listener : listeners) {
            listener.start(hardwareMap, telemetry);
        }

        while (opModeIsActive()) {
            for (XbotOpModeListener listener : listeners) {
                listener.handle(gamepad1, gamepad2);
            }
        }

        for (XbotOpModeListener listener : listeners) {
            listener.stop();
        }
    }
}
