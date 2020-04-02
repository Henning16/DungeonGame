package jnh.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnh.game.gfx.Assets;
import jnh.game.screens.GameScreen;

public class DungeonGame extends Game {

	@Override
	public void create () {
		new Assets();
		setScreen(new GameScreen(this));
	}

}