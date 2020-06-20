package jnh.game.gameObjects.components;

import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;

public class Measurements extends Component {

    private Vector2 position = new Vector2(0,0);
    private Vector2 dimension = new Vector2(1, 1);

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
