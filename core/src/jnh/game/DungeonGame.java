package jnh.game;

import com.badlogic.gdx.Game;
import jnh.game.assets.Assets;
import jnh.game.screens.GameScreen;
import jnh.game.screens.StartScreen;
import jnh.game.settings.Settings;

public class DungeonGame extends Game {

    @Override
    public void create() {
        Settings.load();
        new Assets();
        setScreen(new StartScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
        getScreen().dispose();
    }

}