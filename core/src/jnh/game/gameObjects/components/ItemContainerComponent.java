package jnh.game.gameObjects.components;

import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.ID;

import java.util.ArrayList;
import java.util.List;

public class ItemContainerComponent extends Component {

    private List<ID> items = new ArrayList<>();
    private int size = 1;

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
        return c;
    }

    public boolean add(ID id) {
        if(items.size() >= size) {
            return false;
        } else {
            items.add(id);
            if(gameObject.getType().equals("PLAYER")) {
                gameObject.getGameObjectManager().getGameObject(id).setPersistent(true);
            }
            return true;
        }
    }

    public ID remove(int index) {
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
}