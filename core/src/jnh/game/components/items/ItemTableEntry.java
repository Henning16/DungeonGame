package jnh.game.components.items;

public class ItemTableEntry {

    private int lowerQuantity = 1;
    private int upperQuantity = 1;
    private String type;

    public int getLowerQuantity() {
        return lowerQuantity;
    }

    public int getUpperQuantity() {
        return upperQuantity;
    }

    public String getType() {
        return type;
    }
}
