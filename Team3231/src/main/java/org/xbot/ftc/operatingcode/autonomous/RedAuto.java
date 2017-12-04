package org.xbot.ftc.operatingcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.xbot.ftc.robotcore.subsystems.arm.JewelArm;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.vision.XbotColorSensor;

@Autonomous(name="Red", group="Auto")
public class RedAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        BaseJewelAuto baseJewelAuto = new BaseJewelAuto(BaseJewelAuto.AllianceColor.RED, this, hardwareMap, telemetry);

        waitForStart();

        baseJewelAuto.getJewelArm().setPosition(JewelArm.ArmPosition.DOWN);
        Thread.sleep(1500);
        XbotColorSensor.Color color = baseJewelAuto.keepDetecting(baseJewelAuto.getXbotColorSensor());
        telemetry.addData("Color: ", color);
        telemetry.update();
        if (color == XbotColorSensor.Color.BLUE) {
            baseJewelAuto.getDrive().turn(Drive.TurnDirection.RIGHT, 0.5);
        } else {
            baseJewelAuto.getDrive().turn(Drive.TurnDirection.LEFT, 0.5);
        }

        Thread.sleep(700);

        baseJewelAuto.getDrive().stop();
        baseJewelAuto.getJewelArm().setPosition(JewelArm.ArmPosition.UP);
        Thread.sleep(1000);
    }
}
