package org.xbot.ftc.operatingcode.teleop;

import org.xbot.ftc.operatingcode.teleop.operator_1.TeleOpMecanumDrive;
import org.xbot.ftc.operatingcode.teleop.operator_2.TeleOpElevator;
import org.xbot.ftc.operatingcode.teleop.operator_2.TeleOpJewelSmacker;
import org.xbot.ftc.robotcore.XbotTeleOpListener;

import java.util.ArrayList;
import java.util.List;

public class TeleOpSubHandlerRegister {

    private static List<XbotTeleOpListener> listeners = new ArrayList<>();

    public static void registerListeners() {
        listeners.add(new TeleOpMecanumDrive());
        listeners.add(new TeleOpJewelSmacker());
        listeners.add(new TeleOpElevator());

        for (XbotTeleOpListener listener : listeners) {
            TeleOpHandler.registerListener(listener);
        }
    }

    public static List<XbotTeleOpListener> getListeners() {
        return listeners;
    }
}
