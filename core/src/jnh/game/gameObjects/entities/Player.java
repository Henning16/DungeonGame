package jnh.game.gameObjects.entities;

import com.badlogic.gdx.math.Vector2;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.components.ItemCollectionComponent;
import jnh.game.stages.GameStage;
import jnh.game.utils.Direction;

public class Player extends Entity {

    public Player(GameStage stage, Vector2 position) {
        super(stage, Assets.textures.PLAYER[Direction.DOWN][MovementState.IDLE], position, new Vector2(DEFAULT_ENTITY_WIDTH, DEFAULT_ENTITY_HEIGHT));
        getAnimator().play(Assets.textures.PLAYER[getLooking()][getState()], true);
        setTexture(getAnimator().getTexture());
        addComponent(new ItemCollectionComponent());
    }

    @Override
    public void tick(float delta) {
        super.tick(delta);
    }

    @Override
    public void render() {
        super.render();
        getAnimator().play(Assets.textures.PLAYER[getLooking()][getState()], true);
    }
}