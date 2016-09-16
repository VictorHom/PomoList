package com.example.victorhom.pomolist;

/**
 * Created by victorhom on 9/15/16.
 */
public class TimerSettings {
    private static int taskTimeMinute = 25;
    private static int breakTimeMinute = 5;
    private int taskTime = 60 * taskTimeMinute;
    private int breakTime = 60 * breakTimeMinute;

    private static TimerSettings ts = new TimerSettings();

    private TimerSettings() {};

    public static TimerSettings getInstance() {
        return ts;
    }

    protected static int getTaskTimeMinute() {
        return getInstance().taskTimeMinute;
    }

    protected static int setTaskTimeMinute(int t) {
        getInstance().taskTimeMinute = t;
        return t;
    }

    protected static int getBreakTimeMinute() {
        return getInstance().breakTimeMinute;
    }

    protected static int setBreakTimeMinute(int t) {
        getInstance().breakTimeMinute = t;
        return t;
    }
}
