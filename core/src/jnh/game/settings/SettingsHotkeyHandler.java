package jnh.game.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class SettingsHotkeyHandler {

    public static void update() {

        if(Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
            Settings.setShowingUI(!Settings.isShowingUI());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.F4)) {
            Settings.setRenderingLights(!Settings.isRenderingLights());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
            Settings.setCameraFollowingPlayer(!Settings.isCameraFollowingPlayer());
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F6)) {
            Settings.setUsingColorGrading(!Settings.isUsingColorGrading());
        }
    }

}
