package jnh.game.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.components.Component;
import jnh.game.gfx.animations.Animator;
import jnh.game.stages.GameStage;

import java.util.ArrayList;

public class GameObject extends Image {

    private GameStage stage;

    private TextureRegion texture;
    private Animator animator;

    private ArrayList<Component> components = new ArrayList<>();

    public static final GameObject EMPTY = new GameObject(null, Assets.textures.ERROR, new Vector2(1, 1), new Vector2(1, 1));

    public GameObject(GameStage stage, Animation<TextureRegion> animation, Vector2 position, Vector2 dimension) {
        super(animation.getKeyFrame(0));
        this.stage = stage;
        setBounds(position.x, position.y, dimension.x, dimension.y);
        setOrigin(getWidth() / 2, getHeight() / 2);
        this.animator = new Animator();
    }

    @Override
    public final void act(float delta) {
        super.act(delta);
        tick(delta);
        animator.tick(delta);
        render();
        setTexture(animator.getTexture());
    }

    public void tick(float delta) {
        for(Component component: components) {
            component.act(delta);
        }
    }

    public void render() {

    }

    public GameStage getStage() {
        return stage;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
        setDrawable(new TextureRegionDrawable(texture));
    }

    public Animator getAnimator() {
        return animator;
    }

    public void addComponent(Component component) {
        components.add(component);
    }
}