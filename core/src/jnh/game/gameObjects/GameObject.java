package jnh.game.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
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
    private int id;

    private GameStage stage;
    private GameObjectManager gameObjectManager;

    private TextureRegion texture;
    private Animator animator;

    private ArrayList<Component> components = new ArrayList<>();

    public GameObject(GameStage stage, Blueprint blueprint) {
        this.stage = stage;
        this.gameObjectManager = stage.getGameObjectManager();
        this.blueprint = blueprint;
        this.type = blueprint.type;
        setBounds(1,1,1,1);
        setOrigin(getWidth() / 2, getHeight() / 2);
        this.id = getGameObjectManager().add(this, stage.getMainLayer());
        for(Component component: blueprint.components) {
            addComponent(component.copy());
        }
    }

    //TODO add constructor accepting specific GameObject data

    @Deprecated
    public GameObject(GameStage stage, Animation<TextureRegion> animation, Vector2 position, Vector2 dimension) {
        super(animation.getKeyFrame(0));
        this.stage = stage;
        setBounds(position.x, position.y, dimension.x, dimension.y);
        setOrigin(getWidth() / 2, getHeight() / 2);
        this.animator = new Animator();
    }

    /**
     * Aktualisiert den {@link Animator} und ruft {@link #tick(float)} auf.
     * @param delta die Zeit in Sekunden seit dem letzten Tick
     */
    @Override
    public final void act(float delta) {
        super.act(delta);
        tick(delta);
        if(animator != null) {
            animator.tick(delta);
        }
        if(animator != null) {
            setTexture(animator.getTexture());
        }
    }

    /**
     * Führt die {@link Component#tick(float)} aller Components aus.
     * @param delta die Zeit in Sekunden seit dem letzten Tick
     * @see Component
     */
    public void tick(float delta) {
        for(Component component: components) {
            component.tick(delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for(Component component: components) {
            component.render(batch);
        }
    }

    /**
     * Löscht das GameObject. Diese Methode darf nur vom {@link GameObjectManager} benutzt werden! Zum Löschen eines GameObjects immer {@link GameObjectManager#remove(int)} oder {@link GameObjectManager#requestRemove(int)} benutzen!
     * @return ob das GameObject gelöscht wurde, oder es schon gelöscht war
     */
    @Override
    public boolean remove() {
        boolean remove = super.remove();
        for(Component component: components) {
            component.remove();
        }
        return remove;
    }


    /**
     * Gibt den {@link Blueprint}, also die Vorlage, zurück, aus dem das GameObject erschaffen wurde.
     * @return der Blueprint, aus dem das GameObject erschaffen wurde
     * @see GameObject#GameObject(GameStage, Blueprint) 
     */
    public Blueprint getBlueprint() {
        return blueprint;
    }

    /**
     * Gibt den Typ des GameObjects zurück. Also zum Beispiel {@code PLAYER} oder {@code ZOMBIE}. Das hat den gleichen Effekt wie {@link Blueprint#type}.
     * @return der Typ
     */
    public String getType() {
        return type;
    }

    public int getID() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gibt die {@link GameStage} zurück.
     * @return die GameStage
     */
    public GameStage getStage() {
        return stage;
    }

    /**
     * Gibt den {@link GameObjectManager} zurück.
     * @return der GameObjectManager
     */
    public GameObjectManager getGameObjectManager() {
        return gameObjectManager;
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


    /**
     * Fügt die Compnent dem GameObject hinzu.
     * @param component die hinzuzufügende Component
     */
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

    /**
     * Gibt eine Liste mit allen Components zurück.
     * @return die Components
     * @see #getComponent(Class)
     * @see Component
     * @see ArrayList
     */
    public ArrayList<Component> getComponents() {
        return components;
    }

    /**
     * Überprüft, ob die angegebene Position im GameObject enthalten ist. Das funktioniert bis jetzt nur bei rechteckigen GameObjects.
     * @param position die zu überprüfende Position
     * @return ob die Position enthalten ist.
     */
    public boolean contains(Vector2 position) {
        return new Rectangle(getX(), getY(), getWidth(), getHeight()).contains(position);
    }
}