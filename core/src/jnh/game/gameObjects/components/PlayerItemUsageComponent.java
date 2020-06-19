package jnh.game.gameObjects.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import jnh.game.gameObjects.GameObject;

public class PlayerItemUsageComponent extends Component {

    private ItemContainerComponent containerComponent;

    @Override
    public void set(String[] parameters) throws Exception {

    }

    @Override
    public String[] get() {
        return new String[0];
    }

    @Override
    public void tick(float delta) {
        if(containerComponent == null) {
            containerComponent = (ItemContainerComponent) gameObject.getComponent(ItemContainerComponent.class);
            return;
        }
        if(Gdx.input.justTouched()) {
            GameObject item =  gameObject.getGameObjectManager().getGameObject(containerComponent.getItem(0));
            if(item != null) {
                ((ItemComponent) item.getComponent(ItemComponent.class)).use(gameObject);
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
    public Component copy() {
        PlayerItemUsageComponent c = new PlayerItemUsageComponent();
        return c;
    }

}
