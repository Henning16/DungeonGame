package jnh.game.gameObjects.components;

import java.util.LinkedList;

//TODO change from Item to GameObject
public class ItemContainerComponent extends Component {

    private LinkedList<Integer> items = new LinkedList<>();
    private int size = 1;

    @Override
    public void set(String[] parameters) throws IllegalArgumentException {
        size = (parameters[0] != null) ? Integer.parseInt(parameters[0]) : size;
    }

    @Override
    public void tick(float delta) {

    }

    @Override
    public void render() {

    }

    @Override
    public void remove() {

    }

    @Override
    public ItemContainerComponent copy() {
        ItemContainerComponent c = new ItemContainerComponent();
        c.size = size;
        c.items = items;
        return c;
    }

    public boolean add(int id) {
        if(items.size() >= size) {
            return false;
        } else {
            items.add(id);
            return true;
        }
    }

    public int remove(int id) {
        return items.remove(id);
    }
}
