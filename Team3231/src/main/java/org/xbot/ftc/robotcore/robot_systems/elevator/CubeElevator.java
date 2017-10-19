package org.xbot.ftc.robotcore.robot_systems.elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.xbot.ftc.robotcore.XbotRobotConstants;

public class CubeElevator {

    private static CubeElevator instance = null;
    private static boolean initialized = false;

    private DcMotor elevatorMotor = null;

    private CubeElevator() {
    }

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

    public synchronized static CubeElevator getInstance() {
        if (instance == null) {
            instance = new CubeElevator();
        }
        return instance;
    }
}
