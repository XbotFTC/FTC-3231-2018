package org.xbot.ftc.robotcore.subsystems.cube;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;

public class CubeGripper extends XbotSubsystem {

    public static final String CLASS_NAME = CubeGripper.class.getName();
    private static CubeGripper instance = null;
    private static boolean initialized = false;

    private Servo leftServo;
    private Servo rightServo;

    private CubeGripper() {
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        if (initialized) return;
        leftServo = hardwareMap.get(Servo.class, XbotRobotConstants.GRIPPER_SERVO_LEFT);
        rightServo = hardwareMap.get(Servo.class, XbotRobotConstants.GRIPPER_SERVO_RIGHT);

        leftServo.setPosition(0);
        rightServo.setPosition(1);

        initialized = true;
    }

    public void setServoPositions(double leftServoPos, double rightServoPos) {
        leftServo.setPosition(leftServoPos);
        rightServo.setPosition(rightServoPos);
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
        return CLASS_NAME;
    }

    public static XbotSubsystem getInstance() {
        if (instance == null) {
            instance = new CubeGripper();
        }
        return instance;
    }
}
