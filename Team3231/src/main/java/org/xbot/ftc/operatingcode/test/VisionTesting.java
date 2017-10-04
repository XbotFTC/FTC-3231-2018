package org.xbot.ftc.operatingcode.test;

import android.content.Context;
import android.widget.Toast;

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
            RelicRecoveryVuMark umm = vuMarkIdentifier.keepIdentifyingUntilVuMarkIsFound();
            Context context = hardwareMap.appContext;
            Toast.makeText(context, "Where To Place The Thing:" + umm, Toast.LENGTH_SHORT).show();
            telemetry.addData("Where To Put Thingy", umm);
            telemetry.update();
        }
    }
}
