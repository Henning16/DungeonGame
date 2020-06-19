package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.LinkedList;
import java.util.List;

public class ItemContainerComponent extends Component {

    private LinkedList<Integer> items = new LinkedList<>();
    private int size = 1;

    @Override
    public void set(String[] parameters) throws IllegalArgumentException {
        size = (parameters[0] != null) ? Integer.parseInt(parameters[0]) : size;
    }

    @Override
    public String[] get() {
        String[] parameters = new String[1];
        parameters[0] = String.valueOf(size);
        return parameters;
    }

    @Override
    public void tick(float delta) {

    }

    @Override
    public void render(Batch batch) {

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

    public int getItem(int id) {
        try {
            return items.get(id);
        } catch(IndexOutOfBoundsException e) {
            return -1;
        }
    }

    public List<Integer> getItems() {
        return items;
    }

    public boolean isFull() {
        return items.size() >= size;
    }
}
