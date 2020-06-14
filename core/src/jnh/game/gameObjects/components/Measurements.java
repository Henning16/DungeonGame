package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;

public class Measurements extends Component {

    private Vector2 position = new Vector2(0,0);
    private Vector2 dimension = new Vector2(1, 1);

    @Override
    public void set(String[] parameters) throws Exception {
        position.x = (parameters[0] != null) ? Float.parseFloat(parameters[0]) : position.x;
        position.y = (parameters[1] != null) ? Float.parseFloat(parameters[1]) : position.y;
        dimension.x = (parameters[2] != null) ? Float.parseFloat(parameters[2]) : dimension.x;
        dimension.y = (parameters[3] != null) ? Float.parseFloat(parameters[3]) : dimension.y;
        if(gameObject != null) {
            gameObject.setBounds(position.x, position.y, dimension.x, dimension.y);
        }
    }

    @Override
    public String[] get() {
        String[] parameters = new String[4];
        parameters[0] = String.valueOf(gameObject.getX());
        parameters[1] = String.valueOf(gameObject.getY());
        parameters[2] = String.valueOf(gameObject.getWidth());
        parameters[3] = String.valueOf(gameObject.getHeight());
        return parameters;
    }

    @Override
    public void tick(float delta) {
    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public void remove() {

    }

    @Override
    public void attachedTo(GameObject gameObject) {
        super.attachedTo(gameObject);
        gameObject.setBounds(position.x, position.y, dimension.x, dimension.y);
    }

    @Override
    public Component copy() {
        Measurements c = new Measurements();
        c.position = new Vector2(position.x, position.y);
        c.dimension = new Vector2(dimension.x, dimension.y);
        return c;
    }
}
