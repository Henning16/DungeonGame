package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
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
    private transient int oldIndex = 0;
    private CollisionBox[] collisionBoxes = new CollisionBox[0];
    private transient boolean alreadyDeleted = false;

    public BodyComponent() {
    }

    @Override
    public void tick(float delta) {
        if(gameObject.getType().equals("PLAYER") && body.getLinearVelocity().len2() < 0.8f) {
            body.setLinearVelocity(0, 0);
        }
        if(gameObject.getX() != getBody().getPosition().x ||gameObject.getY() != getBody().getPosition().y) {
            gameObject.setPosition(getBody().getPosition().x, getBody().getPosition().y);
        }
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
        body = gameObject.getStage().getScreen().getPhysicsWorld().createBody(def);

        CollisionBox collisionBox = new CollisionBox();
        try {
            if(collisionBoxes[0] != null) {
                collisionBox = collisionBoxes[0];
            }
        } catch(ArrayIndexOutOfBoundsException e) {
        }

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((1f / 32f) * collisionBox.dimension.x, (1f / 32f) * collisionBox.dimension.y, new Vector2((1f / 16f) * collisionBox.position.x, (1f / 16f) * collisionBox.position.y), 0);getBody().createFixture(shape, density);
        shape.dispose();
        body.setSleepingAllowed(false);
    }

    /**
     * Löscht den Body aus der Welt.
     * @see World
     */
    @Override
    public void remove() {
        if(!alreadyDeleted) {
            gameObject.getStage().getScreen().getPhysicsWorld().destroyBody(body);
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
        c.collisionBoxes = collisionBoxes;
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

    public void setCollisionBox(int index) {
        try {
            if(collisionBoxes[index] != null) {
                updateCollisionBox(collisionBoxes[index]);
            }
        } catch(ArrayIndexOutOfBoundsException e) { }
    }

    public void updateCollisionBox(CollisionBox collisionBox) {
        CollisionBox oldCollisionBox = new CollisionBox();
        try {
            if(collisionBoxes[oldIndex] != null) {
                oldCollisionBox = collisionBoxes[oldIndex];
            }
        } catch(ArrayIndexOutOfBoundsException e) { }
        for(Fixture fixture: body.getFixtureList()) {
            ((PolygonShape) fixture.getShape()).setAsBox((1f / 32f) * collisionBox.dimension.x, (1f / 32f) * collisionBox.dimension.y, new Vector2((1f / 16f) * collisionBox.position.x, (1f / 16f) * collisionBox.position.y), 0);
        }
    }

}
