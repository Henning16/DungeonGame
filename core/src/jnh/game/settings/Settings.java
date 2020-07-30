package jnh.game.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class Settings {

    private static Settings instance;

    private transient boolean cameraFollowingPlayer = true;
    private transient boolean renderingLights = true;
    private transient boolean showingUI = true;
    private transient boolean usingColorGrading = true;
    private float uiScale = 2;

    private Settings() {

    }

    public static void load() {
        FileHandle settingsFile = Gdx.files.external(".dungeongame/settings.json");
        if(settingsFile.exists()) {
            instance = new Json().fromJson(Settings.class, settingsFile.readString());
        } else {
            instance = new Settings();
        }
    }

    public static void save() {
        Gdx.files.external(".dungeongame/settings.json").writeString(new Json().toJson(instance), false);
    }

    public static boolean isCameraFollowingPlayer() {
        return instance.cameraFollowingPlayer;
    }

    public static void setCameraFollowingPlayer(boolean cameraFollowingPlayer) {
        instance.cameraFollowingPlayer = cameraFollowingPlayer;
    }

    public static boolean isRenderingLights() {
        return instance.renderingLights;
    }

    public static void setRenderingLights(boolean renderingLights) {
        instance.renderingLights = renderingLights;
    }

    public static boolean isShowingUI() {
        return instance.showingUI;
    }

    public static boolean isUsingColorGrading() {
        return instance.usingColorGrading;
    }

    public static void setUsingColorGrading(boolean usingColorGrading) {
        instance.usingColorGrading = usingColorGrading;
    }

    public static void setShowingUI(boolean showingUI) {
        instance.showingUI = showingUI;
    }

    public static float getUIScale() {
        return instance.uiScale;
    }

    public static void setUIScale(float uiScale) {
        instance.uiScale = uiScale;
    }

}