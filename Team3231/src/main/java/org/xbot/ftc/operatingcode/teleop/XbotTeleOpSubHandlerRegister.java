package org.xbot.ftc.operatingcode.teleop;

import org.xbot.ftc.operatingcode.teleop.operator_1.TeleOpTankDrive;
import org.xbot.ftc.operatingcode.teleop.operator_2.TeleOpElevator;
import org.xbot.ftc.operatingcode.teleop.operator_2.TeleOpJewelSmacker;

import java.util.ArrayList;
import java.util.List;

public class XbotTeleOpSubHandlerRegister {

    private static List<XbotTeleOpListener> listeners = new ArrayList<>();

    public static void registerListeners() {
        listeners.add(new TeleOpJewelSmacker());
        listeners.add(new TeleOpElevator());
        listeners.add(new TeleOpTankDrive());

        for (XbotTeleOpListener listener : listeners) {
            XbotTeleOpHandler.registerListener(listener);
        }
    }

    public static List<XbotTeleOpListener> getListeners() {
        return listeners;
    }
}
