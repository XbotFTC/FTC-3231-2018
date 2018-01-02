package org.xbot.ftc.operatingcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.operatingcode.autonomous.jewel_smacker.BaseJewelAuto;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.cube.CubeElevator;
import org.xbot.ftc.robotcore.subsystems.cube.CubeGripper;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.imu.BoschIMU;
import org.xbot.ftc.robotcore.subsystems.vision.XbotColorSensor;

@Autonomous(name="Experimental: Jewel and Rotate", group="Testing")
@Disabled
public class JewelPlusRotateAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        BaseRobot.initOpMode(this, hardwareMap, telemetry);
        RobotSubsystemManager robotSubsystemManager = RobotSubsystemManager.getInstance();
        BaseJewelAuto baseJewelAuto = new BaseJewelAuto(XbotColorSensor.Color.RED, this, hardwareMap, telemetry);
        Drive drive = (Drive) robotSubsystemManager.getSubsystem(Drive.class.getName());
        CubeElevator cubeElevator =
                (CubeElevator) robotSubsystemManager.getSubsystem(CubeElevator.class.getName());
        CubeGripper cubeGripper =
                (CubeGripper) robotSubsystemManager.getSubsystem(CubeGripper.class.getName());
        BoschIMU imu = (BoschIMU) robotSubsystemManager.getSubsystem(BoschIMU.class.getName());
        imu.enableImu();
        waitForStart();

        robotSubsystemManager.getGameClock().resetClock();

        baseJewelAuto.run();

        double heading = Double.parseDouble(imu.getHeading());
        telemetry.addData("Heading: ", heading);
        telemetry.update();
        while (heading < (90 - 5) && heading > (90 + 5) && opModeIsActive()) {
            heading = Double.parseDouble(imu.getHeading());
            if (heading < 90) {
                drive.turn(Drive.TurnDirection.RIGHT, Drive.DrivePower.HALF);
            } else {
                drive.turn(Drive.TurnDirection.LEFT, Drive.DrivePower.HALF);
            }
        }
        RobotSubsystemManager.getInstance().stop();
    }
}
