package org.xbot.ftc.operatingcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.arm.JewelArm;

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

        JewelArm arm =
                (JewelArm) RobotSubsystemManager.getInstance().getSubsystem(JewelArm.class.getName());
        arm.setPosition(JewelArm.ArmPosition.UP);

        for (XbotOperatorSubHandler handler : handlers) {
            handler.start();
        }

        while (opModeIsActive()) {
            for (XbotOperatorSubHandler handler : handlers) {
                handler.handle(gamepad1, gamepad2);
                handler.updateTelemetry(telemetry);
            }
        }

        for (XbotOperatorSubHandler handler : handlers) {
            handler.stop();
        }
    }
}