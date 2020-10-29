package jnh.game.components.items;

import jnh.game.assets.Tags;
import jnh.game.components.Component;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.ID;

import java.util.ArrayList;
import java.util.List;

public class ItemContainerComponent extends Component {

    private List<ID> items = new ArrayList<>();
    private int size = 1;
    private float spread = 1;

    @Override
    public void tick(float delta) {
        for(ID itemID: items) {
            GameObject item = gameObject.getGameObjectManager().getGameObject(itemID);
            if(item != null) {
                item.tick(delta);
            }
        }
    }

    @Override
    public ItemContainerComponent copy() {
        ItemContainerComponent c = new ItemContainerComponent();
        c.size = size;
        c.items = items;
        c.spread = spread;
        return c;
    }

    public boolean add(ID id) {
        if(items.size() >= size || itemIsInContainer(id)) {
            return false;
        } else {
            items.add(id);
            if(gameObject.getType().equals("PLAYER")) {
                gameObject.getGameObjectManager().getGameObject(id).setPersistent(true);
            }
            return true;
        }
    }

    public boolean itemIsInContainer(ID itemID) {
        for(ID id : items) {
            if(id.equals(itemID)) {
                return true;
            }
        }
        return false;
    }

    public void remove(ID id) {
        items.remove(id);
    }

    public ID remove(int index) {
        ID id = items.get(index);
        if(id != null) {
            gameObject.getGameObjectManager().getGameObject(id).getComponent(ItemComponent.class).setInHand(false);
            gameObject.getGameObjectManager().getGameObject(id).setPersistent(false);
        }
        return items.remove(index);
    }

    public ID getItem(int index) {
        try {
            return items.get(index);
        } catch(IndexOutOfBoundsException e) {
            return ID.NULL;
        }
    }

    public List<ID> getItems() {
        return items;
    }

    public boolean isFull() {
        return items.size() >= size;
    }

    public void ejectAll() {
        for(ID itemID : items) {
            GameObject item = gameObject.getGameObjectManager().getGameObject(itemID);
            if(item == null) {
                continue;
            }
            item.addTag(Tags.collectable);
            if(item.isRemoved()) {
                gameObject.getStage().getMainLayer().addActor(item);
                item.setRemoved(false);
            }
            item.getComponent(ItemComponent.class).setInHand(false);
            item.setPersistent(false);
            item.setFlip(false, false);
            item.setRotation(315);
            item.setPosition((float) (gameObject.getX() + Math.random() * spread * 2 - spread),
                    (float) (gameObject.getY() + Math.random() * spread * 2 - spread));
        }
        items.clear();
    }


}