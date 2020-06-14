package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.entities.MovementState;
import jnh.game.utils.Direction;

/**
 * Verwaltet die manuellen Bewegungen eines Objekts in der Welt, wie zum Beispiel die Bewegungen von Lebewesen. Diese Component enthält keine AI oder Steuerung und muss von anderen Components verwendet werden, um Sinn zu machen.
 */
public class MovementComponent extends Component {

    private BodyComponent bodyComponent;

    //TODO implement maxSpeed
    private float maxSpeed = 0;

    private int state;
    private int looking;

    public MovementComponent() {

    }

    public MovementComponent(MovementComponent other) {
        super(other);
        this.bodyComponent = other.bodyComponent;
        this.maxSpeed = other.maxSpeed;
        this.state = other.state;
        this.looking = other.looking;
    }

    @Override
    public void set(String[] parameters) throws Exception {
        maxSpeed = (parameters[0] != null) ? Float.parseFloat(parameters[0]) : maxSpeed;
        state = (parameters[1] != null) ? Integer.parseInt(parameters[1]) : state;
        looking = (parameters[2] != null) ? Integer.parseInt(parameters[2]) : looking;
    }

    @Override
    public String[] get() {
        String[] parameters = new String[3];
        parameters[0] = String.valueOf(maxSpeed);
        parameters[1] = String.valueOf(state);
        parameters[2] = String.valueOf(looking);
        return parameters;
    }

    @Override
    public void tick(float delta) {
        if(bodyComponent == null) {
            bodyComponent = (BodyComponent) gameObject.getComponent(BodyComponent.class);
        }
        if(bodyComponent.getBody().getLinearVelocity().len() > 0.5) {
            state = MovementState.WALK;
        } else {
            state = MovementState.IDLE;
        }
    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public void remove() {

    }

    @Override
    public MovementComponent copy() {
        MovementComponent c = new MovementComponent();
        c.maxSpeed = maxSpeed;
        return c;
    }

    /**
     * Bewegt das GameObject in die angegebene Richtung.
     * @param direction die Richtung als Zahl
     * @see Direction
     */
    public void move(int direction, float speedMultiplier) {
        if(bodyComponent == null) {
            return;
        }
        if(direction == Direction.UP && bodyComponent.getBody().getLinearVelocity().y <= maxSpeed) {
            bodyComponent.getBody().applyLinearImpulse(new Vector2(0, maxSpeed * speedMultiplier), bodyComponent.getBody().getPosition(), true);
        }
        if(direction == Direction.RIGHT && bodyComponent.getBody().getLinearVelocity().x <= maxSpeed) {
            bodyComponent.getBody().applyLinearImpulse(new Vector2(maxSpeed * speedMultiplier, 0), bodyComponent.getBody().getPosition(), true);
        }
        if(direction == Direction.DOWN && bodyComponent.getBody().getLinearVelocity().y >= - maxSpeed) {
            bodyComponent.getBody().applyLinearImpulse(new Vector2(0, -maxSpeed * speedMultiplier), bodyComponent.getBody().getPosition(), true);
        }
        if(direction == Direction.LEFT && bodyComponent.getBody().getLinearVelocity().x >= - maxSpeed) {
            bodyComponent.getBody().applyLinearImpulse(new Vector2(-maxSpeed * speedMultiplier, 0), bodyComponent.getBody().getPosition(), true);
        }
        setLooking(direction);
    }

    /**
     * Bewegt das GameObject in die angegebene Richtung.
     * @param direction die Richtung als Zahl
     * @see Direction
     */
    public void move(int direction) {
        move(direction, 1f);
    }

    /**
     * Gibt die Richtung zurück, in die das GameObject gerade schaut.
     * @return die Richtung als Zahl
     * @see Direction
     */
    public int getLooking() {
        return looking;
    }

    /**
     * Setzt die Richtung, in die das GameObject gerade schaut. Dieser kann jedoch automatisch überschrieben werden und hält daher nur temporär an.
     * @param looking die Richtung als Zahl
     * @see Direction
     */
    public void setLooking(int looking) {
        this.looking = looking;
    }

    /**
     * Gibt den Bewegungszustand zurück, in dem sich das GameObject gerade befindet.
     * @return der Zustand als Zahl
     * @see MovementState
     */
    public int getState() {
        return state;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getSpeed() {
        return bodyComponent.getBody().getLinearVelocity().len();
    }
}
