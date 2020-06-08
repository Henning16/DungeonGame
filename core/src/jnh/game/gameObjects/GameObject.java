package jnh.game.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.components.Component;
import jnh.game.gameObjects.construction.Blueprint;
import jnh.game.gfx.animations.Animator;
import jnh.game.stages.GameStage;

import java.util.ArrayList;

public class GameObject extends Image {

    private Blueprint blueprint;
    private String type;

    private GameStage stage;

    private TextureRegion texture;
    private Animator animator;

    private ArrayList<Component> components = new ArrayList<>();

    public static final GameObject EMPTY = new GameObject(null, Assets.textures.ERROR, new Vector2(1, 1), new Vector2(1, 1));

    public GameObject(Blueprint blueprint) {
        this.blueprint = blueprint;
        //TODO check if this really works
        this.components = new ArrayList<>(blueprint.components);
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
        animator.tick(delta);
        render();
        setTexture(animator.getTexture());
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