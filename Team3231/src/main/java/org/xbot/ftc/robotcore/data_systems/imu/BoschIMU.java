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

    private HardwareMap hardwareMap;

    private BNO055IMU imu;
    private Orientation angles;
    private Acceleration gravity;

    public BoschIMU(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        init();
    }

    private void init() {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "BoschIMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, XbotRobotConstants.BOSCH_IMU);
        imu.initialize(parameters);
    }

    public void updateData() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX,
                AngleUnit.DEGREES);
        gravity = imu.getGravity();
    }

    public String getHeading() {
        return formatAngle(angles.angleUnit, angles.firstAngle);
    }

    public String getRoll() {
        return formatAngle(angles.angleUnit, angles.secondAngle);
    }

    public String getPitch() {
        return formatAngle(angles.angleUnit, angles.thirdAngle);
    }

    public String getGravity() {
        return gravity.toString();
    }

    private String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    private String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}
