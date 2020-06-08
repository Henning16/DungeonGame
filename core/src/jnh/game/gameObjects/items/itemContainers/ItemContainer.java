package jnh.game.gameObjects.items.itemContainers;

import jnh.game.gameObjects.items.Item;

import java.util.LinkedList;

@Deprecated
public class ItemContainer {

    public static final int DEFAULT_SIZE = 32;

    private LinkedList<Item> items;
    private int size;

    public ItemContainer(int size) {
        this.size = size;
        items = new LinkedList<Item>();
    }

    public boolean add(Item item) {
        if(items.size() >= size) {
            return false;
        } else {
            items.add(item);
            return true;
        }
    }

    public boolean remove(Item item) {
        return items.remove(item);
    }

}