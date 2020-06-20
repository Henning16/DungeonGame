package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;

public class RandomTextureComponent extends Component {

    private String textureSetName = "ERROR";
    private transient Animation<TextureRegion>[] textureSet = new Animation[]{Assets.textures.ERROR};
    private transient Animation<TextureRegion> texture;

    private transient float elapsedTime = 0f;
    private boolean paused = true;

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
    public void attachedTo(GameObject gameObject) {
        super.attachedTo(gameObject);
        textureSet = Assets.textures.getTextureSet(textureSetName);
        texture = textureSet[(int) (Math.random() * textureSet.length)];
        gameObject.setTexture(texture.getKeyFrame(elapsedTime));
    }

    @Override
    public RandomTextureComponent copy() {
        RandomTextureComponent c = new RandomTextureComponent();
        c.textureSetName = textureSetName;
        c.elapsedTime = elapsedTime;
        c.paused = paused;
        return c;
    }
}
