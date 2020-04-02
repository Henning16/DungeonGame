package jnh.game.gameObjects.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;
import jnh.game.stages.GameStage;

public class Item extends GameObject {

    public static final float ITEM_SIZE = 0.6f, COLLECT_DISTANCE = 1f;

    public Item(GameStage stage, Texture texture, Vector2 position) {
        super(stage, texture, position, new Vector2(0.6f, 0.6f));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(Math.sqrt((getStage().getPlayer().getX() - getX()) * (getStage().getPlayer().getX() - getX()) +
        (getStage().getPlayer().getY() - getY()) * (getStage().getPlayer().getY() - getY())) <= COLLECT_DISTANCE) {
            getStage().getPlayer().getItemContainer().add(this);
            remove();
        }
    }
}
