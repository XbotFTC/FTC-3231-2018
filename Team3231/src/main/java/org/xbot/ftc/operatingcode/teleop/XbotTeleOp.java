package org.xbot.ftc.operatingcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;

import java.util.ArrayList;
import java.util.List;


@TeleOp(name="Main: TeleOp", group="Main")
public class XbotTeleOp extends LinearOpMode {

    private static List<XbotTeleOpHandler> listeners = new ArrayList<>();

    public static void registerListener(XbotTeleOpHandler listener) {
        listeners.add(listener);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Listeners:", "Registering");
        telemetry.update();
        RobotSubsystemManager.getInstance().init(hardwareMap);
        XbotTeleOpSubHandlerRegister.registerListeners();
        telemetry.addData("Listeners:", "Registered");
        telemetry.update();

        waitForStart();

        for (XbotTeleOpHandler listener : listeners) {
            listener.start(hardwareMap, telemetry);
        }

        while (opModeIsActive()) {
            for (XbotTeleOpHandler listener : listeners) {
                listener.handle(gamepad1, gamepad2);
                listener.updateTelemetry();
            }
        }

        for (XbotTeleOpHandler listener : listeners) {
            listener.stop();
        }
    }
}
