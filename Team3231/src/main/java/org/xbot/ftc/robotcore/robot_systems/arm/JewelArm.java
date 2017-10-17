package org.xbot.ftc.robotcore.robot_systems.arm;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.xbot.ftc.robotcore.XbotRobotConstants;

public class JewelArm {

    private static JewelArm instance = null;

    private Servo jewelArmServo;
    private ArmState currentState;

    public enum ArmState {
        UP(0.0), DOWN(1.0), UNKNOWN(3231.488);

        private final double ARM_POSITION;
        ArmState(final double ARM_POSITION) { this.ARM_POSITION = ARM_POSITION; }
    }

    private JewelArm() {
    }

    public void init(HardwareMap hardwareMap) {
        jewelArmServo = hardwareMap.get(Servo.class, XbotRobotConstants.JEWEL_SMACKER_SERVO);
        jewelArmServo.setPosition(0);
        currentState = ArmState.UP;
    }

    public void toggleArm() {
        if (currentState == ArmState.UP) {
            setPosition(ArmState.DOWN.ARM_POSITION);
        } else {
            setPosition(ArmState.UP.ARM_POSITION);
        }
    }

    public void setPosition(double position) {
        jewelArmServo.setPosition(position);

        double currentArmPosition = jewelArmServo.getPosition();

        if (currentArmPosition == ArmState.UP.ARM_POSITION) {
            currentState = ArmState.UP;
        } else if (currentArmPosition == ArmState.DOWN.ARM_POSITION) {
            currentState = ArmState.DOWN;
        } else {
            currentState = ArmState.UNKNOWN;
        }
    }

    public Servo getJewelArmServo() {
        return jewelArmServo;
    }

    public synchronized static JewelArm getInstance() {
        if (instance == null) {
            instance = new JewelArm();
        }
        return instance;
    }
}
