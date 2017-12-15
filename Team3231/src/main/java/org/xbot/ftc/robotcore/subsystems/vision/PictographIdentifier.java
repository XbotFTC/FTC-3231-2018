package org.xbot.ftc.robotcore.subsystems.vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.xbot.ftc.R;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;

public class PictographIdentifier extends XbotSubsystem {

    private static XbotSubsystem instance = null;
    private static boolean initialized = false;

    private VuforiaTrackables relicTrackables;
    private VuforiaTrackable relicTemplate;

    private PictographIdentifier() {
    }

    @Override
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;
        int cameraMonitorViewId = hardwareMap
                .appContext
                .getResources()
                .getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = hardwareMap.appContext.getString(R.string.vuforia_license_key);
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        initialized = true;
    }

    public RelicRecoveryVuMark keepIdentifyingUntilVuMarkIsFound() {
        RelicRecoveryVuMark vuMark = whereDoesTheRobotPutThisBox();
        while (vuMark == RelicRecoveryVuMark.UNKNOWN)
            vuMark = whereDoesTheRobotPutThisBox();
        return vuMark;
    }

    public RelicRecoveryVuMark whereDoesTheRobotPutThisBox() {
        relicTrackables.activate();
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        relicTrackables.deactivate();
        return vuMark;
    }

    @Override
    public void shutdownSubystem() {
        initialized = false;
    }

    @Override
    public String getClassName() {
        return PictographIdentifier.class.getName();
    }

    public static XbotSubsystem getInstance() {
        if (instance == null)
            instance = new PictographIdentifier();
        return instance;
    }
}
