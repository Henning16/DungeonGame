package jnh.game.desktop;

import com.badlogic.gdx.Files;
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
        config.samples = 0;
        config.forceExit = false;
        config.pauseWhenBackground = true;
        config.backgroundFPS = -1;
        config.foregroundFPS = 60;
        config.vSyncEnabled = false;
        config.resizable = true;
        config.addIcon("textures/icon.png", Files.FileType.Internal);
        new LwjglApplication(new DungeonGame(), config);
    }
}