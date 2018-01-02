package org.xbot.ftc.robotcore.subsystems.arm;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;

public class JewelArm extends XbotSubsystem {

    private static XbotSubsystem instance = null;
    private static boolean initialized = false;

    private Servo jewelArmServo;

    public enum ArmPosition {
        UP(-1.0), DOWN(1.0);

        private final double ARM_POSITION;
        ArmPosition(final double ARM_POSITION) { this.ARM_POSITION = ARM_POSITION; }
    }

    private JewelArm() {
    }

    @Override
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;
        jewelArmServo = hardwareMap.get(Servo.class, XbotRobotConstants.JEWEL_SMACKER_SERVO);
        jewelArmServo.setDirection(Servo.Direction.FORWARD);
        setPosition(ArmPosition.UP);
        initialized = true;
    }

    public void setPosition(double position) {
        jewelArmServo.setPosition(position);
    }

    public void setPosition(ArmPosition position) {
        setPosition(position.ARM_POSITION);
    }

    public Servo getJewelArmServo() {
        return jewelArmServo;
    }

    public ArmPosition getArmPosition() {
        return jewelArmServo.getPosition() > 0.0 && jewelArmServo.getPosition() <= 1.0
                ? ArmPosition.DOWN
                : ArmPosition.UP;
    }

    @Override
    public String getClassName() {
        return JewelArm.class.getName();
    }

    public static XbotSubsystem getInstance() {
        if (instance == null)
            instance = new JewelArm();
        return instance;
    }

    @Override
    public void shutdownSubystem() {
        setPosition(ArmPosition.UP);
        initialized = false;
    }
}
