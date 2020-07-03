package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import jnh.game.gameObjects.GameObject;

/**
 * Verwaltet den möglichen Body, also die physikalischen Eigenschaften, des GameObjects.
 * @see Body
 * @see jnh.game.gameObjects.GameObject
 */
public class BodyComponent extends Component {

    private transient Body body;

    private BodyDef.BodyType bodyType = BodyDef.BodyType.DynamicBody;
    private float density = 1f;
    private float linearDamping = 10f;
    private boolean fixedRotation = true;
    private Vector2 collisionBoxPosition = new Vector2(0, 0);
    private Vector2 collisionBoxDimension = new Vector2(16, 16);
    private transient boolean alreadyDeleted = false;

    public BodyComponent() {

    }

    @Override
    public void tick(float delta) {
        gameObject.setPosition(getBody().getPosition().x, getBody().getPosition().y);
    }

    @Override
    public void render(Batch batch) {
        super.render(batch);
    }

    @Override
    public void attachedTo(GameObject gameObject) {
        super.attachedTo(gameObject);
        BodyDef def = new BodyDef();
        def.type = bodyType;
        def.position.set(gameObject.getX(), gameObject.getY());
        def.linearDamping = linearDamping;
        def.fixedRotation = fixedRotation;
        body = gameObject.getStage().getScreen().getWorld().createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((1f / 32f) * collisionBoxDimension.x, (1f / 32f) * collisionBoxDimension.y, new Vector2((1f / 16f) * collisionBoxPosition.x, (1f / 16f) * collisionBoxPosition.y), 0);
        getBody().createFixture(shape, density);
        shape.dispose();
        body.setUserData(gameObject);
    }

    /**
     * Löscht den Body aus der Welt.
     * @see World
     */
    @Override
    public void remove() {
        if(!alreadyDeleted) {
            gameObject.getStage().getScreen().getWorld().destroyBody(body);
            alreadyDeleted = true;
        }
    }

    @Override
    public BodyComponent copy() {
        BodyComponent c = new BodyComponent();
        c.fixedRotation = fixedRotation;
        c.linearDamping = linearDamping;
        c.bodyType = bodyType;
        c.density = density;
        c.collisionBoxPosition = collisionBoxPosition;
        c.collisionBoxDimension = collisionBoxDimension;
        return c;
    }

    /**
     * Gibt den Body des GameObjects zurück.
     * @return den Body
     * @see Body
     */
    public Body getBody() {
        return body;
    }

}
