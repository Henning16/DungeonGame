package jnh.game.gameObjects.components;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class BodyComponent extends Component {

    private World world;
    private Body body;

    @Override
    public void set(String[] parameters) throws IllegalArgumentException {

    }

    @Override
    public void tick(double delta) {

    }

    @Override
    public void render() {

    }

    @Override
    public void remove() {
        gameObject.getStage().getScreen().getWorld().destroyBody(body);
        body.setUserData(gameObject);
    }

    public Body getBody() {
        return body;
    }
}
