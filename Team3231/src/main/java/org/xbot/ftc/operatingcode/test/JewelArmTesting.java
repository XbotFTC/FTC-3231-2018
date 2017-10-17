package org.xbot.ftc.operatingcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.xbot.ftc.robotcore.robot_systems.arm.JewelArm;

@TeleOp(name="Testing: JewelArm", group="Testing")
public class JewelArmTesting extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        JewelArm arm = JewelArm.getInstance();
        arm.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                arm.toggleArm();
            }
        }
    }
}
