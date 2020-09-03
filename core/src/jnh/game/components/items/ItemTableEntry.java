package jnh.game.components.items;

public class ItemTableEntry {

    private final int lowerQuantity = 1;
    private final int upperQuantity = 1;
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
