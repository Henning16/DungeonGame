package jnh.game.gfx;

import com.badlogic.gdx.graphics.OrthographicCamera;
import jnh.game.Global;
import jnh.game.screens.GameScreen;

public class GameCamera extends OrthographicCamera {

    private GameScreen screen;

    private Shake shake = Shake.EMPTY;

    public GameCamera(GameScreen screen) {
        super();
        this.screen = screen;
        position.set(screen.VIEWPORT_WIDTH / 2, screen.VIEWPORT_HEIGHT / 2, 0);
        update();
    }

    public void shake(Shake shake) {
        this.shake = shake;
    }

    public void act(float delta) {
        position.x = screen.getStage().getGameObjectManager().getGameObject(screen.getStage().getGameObjectManager().playerID).getX() * Global.UNIT;
        position.y = screen.getStage().getGameObjectManager().getGameObject(screen.getStage().getGameObjectManager().playerID).getY() * Global.UNIT;
        if(shake.duration > 0f) {
            translate((float) (Math.random() * 2f - 1f) * shake.intensity, (float) (Math.random() * 2f - 1f) * shake.intensity);
        }
        if(shake.duration > 0f) {
            shake.duration = Math.max(0f, shake.duration - delta);
        }
        update();
    }

    public void resize(int width, int height) {
        viewportWidth = screen.VIEWPORT_WIDTH;
        viewportHeight = (screen.VIEWPORT_WIDTH / width) * height;
    }
}
