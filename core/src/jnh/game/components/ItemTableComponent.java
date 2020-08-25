package jnh.game.components;

import jnh.game.assets.Assets;
import jnh.game.assets.Blueprints;
import jnh.game.assets.Tags;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.construction.Blueprint;

public class ItemTableComponent extends Component {

    private ItemTableEntry[] entries = new ItemTableEntry[0];

    private transient ItemContainerComponent itemContainerComponent;

    @Override
    public void tick(float delta) {
        super.tick(delta);
        itemContainerComponent = gameObject.getComponent(ItemContainerComponent.class);
        if(itemContainerComponent == null) {
            return;
        }
        for(ItemTableEntry entry : entries) {
            int quantity = (int) Math.round(entry.getLowerQuantity() +
                    (entry.getUpperQuantity() - entry.getLowerQuantity()) * Math.random());
            for(int i = 0; i < quantity; i++) {
                try {
                    GameObject item = new GameObject(gameObject.getStage(), (Blueprint) Blueprints.class.getField(entry.getType()).get(Assets.blueprints));
                    item.removeTag(Tags.collectable);
                    item.remove();
                    itemContainerComponent.add(item.getID());
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        gameObject.getComponents().remove(this);
    }

    @Override
    public Component copy() {
        ItemTableComponent c = new ItemTableComponent();
        c.entries = entries;
        return c;
    }

}