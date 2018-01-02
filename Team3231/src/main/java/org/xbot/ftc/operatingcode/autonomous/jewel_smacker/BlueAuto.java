package org.xbot.ftc.operatingcode.autonomous.jewel_smacker;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.vision.XbotColorSensor;
import org.xbot.ftc.robotcore.utils.GameClock;

@Autonomous(name="Jewel: Blue", group="Test")
public class BlueAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        BaseJewelAuto baseJewelAuto = new BaseJewelAuto(XbotColorSensor.Color.BLUE, this, hardwareMap, telemetry);
        waitForStart();
        RobotSubsystemManager.getInstance().getGameClock().resetClock();
        baseJewelAuto.run();
    }
}
