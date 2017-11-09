package org.xbot.ftc.operatingcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="Main: TeleOp", group="Main")
public class XbotTeleOp extends LinearOpMode {

    private List<XbotOperatorSubHandler> handlers = new ArrayList<>();

    public void registerHandler(XbotOperatorSubHandler listener) {
        handlers.add(listener);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Listeners:", "Registering");
        telemetry.update();
        BaseRobot.initOpMode(this, hardwareMap, telemetry);
        new XbotTeleOpSubHandlerRegister().registerListeners(this);
        telemetry.addData("Listeners:", "Registered");
        telemetry.update();

        waitForStart();

        RobotSubsystemManager.getInstance().getGameClock().resetClock();

        for (XbotOperatorSubHandler listener : handlers) {
            listener.start();
        }

        while (opModeIsActive()) {
            for (XbotOperatorSubHandler listener : handlers) {
                listener.handle(gamepad1, gamepad2);
                listener.updateTelemetry(telemetry);
            }
        }

        for (XbotOperatorSubHandler listener : handlers) {
            listener.stop();
        }
    }
}
