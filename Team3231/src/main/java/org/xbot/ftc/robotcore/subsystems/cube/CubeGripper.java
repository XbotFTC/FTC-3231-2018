package org.xbot.ftc.robotcore.subsystems.cube;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;

public class CubeGripper extends XbotSubsystem {

    private static XbotSubsystem instance = null;
    private static boolean initialized = false;

    private Servo leftServo;
    private Servo rightServo;

    private CubeGripper() {
    }

    @Override
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;
        leftServo = hardwareMap.get(Servo.class, XbotRobotConstants.GRIPPER_SERVO_LEFT);
        rightServo = hardwareMap.get(Servo.class, XbotRobotConstants.GRIPPER_SERVO_RIGHT);

        leftServo.setDirection(Servo.Direction.REVERSE);
        rightServo.setDirection(Servo.Direction.FORWARD);

        setServoPositions(0, 0);

        initialized = true;
    }

    public void grip() {
        setServoPositions(1, 1);
    }

    public void letItGo() {
        setServoPositions(0, 0);
    }

    public void setServoPositions(double leftServoPos, double rightServoPos) {
        leftServo.setPosition(leftServoPos);
        rightServo.setPosition(rightServoPos);
    }

    public void setServoPositions(double power) {
        setServoPositions(power, power);
    }

    public void setLeftServoPosition(double position) {
        leftServo.setPosition(position);
    }

    public void setRightServoPosition(double position) {
        rightServo.setPosition(position);
    }

    public double getLeftServoPosition() {
        return leftServo.getPosition();
    }

    public double getRightServoPosition() {
        return rightServo.getPosition();
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
}
