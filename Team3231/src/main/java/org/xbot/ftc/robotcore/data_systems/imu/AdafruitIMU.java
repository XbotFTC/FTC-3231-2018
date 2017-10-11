package org.xbot.ftc.robotcore.data_systems.imu;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.xbot.ftc.robotcore.XbotRobotConstants;

import java.util.Locale;

public class AdafruitIMU {

    private HardwareMap hardwareMap;

    private boolean runIMU;

    private BNO055IMU imu;
    private Orientation angles;
    private Acceleration gravity;

    public AdafruitIMU(HardwareMap hardwareMap, boolean runIMU) {
        this.hardwareMap = hardwareMap;
        this.runIMU = runIMU;
        init();
    }

    public AdafruitIMU(HardwareMap hardwareMap) {
        this(hardwareMap, false);
    }

    private void init() {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "AdafruitIMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, XbotRobotConstants.ADAFRUIT_IMU);
        imu.initialize(parameters);

        updateData();
    }

    public void enableIMU() {
        this.runIMU = true;
    }

    public void disableIMU() {
        this.runIMU = false;
    }

    private void updateData() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX,
                AngleUnit.DEGREES);
        gravity = imu.getGravity();
    }

    public String getHeading() {
        updateData();
        return formatAngle(angles.angleUnit, angles.firstAngle);
    }

    public String getRoll() {
        updateData();
        return formatAngle(angles.angleUnit, angles.secondAngle);
    }

    public String getPitch() {
        updateData();
        return formatAngle(angles.angleUnit, angles.thirdAngle);
    }

    public String getGravity() {
        updateData();
        return gravity.toString();
    }

    private String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    private String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}
