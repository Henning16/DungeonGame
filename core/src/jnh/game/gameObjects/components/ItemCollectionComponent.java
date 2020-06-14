package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import jnh.game.gameObjects.GameObject;

import java.io.Serializable;

public class ItemCollectionComponent extends Component implements Serializable {

    private ItemContainerComponent containerComponent;

    private float range = 0.5f;

    public ItemCollectionComponent() {

    }

    public ItemCollectionComponent(ItemCollectionComponent other) {
        this.range = other.range;
    }

    @Override
    public void set(String[] parameters) throws Exception {
        range = (parameters[0] != null) ? Float.parseFloat(parameters[0]): range;
    }

    @Override
    public String[] get() {
        String[] parameters = new String[1];
        parameters[0] = String.valueOf(range);
        return parameters;
    }

    @Override
    public void tick(float delta) {
        if(containerComponent == null) {
            containerComponent = (ItemContainerComponent) gameObject.getComponent(ItemContainerComponent.class);
            return;
        }
        //TODO use new item system
        for(int itemID: gameObject.getGameObjectManager().items) {
            //pythagoras
            GameObject item = gameObject.getGameObjectManager().getGameObject(itemID);
            float distanceSquare = (item.getX() - gameObject.getX())*(item.getX() - gameObject.getX()) + (item.getY() - gameObject.getY())*(item.getY() - gameObject.getY());
            if(distanceSquare <= 5 * range * range) {
                item.moveBy(0.2f * (gameObject.getX() - item.getX()), 0.2f * (gameObject.getY() - item.getY()));
            }
            if(distanceSquare <= range * range) {
                containerComponent.add(itemID);
                gameObject.getGameObjectManager().requestRemove(itemID);
            }
        }
    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public void remove() {

    }

    @Override
    public ItemCollectionComponent copy() {
        ItemCollectionComponent c = new ItemCollectionComponent();
        c.range = range;
        return c;
    }
}