package jnh.game.gameObjects.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import jnh.game.utils.Direction;

public class PlayerMovementComponent extends Component {

    private MovementComponent movementComponent;

    public PlayerMovementComponent() {

    }

    public PlayerMovementComponent(PlayerMovementComponent other) {
        super(other);
        this.movementComponent = other.movementComponent;
    }

    @Override
    public void set(String[] parameters) throws Exception {

    }

    @Override
    public void tick(float delta) {
        if(movementComponent == null) {
            movementComponent = (MovementComponent) gameObject.getComponent(MovementComponent.class);
            return;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            movementComponent.move(Direction.UP);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            movementComponent.move(Direction.RIGHT);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            movementComponent.move(Direction.DOWN);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            movementComponent.move(Direction.LEFT);
        }
    }

    @Override
    public void render() {

    }

    @Override
    public void remove() {

    }

    @Override
    public PlayerMovementComponent copy() {
        PlayerMovementComponent c = new PlayerMovementComponent();
        return c;
    }
}
