package org.xbot.ftc.robotcore.subsystems.imu;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class BoschIMU extends XbotSubsystem {

    public static final String CLASS_NAME = BoschIMU.class.getName();
    private static boolean initialized = false;

    private boolean imuEnabled = false;
    private BoschIMUUpdater imuUpdater;

    private BoschIMU() {
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        if (initialized) return;
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "BoschIMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, XbotRobotConstants.BOSCH_IMU);
        imu.initialize(parameters);

        imuUpdater = BoschIMUUpdater.getInstance();
        imuUpdater.init(imu);

        initialized = true;
    }

    public void enableImu() {
        if (!imuEnabled) {
            imuUpdater.enable();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        imuEnabled = true;
    }

    public void disableImu() {
        if (imuEnabled) {
            imuUpdater.disable();
        }
        imuEnabled = false;
    }

    public String getHeading() {
        return formatAngle(imuUpdater.getAngles().angleUnit, imuUpdater.getAngles().firstAngle);
    }

    public String getRoll() {
        return formatAngle(imuUpdater.getAngles().angleUnit, imuUpdater.getAngles().secondAngle);
    }

    public String getPitch() {
        return formatAngle(imuUpdater.getAngles().angleUnit, imuUpdater.getAngles().thirdAngle);
    }

    public String getGravity() {
        return imuUpdater.getGravity().toString();
    }

    private String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    private String formatDegrees(double degrees) {
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    public static XbotSubsystem getInstance() {
        if (instance == null) {
            instance = new BoschIMU();
        }
        return instance;
    }
}

class BoschIMUUpdater implements Runnable {

    private static BoschIMUUpdater instance = null;
    private static boolean initialized = false;

    private ThreadPoolExecutor executor;
    private static boolean running = false;

    private BNO055IMU imu;

    private Orientation angles;
    private Acceleration gravity;

    private BoschIMUUpdater() {
    }

    public void init(BNO055IMU imu) {
        if (initialized) return;
        this.imu = imu;
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        initialized = true;
    }

    public void enable() {
        running = true;
        executor.execute(this);
    }

    public void disable() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC,
                    AxesOrder.ZYX,
                    AngleUnit.DEGREES);
            gravity = imu.getGravity();
        }
    }

    public synchronized Orientation getAngles() {
        return angles;
    }

    public synchronized Acceleration getGravity() {
        return gravity;
    }

    public static BoschIMUUpdater getInstance() {
        if (instance == null) {
            instance = new BoschIMUUpdater();
        }
        return instance;
    }
}
