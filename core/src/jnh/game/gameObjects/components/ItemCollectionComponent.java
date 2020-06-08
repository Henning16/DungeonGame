package jnh.game.gameObjects.components;

import jnh.game.gameObjects.items.Item;

public class ItemCollectionComponent extends Component {

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
    public void tick(float delta) {
        if(containerComponent == null) {
            containerComponent = (ItemContainerComponent) gameObject.getComponent(ItemContainerComponent.class);
            return;
        }
        for(Item item: gameObject.getStage().getItems()) {
            //pythagoras
            if((item.getX() - gameObject.getX())*(item.getX() - gameObject.getX()) + (item.getY() - gameObject.getY())*(item.getY() - gameObject.getY()) <= range * range) {
                containerComponent.add(item);
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