package org.xbot.ftc.operatingcode.autonomous.jewel_smacker;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.vision.XbotColorSensor;
import org.xbot.ftc.robotcore.utils.GameClock;

@Autonomous(name="Jewel: Red", group="Test")
public class RedAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        BaseJewelAuto baseJewelAuto = new BaseJewelAuto(XbotColorSensor.Color.RED, this, hardwareMap, telemetry);
        waitForStart();
        RobotSubsystemManager.getInstance().getGameClock().resetClock();
        baseJewelAuto.run();
    }
}
