package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;

import java.io.Serializable;

public class ItemCollectionComponent extends Component implements Serializable {

    private ItemContainerComponent containerComponent;

    private float range = 0.5f;

    public ItemCollectionComponent() {

    }

    public ItemCollectionComponent(ItemCollectionComponent other) {
        this.range = other.range;
    }

    @Override
    public void set(String[] parameters) throws Exception {
        range = (parameters[0] != null) ? Float.parseFloat(parameters[0]): range;
    }

    @Override
    public String[] get() {
        String[] parameters = new String[1];
        parameters[0] = String.valueOf(range);
        return parameters;
    }

    @Override
    public void tick(float delta) {
        if(containerComponent == null) {
            containerComponent = (ItemContainerComponent) gameObject.getComponent(ItemContainerComponent.class);
            return;
        }
        for(int itemID: gameObject.getGameObjectManager().items) {
            GameObject item = gameObject.getGameObjectManager().getGameObject(itemID);
            float distanceSquare = (item.getX() - gameObject.getX())*(item.getX() - gameObject.getX()) + (item.getY() - gameObject.getY())*(item.getY() - gameObject.getY());
            if(distanceSquare <= 5 * range * range && !containerComponent.isFull()) {
                item.moveBy(0.2f * (gameObject.getX() - item.getX()), 0.2f * (gameObject.getY() - item.getY()));
            }
            if(distanceSquare <= range * range) {
                if(containerComponent.add(itemID)) {
                    long soundID = Assets.sounds.COLLECT_ITEM.play();
                    Assets.sounds.COLLECT_ITEM.setPitch(soundID, (float) Math.random() + 1);
                    Assets.sounds.COLLECT_ITEM.setVolume(soundID, 1000);
                    gameObject.getGameObjectManager().requestRemove(itemID);
                }
            }
        }
    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public void remove() {

    }

    @Override
    public ItemCollectionComponent copy() {
        ItemCollectionComponent c = new ItemCollectionComponent();
        c.range = range;
        return c;
    }
}