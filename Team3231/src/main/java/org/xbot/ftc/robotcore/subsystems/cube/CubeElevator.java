package org.xbot.ftc.robotcore.subsystems.cube;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;

public class CubeElevator extends XbotSubsystem {

    private static boolean initialized = false;

    private DcMotor elevatorMotor = null;

    private CubeElevator() {
    }

    @Override
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;
        elevatorMotor = hardwareMap.get(DcMotor.class, XbotRobotConstants.ELEVATOR_MOTOR);

        initialized = true;
    }

    /**
     * PLEASE USE THIS METHOD WITH CAUTION TO PREVENT STRIPPING THE GEAR
     */
    public void lift() {
        setPower(1);
    }

    /**
     * PLEASE USE THIS METHOD WITH CAUTION TO PREVENT STRIPPING THE GEAR
     */
    public void down() {
        setPower(-1);
    }

    public void setPower(double power) {
        elevatorMotor.setPower(power);
    }

    public void stop() {
        elevatorMotor.setPower(0);
    }

    @Override
    public String getClassName() {
        return CubeElevator.class.getName();
    }

    public static XbotSubsystem getInstance() {
        if (instance == null)
            instance = new CubeElevator();
        return instance;
    }
}
