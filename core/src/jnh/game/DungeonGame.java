package jnh.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import jnh.game.gfx.Assets;
import jnh.game.screens.GameScreen;

public class DungeonGame extends Game {

    @Override
    public void create () {
        new Assets ();
        Gdx.graphics.setVSync (false);
        setScreen (new GameScreen (this));
    }

}