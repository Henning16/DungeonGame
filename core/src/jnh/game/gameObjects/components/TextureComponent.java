package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;

public class TextureComponent extends Component {

    private Animation<TextureRegion> texture = Assets.textures.ERROR;
    private String textureString = "ERROR";
    private float elapsedTime = 0f;
    private boolean paused = true;

    @Override
    public void set(String[] parameters) throws Exception {
        textureString = (parameters[0] != null) ? parameters[0] : textureString;
        texture = (parameters[0] != null) ? (Animation<TextureRegion>) Assets.textures.getClass().getField(parameters[0]).get(Assets.textures) : texture;
        paused = (parameters[1] != null) ? Boolean.parseBoolean(parameters[1]) : paused;
    }

    @Override
    public String[] get() {
        String[] parameters = new String[2];
        parameters[0] = textureString;
        parameters[1] = String.valueOf(paused);
        return parameters;
    }

    @Override
    public void tick(float delta) {
        if(!paused) {
            elapsedTime += delta;
        }
    }

    @Override
    public void render(Batch batch) {
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
        TextureComponent c = new TextureComponent();
        c.texture = texture;
        c.textureString = textureString;
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
