package org.xbot.ftc.operatingcode.teleop;

import org.xbot.ftc.operatingcode.teleop.operator_1.TeleOpDrive;
import org.xbot.ftc.operatingcode.teleop.operator_1.TeleOpJewelSmacker;
import org.xbot.ftc.operatingcode.teleop.operator_2.TeleOpElevator;
import org.xbot.ftc.operatingcode.teleop.operator_2.TeleOpGlyphGripper;

public class XbotTeleOpSubHandlerRegister {

    public void registerListeners(XbotTeleOp xbotTeleOp) {
        xbotTeleOp.registerHandler(new TeleOpElevator());
        xbotTeleOp.registerHandler(new TeleOpDrive());
        xbotTeleOp.registerHandler(new TeleOpGlyphGripper());
        xbotTeleOp.registerHandler(new TeleOpJewelSmacker());
    }
}
