package org.xbot.ftc.robotcore;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.xbot.ftc.robotcore.data_systems.imu.BoschIMU;
import org.xbot.ftc.robotcore.data_systems.vision.VuMarkIdentifier;
import org.xbot.ftc.robotcore.robot_systems.arm.JewelArm;
import org.xbot.ftc.robotcore.robot_systems.drive.Drive;
import org.xbot.ftc.robotcore.robot_systems.elevator.CubeElevator;

public class RobotSystemsManager {

    private static RobotSystemsManager instance = null;
    private static boolean initalized = false;

    private BoschIMU boschIMU;
    private VuMarkIdentifier vuMarkIdentifier;
    private Drive drive;
    private JewelArm jewelArm;
    private CubeElevator cubeElevator;

    private RobotSystemsManager() {
    }

    public void init(HardwareMap hardwareMap) {
        if (initalized) return;
        boschIMU = BoschIMU.getInstance();
        boschIMU.init(hardwareMap);

        vuMarkIdentifier = VuMarkIdentifier.getInstance();
        vuMarkIdentifier.init(hardwareMap);

        drive = Drive.getInstance();
        drive.init(hardwareMap);

        jewelArm = JewelArm.getInstance();
        jewelArm.init(hardwareMap);

        cubeElevator = CubeElevator.getInstance();
        cubeElevator.init(hardwareMap);

        initalized = true;
    }

    public BoschIMU getBoschIMU() {
        return boschIMU;
    }

    public VuMarkIdentifier getVuMarkIdentifier() {
        return vuMarkIdentifier;
    }

    public Drive getDrive() {
        return drive;
    }

    public JewelArm getJewelArm() {
        return jewelArm;
    }

    public CubeElevator getCubeElevator() {
        return cubeElevator;
    }

    public synchronized static RobotSystemsManager getInstance() {
        if (instance == null) {
            instance = new RobotSystemsManager();
        }
        return instance;
    }
}
