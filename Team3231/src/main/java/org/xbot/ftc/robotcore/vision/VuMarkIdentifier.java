package org.xbot.ftc.robotcore.vision;


import android.content.res.Resources;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.xbot.ftc.R;

public class VuMarkIdentifier {

    private HardwareMap hardwareMap;

    private VuforiaLocalizer vuforia;
    private VuforiaTrackables relicTrackables;
    private VuforiaTrackable relicTemplate;

    public VuMarkIdentifier(HardwareMap hardwareMap,
                            String vuforiaLicenseKey,
                            VuforiaLocalizer.CameraDirection cameraDirection) {
        this.hardwareMap = hardwareMap;
        init(vuforiaLicenseKey, cameraDirection);
    }

    public VuMarkIdentifier(HardwareMap hardwareMap,
                            VuforiaLocalizer.CameraDirection cameraDirection) {
        this(hardwareMap,
                Resources.getSystem().getString(R.string.vuforia_license_key),
                cameraDirection);
    }

    public VuMarkIdentifier(HardwareMap hardwareMap) {
        this(hardwareMap,
                Resources.getSystem().getString(R.string.vuforia_license_key),
                VuforiaLocalizer.CameraDirection.BACK);
    }

    private void init(String vuforiaLicenseKey, VuforiaLocalizer.CameraDirection cameraDirection) {
        int cameraMonitorViewId = hardwareMap
                .appContext
                .getResources()
                .getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = vuforiaLicenseKey;
        parameters.cameraDirection = cameraDirection;

        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
    }

    public RelicRecoveryVuMark keepIdentifyingUntilVuMarkIsFound() {
        RelicRecoveryVuMark vuMark = whereDoesTheRobotPutThisBox();
        while (vuMark == null) {
            vuMark = whereDoesTheRobotPutThisBox();
        }
        return vuMark;
    }

    public RelicRecoveryVuMark whereDoesTheRobotPutThisBox() {
        return RelicRecoveryVuMark.from(relicTemplate);
    }
}
