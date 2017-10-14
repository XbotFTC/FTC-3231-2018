package org.xbot.ftc.robotcore.data_systems.vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.xbot.ftc.R;

public class VuMarkIdentifier {

    private static VuMarkIdentifier instance = null;

    private VuforiaLocalizer vuforia;
    private VuforiaTrackables relicTrackables;
    private VuforiaTrackable relicTemplate;

    private VuMarkIdentifier() {
    }

    public void init(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap
                .appContext
                .getResources()
                .getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = hardwareMap.appContext.getString(R.string.vuforia_license_key);
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
    }

    public RelicRecoveryVuMark keepIdentifyingUntilVuMarkIsFound() {
        RelicRecoveryVuMark vuMark = whereDoesTheRobotPutThisBox();
        while (vuMark == RelicRecoveryVuMark.UNKNOWN) {
            vuMark = whereDoesTheRobotPutThisBox();
        }
        return vuMark;
    }

    public RelicRecoveryVuMark whereDoesTheRobotPutThisBox() {
        relicTrackables.activate();
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        relicTrackables.deactivate();
        return vuMark;
    }

    public synchronized static VuMarkIdentifier getInstance() {
        if (instance == null) {
            instance = new VuMarkIdentifier();
        }
        return instance;
    }
}
