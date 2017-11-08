package org.xbot.ftc.operatingcode.teleop;

import org.xbot.ftc.operatingcode.teleop.operator_1.TeleOpDrive;
import org.xbot.ftc.operatingcode.teleop.operator_2.TeleOpElevator;
import org.xbot.ftc.operatingcode.teleop.operator_2.TeleOpGlyphGripper;

public class XbotTeleOpSubHandlerRegister {

    public static void registerListeners() {
        XbotTeleOp.registerHandler(new TeleOpElevator());
        XbotTeleOp.registerHandler(new TeleOpDrive());
        XbotTeleOp.registerHandler(new TeleOpGlyphGripper());
    }
}
