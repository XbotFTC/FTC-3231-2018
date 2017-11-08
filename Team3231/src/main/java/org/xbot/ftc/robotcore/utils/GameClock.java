package org.xbot.ftc.robotcore.utils;

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
        double currentTime = System.currentTimeMillis();
        secondsElapsed += (currentTime - previousSystemTime) / 1000.0;
        previousSystemTime = currentTime;
        return secondsElapsed;
    }

    public static GameClock getInstance() {
        if (instance == null) {
            instance = new GameClock();
        }
        return instance;
    }
}
