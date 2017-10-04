package org.xbot.ftc.operatingcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.xbot.ftc.robotcore.vision.VuMarkIdentifier;

@TeleOp(name = "VisionTesting", group = "Vision")
public class VisionTesting extends LinearOpMode {


    private VuMarkIdentifier vuMarkIdentifier;

    @Override
    public void runOpMode() {
        vuMarkIdentifier = new VuMarkIdentifier(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            RelicRecoveryVuMark umm = vuMarkIdentifier.whereDoesTheRobotPutThisBox();
            if (umm != RelicRecoveryVuMark.UNKNOWN) {
                telemetry.addData("Where To Put Thingy", umm);
                telemetry.update();
            }
        }
    }
}
