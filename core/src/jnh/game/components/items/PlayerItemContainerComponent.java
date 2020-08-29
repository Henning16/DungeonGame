package jnh.game.components.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import jnh.game.components.Component;
import jnh.game.gameObjects.ID;

public class PlayerItemContainerComponent extends Component {

    private transient ItemContainerComponent itemContainerComponent;

    @Override
    public void tick(float delta) {
        super.tick(delta);
        if(itemContainerComponent == null) {
            itemContainerComponent = gameObject.getComponent(ItemContainerComponent.class);
            return;
        }
        itemScrolling();
        itemThrowing();

    }

    @Override
    public Component copy() {
        PlayerItemContainerComponent c = new PlayerItemContainerComponent();
        return c;
    }

    private void itemScrolling() {
        short scroll = gameObject.getStage().getScreen().getInputHandler().getScroll();
        if(scroll == 1) {
            ID oldHandItemID = itemContainerComponent.getItems().get(0);
            if(oldHandItemID != null) {
                gameObject.getGameObjectManager().getGameObject(oldHandItemID).remove();
                gameObject.getGameObjectManager().getGameObject(oldHandItemID).getComponent(ItemComponent.class).setInHand(false);
            }
            if(itemContainerComponent.getItems().size() != 0) {
                ID id = itemContainerComponent.getItems().remove(0);
                itemContainerComponent.getItems().add(id);
            }
            ID newHandItemID = itemContainerComponent.getItems().get(0);
            if(newHandItemID != null) {
                gameObject.getGameObjectManager().getGameObject(newHandItemID).setPosition(gameObject.getPosition());
            }
        } else if(scroll == -1) {
            ID oldHandItemID = itemContainerComponent.getItems().get(0);
            if(oldHandItemID != null) {
                gameObject.getGameObjectManager().getGameObject(oldHandItemID).remove();
                gameObject.getGameObjectManager().getGameObject(oldHandItemID).getComponent(ItemComponent.class).setInHand(false);
            }
            if(itemContainerComponent.getItems().size() != 0) {
                ID id = itemContainerComponent.getItems().get(itemContainerComponent.getItems().size() - 1);
                itemContainerComponent.getItems().remove(itemContainerComponent.getItems().size() - 1);
                itemContainerComponent.getItems().add(0, id);
            }
            ID newHandItemID = itemContainerComponent.getItems().get(0);
            if(newHandItemID != null) {
                gameObject.getGameObjectManager().getGameObject(newHandItemID).setPosition(gameObject.getPosition());
            }
        }
    }

    private void itemThrowing() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            ID oldHandItemID = itemContainerComponent.getItems().get(0);
            if(oldHandItemID != null) {
                gameObject.getGameObjectManager().getGameObject(oldHandItemID).remove();
                gameObject.getGameObjectManager().getGameObject(oldHandItemID).getComponent(ItemComponent.class).setInHand(false);
            }
            ID newHandItemID = itemContainerComponent.getItems().get(0);
            if(newHandItemID != null) {
                gameObject.getGameObjectManager().getGameObject(newHandItemID).setPosition(gameObject.getPosition());
            }
        }
    }

}
