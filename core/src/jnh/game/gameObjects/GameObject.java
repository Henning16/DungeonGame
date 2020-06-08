package jnh.game.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import jnh.game.gameObjects.components.Component;
import jnh.game.gameObjects.construction.Blueprint;
import jnh.game.gfx.animations.Animator;
import jnh.game.stages.GameStage;

import java.util.ArrayList;

public class GameObject extends Image {

    private Blueprint blueprint;
    private String type;

    private GameStage stage;

    @Deprecated
    private TextureRegion texture;
    @Deprecated
    private Animator animator;

    private ArrayList<Component> components = new ArrayList<>();

    public GameObject(GameStage stage, Blueprint blueprint) {
        this.stage = stage;
        this.blueprint = blueprint;
        setBounds(1,1,1,1);
        setOrigin(getWidth() / 2, getHeight() / 2);
        for(Component component: blueprint.components) {
            addComponent(component.copy());
        }
        this.type = blueprint.type;
    }

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
        if(animator != null) {
            animator.tick(delta);
        }
        render();
        if(animator != null) {
            setTexture(animator.getTexture());
        }
    }

    public void tick(float delta) {
        for(Component component: components) {
            component.tick(delta);
        }
    }

    public void render() {
        for(Component component: components) {
            component.render();
        }
    }

    @Override
    public boolean remove() {
        boolean remove = super.remove();
        for(Component component: components) {
            component.remove();
        }
        return remove;
    }

    public Blueprint getBlueprint() {
        return blueprint;
    }

    public String getType() {
        return type;
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
        component.attachedTo(this);
    }

    /**
     * Gibt die erste Component des angegebenen Typs zurück, die gefunden werden konnte.
     * @param componentClass die Klasse der Component
     * @return die Component
     * @see Component
     */
    public Component getComponent(Class componentClass) {
        for(Component component: components) {
            if(component.getClass() == componentClass) {
                return component;
            }
        }
        return null;
    }
}