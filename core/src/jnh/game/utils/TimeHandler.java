package jnh.game.utils;

public class TimeHandler {

    private static float time = 0;

    private static long ticks = 0;

    public static void tick(float delta) {
        time += delta;
        ticks++;
    }

    public static float getTime() {
        return time;
    }

    public static long getTicks() {
        return ticks;
    }
}
