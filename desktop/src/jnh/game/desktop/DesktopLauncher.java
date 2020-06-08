package jnh.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import jnh.game.DungeonGame;

public class DesktopLauncher {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "DungeonGame";
        config.useGL30 = false;
        config.width = 1600;
        config.height = 900;
        config.backgroundFPS = 60;
        config.foregroundFPS = 60;
        config.vSyncEnabled = true;
        config.fullscreen = false;
        new LwjglApplication(new DungeonGame(), config);
    }
}