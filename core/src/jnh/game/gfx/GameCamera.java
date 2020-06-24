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
            translate((float) (Math.random() * 2f - 1f) * shake.intensity / 16, (float) (Math.random() * 2f - 1f) * shake.intensity / 16);
        }
        if(shake.duration > 0f) {
            shake.duration = Math.max(0f, shake.duration - delta);
        }
        update();
    }

    public void resize(int width, int height) {
        int scl = (int) ((width / screen.VIEWPORT_WIDTH) / 16);
        if(scl == 0) {
           scl = 1;
        }
        viewportWidth = width / (scl * 16);
        viewportHeight = (viewportWidth / width) * height;
    }
}
