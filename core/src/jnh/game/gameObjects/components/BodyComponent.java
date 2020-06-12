package jnh.game.gameObjects.components;

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

    private Body body;

    private BodyDef.BodyType bodyType = BodyDef.BodyType.DynamicBody;
    private float density = 1f;
    private float linearDamping = 10f;
    private boolean fixedRotation = true;

    public BodyComponent() {

    }

    /**
     * Setzt die entsprechenden Werte der Component.
     * @param parameters Übergebene Parameter als String-Array. {@code null} wird ignoriert, bestehende Werte also nicht überschireben.
     * @throws IllegalArgumentException
     */
    @Override
    public void set(String[] parameters) throws IllegalArgumentException {
        switch(Integer.parseInt(parameters[0])) {
            case 0:
                bodyType = BodyDef.BodyType.StaticBody;
                break;
            case 1:
                bodyType = BodyDef.BodyType.KinematicBody;
                break;
            case 2:
                bodyType = BodyDef.BodyType.DynamicBody;
                break;
            default:
        }
        density = (parameters[1] != null) ? Float.parseFloat(parameters[1]): density;
        linearDamping = (parameters[2] != null) ? Float.parseFloat(parameters[2]): linearDamping;
        fixedRotation = (parameters[3] != null) ? Boolean.parseBoolean(parameters[3]): fixedRotation;
    }

    @Override
    public void tick(float delta) {
        gameObject.setPosition(getBody().getPosition().x, getBody().getPosition().y);
    }

    @Override
    public void render() {

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
        shape.setAsBox(gameObject.getWidth() / 2, gameObject.getHeight() / 2, new Vector2(-0.15f, 0), 0);
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
        gameObject.getStage().getScreen().getWorld().destroyBody(body);
    }

    /**
     * Gibt den Body des GameObjects zurück.
     * @return den Body
     * @see Body
     */
    public Body getBody() {
        return body;
    }

    @Override
    public BodyComponent copy() {
        BodyComponent c = new BodyComponent();
        c.fixedRotation = fixedRotation;
        c.linearDamping = linearDamping;
        c.bodyType = bodyType;
        c.density = density;
        return c;
    }


}
