package jnh.game.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import jnh.game.gameObjects.behaviors.Behavior;
import jnh.game.gfx.animations.Animator;
import jnh.game.stages.GameStage;

import java.util.ArrayList;

public abstract class GameObject extends Image {

    private GameStage stage;

    private TextureRegion texture;
    private Animator animator;

    private ArrayList<Behavior> behaviors = new ArrayList<>();

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
        for(Behavior behavior: behaviors) {
            behavior.act(delta);
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

    public void addBehavior(Behavior behavior) {
        behaviors.add(behavior);
    }
}