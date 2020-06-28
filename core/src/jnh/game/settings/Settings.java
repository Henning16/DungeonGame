package jnh.game.settings;

public class Settings {

    private static float uiScale = 1;

    public static float getUIScale() {
        return uiScale;
    }

    public static void setUIScale(float uiScale) {
        Settings.uiScale = uiScale;
    }
}