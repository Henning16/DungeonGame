package jnh.game.gameObjects.behaviors;

import jnh.game.gameObjects.entities.Entity;
import jnh.game.gameObjects.items.Item;

public class ItemCollectionBehavior extends Behavior {

    private float sensivity = 0.5f;

    public ItemCollectionBehavior(Entity gameObject) {
        super(gameObject);
    }

    @Override
    public void act(double delta) {
        for(Item item: gameObject.getStage().getItems()) {
            //pythagoras
            if((item.getX() - gameObject.getX())*(item.getX() - gameObject.getX()) + (item.getY() - gameObject.getY())*(item.getY() - gameObject.getY()) <= sensivity*sensivity) {
                ((Entity) gameObject).getItemContainer().add(item);
                item.setToBeRemoved(true);
            }
        }
    }
}