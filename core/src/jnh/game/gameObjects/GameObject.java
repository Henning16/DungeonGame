package jnh.game.gameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import jnh.game.stages.GameStage;

public abstract class GameObject extends Image {

    private GameStage stage;
    private TextureRegion texture;

    public GameObject (GameStage stage, TextureRegion texture, Vector2 position, Vector2 dimension) {
        super (texture);
        this.stage = stage;
        setBounds (position.x, position.y, dimension.x, dimension.y);
        setOrigin (getWidth () / 2, getHeight () / 2);
    }

    @Override
    public void act (float delta) {
        super.act (delta);
    }

    public GameStage getStage () {
        return stage;
    }

    public TextureRegion getTexture () {
        return texture;
    }

    public void setTexture (TextureRegion texture) {
        this.texture = texture;
        setDrawable (new TextureRegionDrawable (texture));
    }

}