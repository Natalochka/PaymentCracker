package com.payment_cracker.api.dao.utils;

/**
 * Created by Александр on 1/18/2015.
 */
public class ExecutionTimeController {
    private static Long startTime;
    private static Long endTime;
    private static Long duration;

    public static void startCount() {
        startTime = System.nanoTime();
    }

    public static void endCount() {
        endTime = System.nanoTime();
        duration = (endTime - startTime);
    }

    public static String getInfo() {
        return ((Double) (duration / 1000000000.0)) + " seconds";
    }
}
