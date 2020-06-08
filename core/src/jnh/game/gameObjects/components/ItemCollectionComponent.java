package jnh.game.gameObjects.components;

import jnh.game.gameObjects.entities.Entity;
import jnh.game.gameObjects.items.Item;

public class ItemCollectionComponent extends Component {

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
    public void tick(double delta) {
        for(Item item: gameObject.getStage().getItems()) {
            //pythagoras
            if((item.getX() - gameObject.getX())*(item.getX() - gameObject.getX()) + (item.getY() - gameObject.getY())*(item.getY() - gameObject.getY()) <= range * range) {
                ((Entity) gameObject).getItemContainer().add(item);
                item.setToBeRemoved(true);
            }
        }
    }

    @Override
    public void render() {

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