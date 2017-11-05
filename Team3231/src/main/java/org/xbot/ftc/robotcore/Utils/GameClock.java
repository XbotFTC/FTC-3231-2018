package org.xbot.ftc.robotcore.Utils;

public class GameClock {

    private static GameClock instance = null;

    private double secondsElapsed = 0.0;
    private double previousSystemTime = System.currentTimeMillis();

    private GameClock() {
    }

    public void resetClock() {
        secondsElapsed = 0.0;
    }

    public double getTimeElapsed() {
        secondsElapsed += (System.currentTimeMillis() - previousSystemTime) / 1000.0;
        return secondsElapsed;
    }

    public static GameClock getInstance() {
        if (instance == null) {
            instance = new GameClock();
        }
        return instance;
    }
}
