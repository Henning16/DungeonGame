package jnh.game.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import jnh.game.stages.GameStage;

public abstract class GameObject extends Image {

    private GameStage stage;

    private Texture texture;

    public GameObject(GameStage stage, Texture texture, Vector2 position, Vector2 dimension) {
        super(texture);
        this.stage = stage;
        setBounds(position.x, position.y, dimension.x, dimension.y);
        setOrigin(getWidth() / 2, getHeight() / 2);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public GameStage getStage() {
        return stage;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));
    }

    public void setTexture(TextureRegion textureRegion) {
        setDrawable(new TextureRegionDrawable(textureRegion));
    }
}