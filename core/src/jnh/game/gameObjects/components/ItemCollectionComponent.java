package jnh.game.gameObjects.components;

import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.entities.Entity;
import jnh.game.gameObjects.items.Item;

public class ItemCollectionComponent extends Component {

    private float range = 0.5f;

    public ItemCollectionComponent(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void init(String[] parameters) throws IllegalArgumentException {
        range = (parameters[0] != null) ? Float.parseFloat(parameters[0]): range;
    }

    @Override
    public void act(double delta) {
        for(Item item: gameObject.getStage().getItems()) {
            //pythagoras
            if((item.getX() - gameObject.getX())*(item.getX() - gameObject.getX()) + (item.getY() - gameObject.getY())*(item.getY() - gameObject.getY()) <= range * range) {
                ((Entity) gameObject).getItemContainer().add(item);
                item.setToBeRemoved(true);
            }
        }
    }
}