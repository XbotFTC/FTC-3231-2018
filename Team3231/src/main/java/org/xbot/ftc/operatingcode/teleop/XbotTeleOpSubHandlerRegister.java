package org.xbot.ftc.operatingcode.teleop;

import org.xbot.ftc.operatingcode.teleop.operator_1.TeleOpDrive;
import org.xbot.ftc.operatingcode.teleop.operator_2.TeleOpElevator;
import org.xbot.ftc.operatingcode.teleop.operator_2.TeleOpGlyphGripper;

import java.util.ArrayList;
import java.util.List;

public class XbotTeleOpSubHandlerRegister {

    private static List<XbotTeleOpHandler> listeners = new ArrayList<>();

    public static void registerListeners() {
        //listeners.add(new TeleOpJewelSmacker());
        listeners.add(new TeleOpElevator());
        listeners.add(new TeleOpDrive());
        listeners.add(new TeleOpGlyphGripper());

        for (XbotTeleOpHandler listener : listeners) {
            XbotTeleOp.registerListener(listener);
        }
    }

    public static List<XbotTeleOpHandler> getListeners() {
        return listeners;
    }
}
