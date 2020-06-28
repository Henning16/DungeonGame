package jnh.game.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.SnapshotArray;
import jnh.game.gameObjects.components.Component;
import jnh.game.gameObjects.components.ComponentHandler;
import jnh.game.gameObjects.construction.Blueprint;
import jnh.game.gfx.animations.Animator;
import jnh.game.stages.GameStage;

import java.util.ArrayList;

public class GameObject extends Image {

    private transient Blueprint blueprint;
    private String type;
    private int id;

    private transient GameStage stage;
    private transient GameObjectManager gameObjectManager;

    private boolean alreadyActed = false;
    private boolean removed = false;

    private transient TextureRegion texture;

    private ComponentHandler components = new ComponentHandler();

    public int indexInParent = -1;

    public GameObject(GameStage stage, Blueprint blueprint) {
        this.stage = stage;
        this.gameObjectManager = stage.getGameObjectManager();
        this.blueprint = blueprint;
        this.type = blueprint.type;
        setBounds(blueprint.position.x,blueprint.position.y,blueprint.dimension.x,blueprint.dimension.y);
        setOrigin(getWidth() / 2, getHeight() / 2);
        switch(blueprint.layer) {
            case "main":
                this.id = getGameObjectManager().add(this, stage.getMainLayer());
                break;
            case "foreground":
                this.id = getGameObjectManager().add(this, stage.getForegroundLayer());
                break;
            case "background":
                this.id = getGameObjectManager().add(this, stage.getBackgroundLayer());
                break;
        }
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
    }

    /**
     * Aktualisiert den {@link Animator} und ruft {@link #tick(float)} auf.
     * @param delta die Zeit in Sekunden seit dem letzten Tick
     */
    @Override
    public final void act(float delta) {
        if(alreadyActed) {
            return;
        }
        super.act(delta);
        tick(delta);
        alreadyActed = true;
    }

    public void setAlreadyActed(boolean alreadyActed) {
        this.alreadyActed = alreadyActed;
    }

    /**
     * Führt die {@link Component#tick(float)} aller Components aus.
     * @param delta die Zeit in Sekunden seit dem letzten Tick
     * @see Component
     */
    public void tick(float delta) {
        components.tick(delta);
        updateZPosition();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        components.render(batch);
    }

    /**
     * Löscht das GameObject. Diese Methode darf nur vom {@link GameObjectManager} benutzt werden! Zum Löschen eines GameObjects immer {@link GameObjectManager#remove(int)} oder {@link GameObjectManager#requestRemove(int)} benutzen!
     * @return ob das GameObject gelöscht wurde, oder es schon gelöscht war
     */
    @Override
    public boolean remove() {
        boolean remove = super.remove();
        components.remove();
        removed = true;
        return remove;
    }

    public boolean isRemoved() {
        return removed;
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
    public <T> T getComponent(Class<T> componentClass) {
        return components.getComponent(componentClass);
    }

    /**
     * Gibt die erste Component, die das angegebene Interface implementiert, zurück, die gefunden werden konnte.
     * @param interfaceClass das Interface der Component
     * @return die Component
     * @see Component
     */
    public <T> T getComponentByInterface(Class<T> interfaceClass) {
        return components.getComponentByInterface(interfaceClass);
    }

    /**
     * Gibt eine Liste mit allen Components zurück.
     * @return die Components
     * @see #getComponent(Class)
     * @see Component
     * @see ArrayList
     */
    public ArrayList<Component> getComponents() {
        return components.getList();
    }

    public Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }

    /**
     * Überprüft, ob die angegebene Position im GameObject enthalten ist. Das funktioniert bis jetzt nur bei rechteckigen GameObjects.
     * @param position die zu überprüfende Position
     * @return ob die Position enthalten ist.
     */
    public boolean contains(Vector2 position) {
        return new Rectangle(getX(), getY(), getWidth(), getHeight()).contains(position);
    }

    private void updateZPosition() {
        if(getParent() == null) {
            return;
        }
        SnapshotArray<Actor> actors = getParent().getChildren();
        if(indexInParent == -1) {
            indexInParent = actors.indexOf(this, true);
        }
        while(indexInParent - 1 > 0 && getY() > actors.get(indexInParent - 1).getY()) {
            getParent().swapActor(indexInParent, indexInParent - 1);
            ((GameObject) actors.get(indexInParent)).indexInParent = indexInParent;
            indexInParent--;
        }
        while(indexInParent < actors.size - 1 && getY() < actors.get(indexInParent + 1).getY()) {
            getParent().swapActor(indexInParent, indexInParent + 1);
            ((GameObject) actors.get(indexInParent)).indexInParent = indexInParent;
            indexInParent++;
        }
    }
}