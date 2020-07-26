package jnh.game;

import com.badlogic.gdx.Game;
import jnh.game.assets.Assets;
import jnh.game.screens.StartScreen;
import jnh.game.settings.Settings;

/**
 * This class manages the overall game.
 */
public class DungeonGame extends Game {

    /**
     * Called when the game is created. This method loads the settings and assets and sets the screen to a new {@link StartScreen}.
     */
    @Override
    public void create() {
        Settings.load();
        Assets.init();
        setScreen(new StartScreen(this));
    }

    /**
     * Called when all ressources should be released.
     */
    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
        getScreen().dispose();
    }

}