package jnh.game;

import com.badlogic.gdx.Game;
import jnh.game.assets.Assets;
import jnh.game.screens.GameScreen;
import jnh.game.screens.StartScreen;

public class DungeonGame extends Game {

    @Override
    public void create() {
        new Assets();
        setScreen(new GameScreen(this));
    }

}