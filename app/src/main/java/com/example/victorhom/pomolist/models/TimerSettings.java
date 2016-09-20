package com.example.victorhom.pomolist.models;

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

    public static int getTaskTimeMinute() {
        return getInstance().taskTimeMinute;
    }

    public static int setTaskTimeMinute(int t) {
        getInstance().taskTimeMinute = t;
        return t;
    }

    public static int getBreakTimeMinute() {
        return getInstance().breakTimeMinute;
    }

    public static int setBreakTimeMinute(int t) {
        getInstance().breakTimeMinute = t;
        return t;
    }
}
