package org.xbot.ftc.operatingcode.autonomous.jewel_smacker;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.xbot.ftc.robotcore.subsystems.arm.JewelArm;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.vision.XbotColorSensor;

@Autonomous(name="Blue", group="Jewel")
public class BlueAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        BaseJewelAuto baseJewelAuto = new BaseJewelAuto(XbotColorSensor.Color.BLUE, this, hardwareMap, telemetry);
        waitForStart();
        baseJewelAuto.run();
    }
}
