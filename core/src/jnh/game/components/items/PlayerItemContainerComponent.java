package jnh.game.components.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import jnh.game.assets.Tags;
import jnh.game.components.Component;
import jnh.game.components.MovementComponent;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.GameObjectManager;
import jnh.game.gameObjects.ID;
import jnh.game.utils.Direction;

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
            if(itemContainerComponent.getItems().size() == 0) {
                return;
            }
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
            if(itemContainerComponent.getItems().size() == 0) {
                return;
            }
            ID oldHandItemID = itemContainerComponent.getItems().get(0);
            if(oldHandItemID != null) {
                final GameObject oldItem = gameObject.getGameObjectManager().getGameObject(oldHandItemID);
                itemContainerComponent.remove(0);
                oldItem.getComponent(ItemComponent.class).setInHand(false);
                MovementComponent movementComponent = gameObject.getComponent(MovementComponent.class);
                Action moveByAction = null;
                float offset = (float) (Math.random() * 1 - 0.5);
                oldItem.setPosition(gameObject.getPosition());
                switch(movementComponent.getLooking()) {
                    case Direction.UP:
                        moveByAction = Actions.moveBy(offset, 1.2f, 0.2f);
                        break;
                    case Direction.DOWN:
                        moveByAction = Actions.moveBy(offset, - 1.2f, 0.2f);
                        break;
                    case Direction.LEFT:
                        moveByAction = Actions.moveBy(- 1.2f, offset, 0.2f);
                        break;
                    case Direction.RIGHT:
                        moveByAction = Actions.moveBy(1.2f, offset, 0.2f);
                        break;
                }
                oldItem.addAction(new SequenceAction(moveByAction, new Action() {
                    @Override
                    public boolean act(float delta) {
                        oldItem.addTag(Tags.collectable);
                        return true;
                    }
                }));
            }
        }
    }

}
