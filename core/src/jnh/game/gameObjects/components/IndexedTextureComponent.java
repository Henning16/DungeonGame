package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;

public class IndexedTextureComponent extends Component {

    private transient Animation<TextureRegion>[] textures = new Animation[] {Assets.textures.ERROR};
    private String texturesString = "ERROR";
    private transient Animation<TextureRegion> texture = Assets.textures.ERROR;
    private int index = 0;
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
        gameObject.setTexture(texture.getKeyFrame(elapsedTime));
    }

    @Override
    public IndexedTextureComponent copy() {
        IndexedTextureComponent c = new IndexedTextureComponent();
        c.textures = textures;
        c.texture = texture;
        c.index = index;
        c.elapsedTime = elapsedTime;
        c.paused = paused;
        return c;
    }
}