package jnh.game.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import jnh.game.gameObjects.GameObject;
import jnh.game.utils.Direction;

public class ItemRenderComponent extends Component {

    private transient ItemContainerComponent containerComponent;
    private transient MovementComponent movementComponent;

    @Override
    public void tick(float delta) {
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
        if(item.isRemoved()) {
            gameObject.getStage().getMainLayer().addActor(item);
            item.setRemoved(false);
            item.getComponent(ItemComponent.class).setInWorld(false);
        }
        item.setPosition(gameObject.getPosition());
        switch(movementComponent.getLooking()) {
            case Direction.LEFT:
                item.setX(item.getX() - 0.1f);
                item.setY(gameObject.getY() + 0.15f);
                item.setzPosition(0);
                item.setRotation(45);
                if(!item.getTexture().isFlipX()) {
                    item.getTexture().flip(true, false);
                }
                break;
            case Direction.RIGHT:
                item.setX(item.getX() + 0.35f);
                item.setY(gameObject.getY() + 0.15f);
                item.setzPosition(-0.151f);
                item.setRotation(315);
                if(item.getTexture().isFlipX()) {
                    item.getTexture().flip(true, false);
                }
                break;
            case Direction.UP:
                item.setX(item.getX() + 0.55f);
                item.setY(gameObject.getY() + 0.22f);
                item.setzPosition(0.221f);
                item.setRotation(337.5f);
                if(item.getTexture().isFlipX()) {
                    item.getTexture().flip(true, false);
                }
                break;
            case Direction.DOWN:
                item.setX(item.getX() - 0.3f);
                item.setY(gameObject.getY() + 0.17f);
                item.setzPosition(-0.01f);
                item.setRotation(22.5f);
                if(!item.getTexture().isFlipX()) {
                    item.getTexture().flip(true, false);
                }
                break;
        }
    }

    @Override
    public void render(Batch batch) {
        if(containerComponent == null) {
            return;
        }
    }

    @Override
    public ItemRenderComponent copy() {
        ItemRenderComponent c = new ItemRenderComponent();
        return c;
    }
}
