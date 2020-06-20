package jnh.game.gameObjects.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import jnh.game.utils.Direction;

public class PlayerMovementComponent extends Component {

    private transient MovementComponent movementComponent;

    public PlayerMovementComponent() {

    }

    @Override
    public void tick(float delta) {
        if(movementComponent == null) {
            movementComponent = (MovementComponent) gameObject.getComponent(MovementComponent.class);
            return;
        }
        int direction = movementComponent.getLooking();
        int counter = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            movementComponent.move(Direction.UP, (Direction.UP == direction) ? 1f : 0.1f);
            counter++;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            movementComponent.move(Direction.RIGHT, (Direction.RIGHT == direction) ? 1f : 0.1f);
            counter++;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            movementComponent.move(Direction.DOWN, (Direction.DOWN == direction) ? 1f : 0.1f);
            counter++;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            movementComponent.move(Direction.LEFT, (Direction.LEFT == direction) ? 1f : 0.1f);
            counter++;
        }
        if(counter != 1) {
            movementComponent.setLooking(direction);
        }
    }

    @Override
    public PlayerMovementComponent copy() {
        PlayerMovementComponent c = new PlayerMovementComponent();
        return c;
    }
}
