package jnh.game.components.textures;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jnh.game.assets.Assets;
import jnh.game.components.Component;
import jnh.game.gameObjects.GameObject;

public class TextureComponent extends Component {

    private String textureName = "ERROR";
    private transient Animation<TextureRegion> texture = Assets.textures.ERROR;
    private transient float elapsedTime = 0f;
    private boolean paused = true;

    @Override
    public void tick(float delta) {
        super.tick(delta);
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
    public void attachedTo(GameObject gameObject) {
        super.attachedTo(gameObject);
        texture = Assets.textures.getTexture(textureName);
        gameObject.setTexture(texture.getKeyFrame(elapsedTime));
    }

    @Override
    public Component copy() {
        TextureComponent c = new TextureComponent();
        c.textureName = textureName;
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
