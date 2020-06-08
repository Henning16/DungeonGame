package jnh.game.gameObjects.items;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;
import jnh.game.stages.GameStage;

public class Item extends GameObject {

    public static final float ITEM_SIZE = 0.6f;

    private boolean toBeRemoved;
    private Animation<TextureRegion> holdingTexture;

    public Item(GameStage stage, Animation<TextureRegion> animation, Animation<TextureRegion> holdingTexture, Vector2 position) {
        super(stage, animation, position, new Vector2(0.6f, 0.6f));
        this.holdingTexture = holdingTexture;

        getStage().getItems().add(this);
        getAnimator().play(animation,true);
    }

    @Override
    public void tick(float delta) {
        super.tick(delta);
        if(toBeRemoved) {
            getStage().getItems().remove(this);
            remove();
        }
    }

    public boolean isToBeRemoved() {
        return toBeRemoved;
    }

    public void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }

    public Animation<TextureRegion> getHoldingTexture() {
        return holdingTexture;
    }

    public void setHoldingTexture(Animation<TextureRegion> holdingTexture) {
        this.holdingTexture = holdingTexture;
    }
}
