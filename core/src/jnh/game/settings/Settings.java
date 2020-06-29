package jnh.game.settings;

public class Settings {

    private static boolean cameraFollowingPlayer = false;
    private static boolean renderingLights = false;
    private static boolean showingUI = false;
    private static float uiScale = 1;

    public static boolean isCameraFollowingPlayer() {
        return cameraFollowingPlayer;
    }

    public static void setCameraFollowingPlayer(boolean cameraFollowingPlayer) {
        Settings.cameraFollowingPlayer = cameraFollowingPlayer;
    }

    public static boolean isRenderingLights() {
        return renderingLights;
    }

    public static void setRenderingLights(boolean renderingLights) {
        Settings.renderingLights = renderingLights;
    }

    public static boolean isShowingUI() {
        return showingUI;
    }

    public static void setShowingUI(boolean showingUI) {
        Settings.showingUI = showingUI;
    }

    public static float getUIScale() {
        return uiScale;
    }

    public static void setUIScale(float uiScale) {
        Settings.uiScale = uiScale;
    }
}