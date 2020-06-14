package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;

public class IndexedTextureComponent extends Component {

    private Animation<TextureRegion>[] textures = new Animation[] {Assets.textures.ERROR};
    private String texturesString = "ERROR";
    private Animation<TextureRegion> texture = Assets.textures.ERROR;
    private int index = 0;
    private float elapsedTime = 0f;
    private boolean paused = true;

    @Override
    public void set(String[] parameters) throws Exception {
        texturesString = (parameters[0] != null) ? parameters[0] : texturesString;
        textures = (parameters[0] != null) ? (Animation<TextureRegion>[]) Assets.textures.getClass().getField(parameters[0]).get(Assets.textures) : textures;
        index = (parameters[1] != null) ? Integer.parseInt(parameters[1]) : index;
        paused = (parameters[2] != null) ? Boolean.parseBoolean(parameters[2]) : paused;
        texture = textures[index];
    }

    @Override
    public String[] get() {
        String[] parameters = new String[3];
        parameters[0] = texturesString;
        parameters[1] = String.valueOf(index);
        parameters[2] = String.valueOf(paused);
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