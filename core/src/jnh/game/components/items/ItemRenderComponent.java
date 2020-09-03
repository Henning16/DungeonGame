package jnh.game.components.items;

import com.badlogic.gdx.graphics.g2d.Batch;
import jnh.game.Global;
import jnh.game.components.Component;
import jnh.game.components.LightComponent;
import jnh.game.components.MovementComponent;
import jnh.game.gameObjects.GameObject;
import jnh.game.utils.Direction;

public class ItemRenderComponent extends Component {

    private transient ItemContainerComponent containerComponent;
    private transient MovementComponent movementComponent;

    @Override
    public void tick(float delta) {
        super.tick(delta);
        if(containerComponent == null ) {
            containerComponent = gameObject.getComponent(ItemContainerComponent.class);
            return;
        }
        if(movementComponent == null) {
            movementComponent = gameObject.getComponent(MovementComponent.class);
            return;
        }
        GameObject item = gameObject.getGameObjectManager().getGameObject(containerComponent.getItem(0));
        if(item == null) {
            return;
        }
        WeaponComponent weaponComponent = item.getComponent(WeaponComponent.class);
        if(item.isRemoved()) {
            gameObject.getStage().getMainLayer().addActor(item);
            item.setRemoved(false);
            if(item.getComponent(LightComponent.class) != null) {
                item.getComponent(LightComponent.class).add();
                item.getComponent(LightComponent.class).tick(0);
            }
        }
        item.getComponent(ItemComponent.class).setInHand(true);
        item.setPosition(gameObject.getPosition());
        int state = movementComponent.getState();
        float rotationModifier = 0;
        if(weaponComponent != null) {
            if(weaponComponent.getAttackTimer() != 0) {
                rotationModifier = weaponComponent.getAttackTimer() * 20;
            }
        }
        switch(movementComponent.getLooking()) {
            case Direction.LEFT:
                item.setX(item.getX() - 0.1f);
                item.setY((float) (gameObject.getY() + 0.18f +
                        state * 0.02f * Math.sin(Global.elapsedTime * 10)));
                item.setzPosition(0);
                item.setRotation((float) (45 -
                        state * 2 * Math.sin(Global.elapsedTime * 10)) + rotationModifier);
                item.setFlip(true, false);
                break;
            case Direction.RIGHT:
                item.setX(item.getX() + 0.3f);
                item.setY((float) (gameObject.getY() + 0.18f +
                        state * 0.02f * Math.sin(Global.elapsedTime * 10)));
                item.setzPosition(-0.2f);
                item.setRotation((float) (315 +
                        state * 2 * Math.sin(Global.elapsedTime * 10)) - rotationModifier);
                item.setFlip(false, false);
                break;
            case Direction.UP:
                item.setX(item.getX() + 0.4f);
                item.setY((float) (gameObject.getY() + 0.18f +
                        state * 0.02f * Math.sin(Global.elapsedTime * 10)));
                item.setzPosition(0.2f);
                item.setRotation((float) (337.5f +
                        state * 2 * Math.sin(Global.elapsedTime * 10)) + rotationModifier);
                item.setFlip(false, false);
                break;
            case Direction.DOWN:
                item.setX(item.getX() - 0.15f);
                item.setY((float) (gameObject.getY() + 0.18f +
                        state * 0.02f * Math.sin(Global.elapsedTime * 10)));
                item.setzPosition(-0.2f);
                item.setRotation((float) (22.5f -
                        state * 2 * Math.sin(Global.elapsedTime * 10)) + rotationModifier);
                item.setFlip(true, false);
                break;
        }
    }

    @Override
    public ItemRenderComponent copy() {
        return new ItemRenderComponent();
    }
}
