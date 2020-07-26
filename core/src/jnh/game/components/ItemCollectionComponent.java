package jnh.game.components;

import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.ID;

import java.io.Serializable;

public class ItemCollectionComponent extends Component implements Serializable {

    private transient ItemContainerComponent containerComponent;

    private float range = 0.5f;

    private ItemCollectionComponent() {

    }

    public ItemCollectionComponent(ItemCollectionComponent other) {
        this.range = other.range;
    }

    @Override
    public void tick(float delta) {
        if(containerComponent == null) {
            containerComponent = gameObject.getComponent(ItemContainerComponent.class);
            return;
        }
        for(ID itemID: gameObject.getGameObjectManager().getGameObjectsByTag("collectable")) {
            GameObject item = gameObject.getGameObjectManager().getGameObject(itemID);
            float distanceSquare = (item.getX() - gameObject.getX())*(item.getX() - gameObject.getX()) + (item.getY() - gameObject.getY())*(item.getY() - gameObject.getY());
            if(distanceSquare <= 5 * range * range && !containerComponent.isFull()) {
                item.moveBy(0.2f * (gameObject.getX() - item.getX()), 0.2f * (gameObject.getY() - item.getY()));
            }
            if(distanceSquare <= range * range) {
                if(containerComponent.add(itemID)) {
                    long soundID = Assets.sounds.COLLECT_ITEM.play();
                    Assets.sounds.COLLECT_ITEM.setPitch(soundID, (float) Math.random() + 1);
                    Assets.sounds.COLLECT_ITEM.setVolume(soundID, 10000);
                    item.removeTagWhileIterating("collectable");
                    gameObject.getGameObjectManager().requestRemove(itemID);
                }
            }
        }
    }

    @Override
    public ItemCollectionComponent copy() {
        ItemCollectionComponent c = new ItemCollectionComponent();
        c.range = range;
        return c;
    }
}