package jnh.game.components.textures;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jnh.game.assets.Assets;
import jnh.game.components.Component;
import jnh.game.gameObjects.GameObject;

public class Indexed2TextureComponent extends Component {

    private String textureSetName = "ERROR";
    private int[] index = {0, 0};
    private transient Animation<TextureRegion>[][] textureSet = new Animation[][] {{Assets.textures.ERROR}};
    private transient Animation<TextureRegion> texture = Assets.textures.ERROR;

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
        super.render(batch);
        if(!paused) {
            gameObject.setTexture(texture.getKeyFrame(elapsedTime));
        }
    }

    public void attachedTo(GameObject gameObject) {
        super.attachedTo(gameObject);
        textureSet = Assets.textures.getTextureSet2(textureSetName);
        setTextureIndex(index);
    }

    @Override
    public Indexed2TextureComponent copy() {
        Indexed2TextureComponent c = new Indexed2TextureComponent();
        c.textureSetName = textureSetName;
        c.index = index;
        c.paused = paused;
        return c;
    }

    public void setTextureIndex(int[] index) {
        if(index[0] < 0 || index[0] >= textureSet.length || index[1] < 0 || index[1] >= textureSet[index[0]].length) throw new IndexOutOfBoundsException("Index not found");
        texture = textureSet[index[0]][index[1]];
        gameObject.setTexture(texture.getKeyFrame(elapsedTime));
    }
}