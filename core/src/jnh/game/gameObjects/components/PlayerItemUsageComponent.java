package jnh.game.gameObjects.components;

import com.badlogic.gdx.Gdx;
import jnh.game.gameObjects.GameObject;

public class PlayerItemUsageComponent extends Component {

    private transient ItemContainerComponent containerComponent;

    @Override
    public void tick(float delta) {
        if(containerComponent == null) {
            containerComponent = gameObject.getComponent(ItemContainerComponent.class);
            return;
        }
        if(Gdx.input.justTouched()) {
            GameObject item =  gameObject.getGameObjectManager().getGameObject(containerComponent.getItem(0));
            if(item != null) {
                item.getComponent(ItemComponent.class).use(gameObject);
            }
        }
    }

    @Override
    public Component copy() {
        PlayerItemUsageComponent c = new PlayerItemUsageComponent();
        return c;
    }

}
