package jnh.game.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import jnh.game.stages.GameStage;

public abstract class RigidObject extends GameObject {

    private World world;
    private Body body;

    public RigidObject(GameStage stage, Texture texture, Vector2 position, Vector2 dimension) {
        super(stage, texture, position, dimension);
        world = stage.getScreen().getWorld();
        createBody();
        getBody().setUserData(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(getBody().getPosition().x, getBody().getPosition().y);
        setRotation((float) Math.toDegrees(getBody().getAngle()));
    }

    @Override
    public boolean remove() {
        boolean remove = super.remove();
        getStage().getScreen().getWorld().destroyBody(body);
        return remove;
    }

    public abstract void createBody();

    public World getWorld() {
        return world;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
