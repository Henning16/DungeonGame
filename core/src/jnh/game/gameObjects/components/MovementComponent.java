package jnh.game.gameObjects.components;

import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.entities.MovementState;
import jnh.game.utils.Direction;

/**
 * Verwaltet die manuellen Bewegungen eines Objekts in der Welt, wie zum Beispiel die Bewegungen von Lebewesen. Diese Component enthält keine AI oder Steuerung und muss von anderen Components verwendet werden, um Sinn zu machen.
 */
public class MovementComponent extends Component {

    private BodyComponent bodyComponent;

    //TODO implement maxSpeed
    private int maxSpeed;

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

    }

    @Override
    public void tick(double delta) {
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
    public void render() {

    }

    @Override
    public void remove() {

    }

    @Override
    public MovementComponent copy() {
        MovementComponent c = new MovementComponent();
        return c;
    }

    /**
     * Bewegt das GameObject in die angegebene Richtung.
     * @param direction die Richtung als Zahl
     * @see Direction
     */
    public void move(int direction) {
        if(bodyComponent == null) {
            return;
        }
        if(direction == Direction.UP) {
            bodyComponent.getBody().applyLinearImpulse(new Vector2(0, 0.5f), bodyComponent.getBody().getPosition(), true);
        }
        if(direction == Direction.RIGHT) {
            bodyComponent.getBody().applyLinearImpulse(new Vector2(0.5f, 0), bodyComponent.getBody().getPosition(), true);
        }
        if(direction == Direction.DOWN) {
            bodyComponent.getBody().applyLinearImpulse(new Vector2(0, -0.5f), bodyComponent.getBody().getPosition(), true);
        }
        if(direction == Direction.LEFT) {
            bodyComponent.getBody().applyLinearImpulse(new Vector2(-0.5f, 0), bodyComponent.getBody().getPosition(), true);
        }
        setLooking(direction);
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
}
