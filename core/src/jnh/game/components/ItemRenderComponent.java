package jnh.game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;

public class ItemRenderComponent extends Component {

    private transient ItemContainerComponent containerComponent;
    private transient MovementComponent movementComponent;

    @Override
    public void tick(float delta) {
        if(containerComponent == null) {
            containerComponent = (ItemContainerComponent) gameObject.getComponent(ItemContainerComponent.class);
            return;
        }
        if(movementComponent == null) {
            movementComponent = (MovementComponent) gameObject.getComponent(MovementComponent.class);
        }
    }

    @Override
    public void render(Batch batch) {
        if(containerComponent == null) {
            return;
        }
        renderFirstHand(batch);
    }

    private void renderFirstHand(Batch batch) {
        GameObject item = gameObject.getGameObjectManager().getGameObject(containerComponent.getItem(0));
        if(item == null) {
            return;
        }
        Vector2 mouseWorldPosition = gameObject.getStage().convertScreenPositionToWorldPosition(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        double angle = Math.atan2( - mouseWorldPosition.y + gameObject.getPosition().y, - mouseWorldPosition.x + gameObject.getPosition().x);
        float degrees = (float) Math.toDegrees(angle);
        //batch.draw(item.getTexture(), gameObject.getX(), gameObject.getY(), 1, 1, 1, 1, 1, 1, degrees + 90);
    }

    @Override
    public ItemRenderComponent copy() {
        ItemRenderComponent c = new ItemRenderComponent();
        return c;
    }
}
