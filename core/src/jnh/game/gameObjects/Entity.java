package jnh.game.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import jnh.game.gameObjects.items.ItemContainer;
import jnh.game.input.Direction;
import jnh.game.stages.GameStage;

public class Entity extends RigidObject {

    private ItemContainer itemContainer;

    public Entity(GameStage stage, Texture texture, Vector2 position, Vector2 dimension) {
        super(stage, texture, position, dimension);
        itemContainer = new ItemContainer(32);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
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
        getBody().createFixture(shape, 1f);
        shape.dispose();
    }

    public void move(Direction direction) {
        if(direction == Direction.UP) {
            getBody().applyLinearImpulse(new Vector2(0, 0.5f), getBody().getPosition(), true);
            getBody().setTransform(getBody().getPosition().x, getBody().getPosition().y, (float) Math.toRadians(180));
        }
        if(direction == Direction.RIGHT) {
            getBody().applyLinearImpulse(new Vector2(0.5f, 0), getBody().getPosition(), true);
            getBody().setTransform(getBody().getPosition().x, getBody().getPosition().y, (float) Math.toRadians(90));
        }
        if(direction == Direction.DOWN) {
            getBody().applyLinearImpulse(new Vector2(0, -0.5f), getBody().getPosition(), true);
            getBody().setTransform(getBody().getPosition().x, getBody().getPosition().y, (float) Math.toRadians(0));
        }
        if(direction == Direction.LEFT) {
            getBody().applyLinearImpulse(new Vector2(-0.5f, 0), getBody().getPosition(), true);
            getBody().setTransform(getBody().getPosition().x, getBody().getPosition().y, (float) Math.toRadians(270));
        }
    }

    public ItemContainer getItemContainer() {
        return itemContainer;
    }
}
