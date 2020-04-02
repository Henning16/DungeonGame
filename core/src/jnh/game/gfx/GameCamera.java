package jnh.game.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import jnh.game.Global;
import jnh.game.screens.GameScreen;

public class GameCamera extends OrthographicCamera {

    private GameScreen screen;

    public GameCamera(GameScreen screen) {
        super();
        this.screen = screen;
        setToOrtho(false, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        update();
    }

    public void act(float delta) {
        position.x = screen.getStage().getPlayer().getX() * Global.UNIT;
        position.y = screen.getStage().getPlayer().getY() * Global.UNIT;
        update();
    }

    public void resize(int width, int height) {
        setToOrtho(false, width / 2f, height / 2f);
    }
}
