package jnh.game.gameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gfx.Assets;
import jnh.game.lights.GamePointLight;
import jnh.game.stages.GameStage;

public class Player extends Entity {

    private GamePointLight playerLight;

    public Player(GameStage stage, Vector2 position) {
        super(stage, Assets.PLAYER_DOWN, position, new Vector2(DEFAULT_ENTITY_WIDTH, DEFAULT_ENTITY_HEIGHT));
        playerLight = new GamePointLight(stage.getRayHandler(), new Color(1f, 1f, 0f, 0.2f), false, 1, position);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        playerLight.setPosition(getBody().getPosition());
        switch(getLooking()) {
            case UP: setTexture(Assets.PLAYER_UP); break;
            case RIGHT: setTexture(Assets.PLAYER_RIGHT); break;
            case DOWN: setTexture(Assets.PLAYER_DOWN); break;
            case LEFT: setTexture(Assets.PLAYER_LEFT); break;
        }
    }
}