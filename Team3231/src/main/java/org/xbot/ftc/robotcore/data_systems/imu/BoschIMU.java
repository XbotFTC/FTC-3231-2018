package org.xbot.ftc.robotcore.data_systems.imu;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.xbot.ftc.robotcore.XbotRobotConstants;

import java.util.Locale;

public class BoschIMU {

    private static BoschIMU instance = null;

    private static boolean initialized = false;

    private boolean imuEnabled = false;
    private BoschIMUThread imuThread;

    private BoschIMU() {
    }

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

        imuThread = BoschIMUThread.getInstance();
        imuThread.init(imu);

        initialized = true;
    }

    public void enableImu() {
        imuEnabled = true;
        imuThread.enableThread();
    }

    public void disableImu() {
        imuEnabled = false;
        imuThread.disableThread();
    }

    public String getHeading() {
        return formatAngle(imuThread.getAngles().angleUnit, imuThread.getAngles().firstAngle);
    }

    public String getRoll() {
        return formatAngle(imuThread.getAngles().angleUnit, imuThread.getAngles().secondAngle);
    }

    public String getPitch() {
        return formatAngle(imuThread.getAngles().angleUnit, imuThread.getAngles().thirdAngle);
    }

    public String getGravity() {
        return imuThread.getGravity().toString();
    }

    private String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    private String formatDegrees(double degrees) {
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }

    public synchronized static BoschIMU getInstance() {
        if (instance == null) {
            instance = new BoschIMU();
        }
        return instance;
    }
}

class BoschIMUThread extends Thread {

    private static BoschIMUThread instance = null;

    private static boolean initialized = false;

    private BNO055IMU imu;

    private boolean threadEnabled;

    private Orientation angles;
    private Acceleration gravity;

    private BoschIMUThread() {
    }

    public void init(BNO055IMU imu) {
        if (initialized) return;
        this.imu = imu;
        threadEnabled = false;
        initialized = true;
    }

    public void enableThread() {
        boolean previousState = threadEnabled;
        threadEnabled = true;
        if (previousState == false) run();
    }

    public void disableThread() {
        threadEnabled = false;
    }

    @Override
    public void run() {
        super.run();

        new Thread(new Runnable() {
            public void run() {
                while (threadEnabled) {
                    angles = imu.getAngularOrientation(AxesReference.INTRINSIC,
                            AxesOrder.ZYX,
                            AngleUnit.DEGREES);
                    gravity = imu.getGravity();
                }
            }
        }).start();
    }

    public Orientation getAngles() {
        return angles;
    }

    public Acceleration getGravity() {
        return gravity;
    }

    public synchronized static BoschIMUThread getInstance() {
        if (instance == null) {
            instance = new BoschIMUThread();
        }
        return instance;
    }
}
