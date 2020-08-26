package jnh.game.components.items;

import jnh.game.assets.Tags;
import jnh.game.components.Component;
import jnh.game.gameObjects.ID;

//TODO might need rework
public class ChestComponent extends Component {

    private transient ItemContainerComponent itemContainerComponent;
    private float range = 1;

    @Override
    public void tick(float delta) {
        super.tick(delta);
        if(itemContainerComponent == null) {
            itemContainerComponent = gameObject.getComponent(ItemContainerComponent.class);
            return;
        }
        for(ID playerID : gameObject.getGameObjectManager().getGameObjectsByTag(Tags.player)) {
            if(gameObject.getPosition().dst2(gameObject.getGameObjectManager().getGameObject(playerID).getPosition()) <= range * range) {
                itemContainerComponent.ejectAll();
            }
        }
    }

    @Override
    public Component copy() {
        ChestComponent c = new ChestComponent();
        c.range = range;
        return c;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }
}
