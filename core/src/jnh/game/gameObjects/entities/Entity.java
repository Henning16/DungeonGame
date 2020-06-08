package jnh.game.gameObjects.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import jnh.game.gameObjects.RigidObject;
import jnh.game.gameObjects.items.itemContainers.ItemContainer;
import jnh.game.stages.GameStage;
import jnh.game.utils.Direction;

public abstract class Entity extends RigidObject {

    public static final float DEFAULT_ENTITY_WIDTH = 0.6875f;
    public static final float DEFAULT_ENTITY_HEIGHT = 1f;

    private int looking = Direction.UP;

    private int state = MovementState.IDLE;
    private final ItemContainer itemContainer;

    public Entity(GameStage stage, Animation<TextureRegion> animation, Vector2 position, Vector2 dimension) {
        super(stage, animation, position, dimension);
        itemContainer = new ItemContainer(32);
    }

    @Override
    public void tick(float delta) {
        super.tick(delta);
        updateWalkingState(delta);
    }

    @Override
    public void createBody() {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(getX(), getY());
        def.linearDamping = 10f;
        def.fixedRotation = true;
        setBody(getStage().getScreen().getWorld().createBody(def));

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, getHeight() / 2);
        shape.setAsBox(getWidth() / 2, getHeight() / 2, new Vector2(-0.15f, 0), 0);
        getBody().createFixture(shape, 1f);
        shape.dispose();
    }

    public void move(int direction) {
        if(direction == Direction.UP) {
            getBody().applyLinearImpulse(new Vector2(0, 0.5f), getBody().getPosition(), true);
        }
        if(direction == Direction.RIGHT) {
            getBody().applyLinearImpulse(new Vector2(0.5f, 0), getBody().getPosition(), true);
        }
        if(direction == Direction.DOWN) {
            getBody().applyLinearImpulse(new Vector2(0, -0.5f), getBody().getPosition(), true);
        }
        if(direction == Direction.LEFT) {
            getBody().applyLinearImpulse(new Vector2(-0.5f, 0), getBody().getPosition(), true);
        }
        setLooking(direction);
    }

    private void updateWalkingState(float delta) {
        if(getBody().getLinearVelocity().len() > 0.5) {
            state = MovementState.WALK;
        } else {
            state = MovementState.IDLE;
        }
    }

    public ItemContainer getItemContainer() {
        return itemContainer;
    }

    public int getLooking() {
        return looking;
    }

    public void setLooking(int looking) {
        this.looking = looking;
    }

    public int getState() {
        return state;
    }
}
