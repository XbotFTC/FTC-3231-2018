package org.xbot.ftc.operatingcode.autonomous.jewel_smacker;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.xbot.ftc.robotcore.subsystems.arm.JewelArm;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.vision.XbotColorSensor;

@Autonomous(name="Red", group="Jewel")
public class RedAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        BaseJewelAuto baseJewelAuto = new BaseJewelAuto(XbotColorSensor.Color.RED, this, hardwareMap, telemetry);
        waitForStart();
        baseJewelAuto.run();
    }
}
