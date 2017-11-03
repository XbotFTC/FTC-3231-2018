package org.xbot.ftc.robotcore.subsystems.cube;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;

public class CubeElevator extends XbotSubsystem {

    public static final String CLASS_NAME = CubeElevator.class.getName();
    private static CubeElevator instance = null;
    private static boolean initialized = false;

    private DcMotor elevatorMotor = null;

    private CubeElevator() {
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        if (initialized) return;
        elevatorMotor = hardwareMap.get(DcMotor.class, XbotRobotConstants.ELEVATOR_MOTOR);

        initialized = true;
    }

    public void setPower(double power) {
        elevatorMotor.setPower(power);
    }

    public void stop() {
        elevatorMotor.setPower(0);
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    public static XbotSubsystem getInstance() {
        if (instance == null) {
            instance = new CubeElevator();
        }
        return instance;
    }
}
