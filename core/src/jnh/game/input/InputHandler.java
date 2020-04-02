package jnh.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import jnh.game.stages.GameStage;

public class InputHandler {

    private GameStage stage;

    public InputHandler(GameStage stage) {
        this.stage = stage;
    }

    public void act(double delta) {
        playerMovement();
    }

    private void playerMovement() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            stage.getPlayer().move(Direction.UP);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            stage.getPlayer().move(Direction.RIGHT);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            stage.getPlayer().move(Direction.DOWN);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            stage.getPlayer().move(Direction.LEFT);
        }
    }

}
