package jnh.game.gameObjects;

import com.badlogic.gdx.math.Vector2;
import jnh.game.gfx.Assets;
import jnh.game.stages.GameStage;

public class Player extends Entity {

    public Player(GameStage stage, Vector2 position) {
        super(stage, Assets.PLAYER_DOWN, position, new Vector2(DEFAULT_ENTITY_WIDTH, DEFAULT_ENTITY_HEIGHT));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        switch(getLooking()) {
            case UP:
                switch(getWalkingState()) {
                    case WALK_1:
                        setTexture(Assets.PLAYER_UP_WALK_1); break;
                    case WALK_2:
                        setTexture(Assets.PLAYER_UP_WALK_2); break;
                    default:
                        setTexture(Assets.PLAYER_UP); break;
                }
                break;
            case RIGHT:
                switch(getWalkingState()) {
                    case WALK_1:
                        setTexture(Assets.PLAYER_RIGHT_WALK_1); break;
                    case WALK_2:
                        setTexture(Assets.PLAYER_RIGHT_WALK_2); break;
                    default:
                        setTexture(Assets.PLAYER_RIGHT); break;
                }
                break;
            case DOWN:
                switch(getWalkingState()) {
                    case WALK_1:
                        setTexture(Assets.PLAYER_DOWN_WALK_1); break;
                    case WALK_2:
                        setTexture(Assets.PLAYER_DOWN_WALK_2); break;
                    default:
                        setTexture(Assets.PLAYER_DOWN); break;
                }
                break;
            case LEFT:
                switch(getWalkingState()) {
                    case WALK_1:
                        setTexture(Assets.PLAYER_LEFT_WALK_1); break;
                    case WALK_2:
                        setTexture(Assets.PLAYER_LEFT_WALK_2); break;
                    default:
                        setTexture(Assets.PLAYER_LEFT); break;
                }
                break;
        }
    }
}