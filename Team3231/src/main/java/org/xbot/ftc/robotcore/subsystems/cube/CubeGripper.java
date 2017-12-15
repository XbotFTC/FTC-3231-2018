package org.xbot.ftc.robotcore.subsystems.cube;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;

public class CubeGripper extends XbotSubsystem {

    private static XbotSubsystem instance = null;
    private static boolean initialized = false;

    private DcMotor gripperMotor;

    private CubeGripper() {
    }

    @Override
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;
        gripperMotor = hardwareMap.get(DcMotor.class, XbotRobotConstants.GRIPPER_MOTOR);
        initialized = true;
    }

    public void setMotorPower(double power) {
        gripperMotor.setPower(power);
    }

    @Override
    public String getClassName() {
        return CubeGripper.class.getName();
    }

    public static XbotSubsystem getInstance() {
        if (instance == null)
            instance = new CubeGripper();
        return instance;
    }

    @Override
    public void shutdownSubystem() {
        gripperMotor.setPower(0);
        initialized = false;
    }
}
