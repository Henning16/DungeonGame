package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;

public class RenderComponent extends Component {

    private Animation<TextureRegion> texture = Assets.textures.ERROR;
    private float elapsedTime = 0f;
    private boolean paused = true;

    @Override
    public void set(String[] parameters) throws Exception {
        texture = (parameters[0] != null) ? (Animation<TextureRegion>) Assets.textures.getClass().getField(parameters[0]).get(Assets.textures) : texture;
        paused = (parameters[1] != null) ? Boolean.parseBoolean(parameters[1]) : paused;
    }

    @Override
    public void tick(double delta) {
        if(!paused) {
            elapsedTime += delta;
        }
    }

    @Override
    public void render() {
        if(!paused) {
            gameObject.setTexture(texture.getKeyFrame(elapsedTime));
        }
    }

    @Override
    public void remove() {

    }

    @Override
    public void attachedTo(GameObject gameObject) {
        super.attachedTo(gameObject);
        gameObject.setTexture(texture.getKeyFrame(elapsedTime));
    }

    @Override
    public Component copy() {
        RenderComponent c = new RenderComponent();
        c.texture = texture;
        c.paused = paused;
        return c;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
