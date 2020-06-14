package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import jnh.game.gameObjects.GameObject;
import jnh.game.utils.TimeHandler;

public class ItemComponent extends Component {

    @Override
    public void set(String[] parameters) throws Exception {

    }

    @Override
    public String[] get() {
        return new String[0];
    }

    @Override
    public void tick(float delta) {
        gameObject.setPosition(gameObject.getX(), (float) (gameObject.getY() + 0.005f * Math.sin(0.1f * TimeHandler.getTicks())));
    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public void remove() {
        //TODO removing system
    }

    @Override
    public void attachedTo(GameObject gameObject) {
        super.attachedTo(gameObject);
        gameObject.getGameObjectManager().items.add(gameObject.getID());
    }

    @Override
    public ItemComponent copy() {
        ItemComponent c = new ItemComponent();
        return c;
    }
}
