package org.xbot.ftc.operatingcode;

import org.xbot.ftc.operatingcode.teleop.TeleOpHandler;
import org.xbot.ftc.operatingcode.teleop.operator_1.TeleOpMecanumDrive;
import org.xbot.ftc.operatingcode.teleop.operator_2.TeleOpJewelSmacker;
import org.xbot.ftc.robotcore.XbotOpModeListener;

import java.util.ArrayList;
import java.util.List;

public class TeleOpRegister {

    private static List<XbotOpModeListener> listeners = new ArrayList<>();

    public static void registerListeners() {
        listeners.add(new TeleOpMecanumDrive());
        listeners.add(new TeleOpJewelSmacker());

        for (XbotOpModeListener listener : listeners) {
            TeleOpHandler.registerListener(listener);
        }
    }

    public static List<XbotOpModeListener> getListeners() {
        return listeners;
    }
}
