package jnh.game.gameObjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.SnapshotArray;
import jnh.game.assets.Blueprints;
import jnh.game.assets.Tags;
import jnh.game.components.Component;
import jnh.game.components.ComponentHandler;
import jnh.game.components.textures.TextureComponent;
import jnh.game.gameObjects.construction.Blueprint;
import jnh.game.stages.GameStage;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * <p>
 * All objects in the actual game are represented using game objects.
 * They can be either loaded from {@link #GameObject(GameStage, GameObjectJson) a json representation}
 * or constructed using a {@link #GameObject(GameStage, Blueprint) json blueprint}.
 * <p>
 * Game objects get their function and unique properties via {@link Component components}.
 * These can be added during runtime or using {@link Blueprint blueprints}.
 * So, you can understand game objects as a class holding and managing the different components.
 * <p>
 * Though, every game object has a few properties like a position, dimension and a texture.
 * However, the last one should be accessed by using a component like {@link TextureComponent}.
 * Further properties are an id, which generated automatically, the type of the game object, usually specified using
 * the blueprint, the layer it is in and whether it is persistent (whether it stays when the room is switched, e.g.
 * the player).
 * <p>
 * Furthermore, each game object has a list of tags, which make it easier to address certain groups like game objects,
 * for example only those who are alive.
 */
public class GameObject extends Image {

    private transient Blueprint blueprint;
    private String type;
    private ID id = ID.NULL;
    private String layerAsString;

    private float zPosition = 0;
    private boolean flipX, flipY;

    private final transient GameStage stage;
    private transient GameObjectManager gameObjectManager;

    private boolean alreadyActed = false;
    private boolean removed = false;
    private boolean persistent;

    private transient TextureRegion texture;

    private final ArrayList<Tags> tags = new ArrayList<>();
    private final transient ArrayList<Tags> tagsToBeAdded = new ArrayList<>();
    private final transient ArrayList<Tags> tagsToBeRemoved = new ArrayList<>();
    private final ComponentHandler components = new ComponentHandler();

    private transient GameObjectJson gameObjectJson;

    public int indexInParent = -1;

    /**
     * <p style="color: blue;">Note: This methods needs to be cleaned up.</p>
     * Loads a new game object from a json representation. This is used when a scene is loaded.
     * @param stage the stage it should be in
     * @param gameObjectJson the json representation that should be used
     * @see #GameObject(GameStage, Blueprint) Create a new game object
     */
    public GameObject(GameStage stage, GameObjectJson gameObjectJson) {
        super();
        this.stage = stage;
        this.gameObjectJson = gameObjectJson;
    }

    /**
     * <p style="color: blue;">Note: This methods needs to be cleaned up.</p>
     * Creates a new game object using the specified blueprint.
     * @param stage the stage it should be in
     * @param blueprint the blueprint that should be used to create the game object
     * @see #GameObject(GameStage, GameObjectJson) Load a game object from json
     * @see Blueprint
     * @see Blueprints
     */
    public GameObject(GameStage stage, Blueprint blueprint) {
        super();
        this.stage = stage;
        this.gameObjectManager = stage.getGameObjectManager();
        this.blueprint = blueprint;
        this.type = blueprint.type;
        setBounds(blueprint.position.x, blueprint.position.y, blueprint.dimension.x, blueprint.dimension.y);
        setOrigin(getWidth() / 2, getHeight() / 2);
        switch (blueprint.layer) {
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
        layerAsString = blueprint.layer;

        for (Tags tag : blueprint.tags) {
            addTag(tag);
        }

        for (Component component : blueprint.components) {
            addComponent(component.copy());
            if(removed) {
                component.remove();
            }
        }
    }

    public void gameObjectManagerInitializationDone() {
        this.gameObjectManager = stage.getGameObjectManager();
        this.type = gameObjectJson.type;
        this.id = gameObjectJson.id;
        this.removed = gameObjectJson.removed;
        this.persistent = gameObjectJson.persistent;
        setBounds(gameObjectJson.position.x,gameObjectJson.position.y,gameObjectJson.dimension.x,gameObjectJson.dimension.y);
        setOrigin(getWidth() / 2, getHeight() / 2);
        if(!removed) {
            switch(gameObjectJson.layer) {
                case "main":
                    stage.getMainLayer().addActor(this);
                    break;
                case "foreground":
                    stage.getForegroundLayer().addActor(this);
                    break;
                case "background":
                    stage.getBackgroundLayer().addActor(this);
                    break;
            }
        }
        layerAsString = gameObjectJson.layer;

        for(Tags tag: gameObjectJson.tags) {
            addTag(tag);
        }

        for(Component component: gameObjectJson.components) {
            addComponent(component);
            if(removed) {
                component.remove();
            }
        }
        gameObjectJson = null;
    }

    /**
     * Calls {@link #tick(float)} when the game object has not acted in the tick before.
     * @param delta the time in seconds since the last tick
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

    /**
     * Sets if the game object already did act in the tick.
     * @param alreadyActed whether it already acted
     */
    public void setAlreadyActed(boolean alreadyActed) {
        this.alreadyActed = alreadyActed;
    }

    /**
     * Calls the {@link Component#tick(float)} method of all components and {@link #updateZPosition() updates the z-position}.
     * @param delta the time in seconds since the last tick
     * @see Component
     */
    public void tick(float delta) {
        components.tick(delta);
        updateZPosition();
    }

    /**
     * <p style="color: orange;">Note: This method might be changed soon.</p>
     * Draws the game object and draws possible further things on top of it by calling the {@link Component#render(Batch)}
     * method of all components.
     * @param batch the batch used for drawing
     * @param parentAlpha which alpha value the parent has
     * @see Component
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(getTexture() != null) {
            getTexture().flip(flipX, flipY);
        }
        super.draw(batch, parentAlpha);
        components.render(batch);
        if(getTexture() != null) {
            getTexture().flip(flipX, flipY);
        }

    }

    /**
     * Deletes the game object. This is method can be only used by the {@link GameObjectManager}! To delete a game objet
     * always use {@link GameObjectManager#remove(ID)} or {@link GameObjectManager#requestRemove(ID)}!
     * @return if the game object was successfully deleted or if it could not be found in the first place
     * @see #isRemoved()
     */
    @Override
    public boolean remove() {
        boolean remove = super.remove();
        components.remove();
        removed = true;
        return remove;
    }

    /**
     * Sets whether the game object is removed.
     * @param removed whether the game object is removed
     */
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    /**
     * @return if the game object was already removed
     * @see #remove()
     */
    public boolean isRemoved() {
        return removed;
    }

    /**
     * Returns the {@link Blueprint}, in other words the template, which was used to create the game object.
     * @return the blueprint
     * @see GameObject#GameObject(GameStage, Blueprint) 
     */
    public Blueprint getBlueprint() {
        return blueprint;
    }

    /**
     * Returns the type of the game object, e.g. {@code "PLAYER"} oder {@code "FLOOR"}.
     * This equivalent to {@link Blueprint#type}.
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the id, which is used to uniquely identify the game object.
     * @return the id
     * @see #setID(ID)
     */
    public ID getID() {
        return id;
    }

    /**
     * Sets the game objects id to the provided. Know that changing the id without knowing what you are doing can
     * cause serious problems. This method is mostly for the {@link GameObjectManager} and scene loading.
     * @see #getID()
     */
    public void setID(ID id) {
        this.id = id;
    }

    /**
     * @return the {@link GameStage stage} the game object is in
     * @see #GameObject(GameStage, Blueprint)
     * @see #GameObject(GameStage, GameObjectJson)
     */
    public GameStage getStage() {
        return stage;
    }

    /**
     * @return the {@link GameObjectManager}
     * @see #setGameObjectManager(GameObjectManager)
     */
    public GameObjectManager getGameObjectManager() {
        return stage.getGameObjectManager();
    }

    /**
     * @param gameObjectManager the new {@link GameObjectManager}
     * @see #getGameObjectManager()
     */
    public void setGameObjectManager(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
    }

    /**
     * @return the current texture region
     */
    public TextureRegion getTexture() {
        return texture;
    }

    /**
     * Sets the current texture to the provided texture region.
     * @param texture the texture region
     */
    public void setTexture(TextureRegion texture) {
        this.texture = texture;
        setDrawable(new TextureRegionDrawable(texture));
    }

    /**
     * Adds the provided tag to the game object if it is not null.
     * @param tag the tag to be added
     * @see #addTagWhileIterating(Tags) Add a tag while iterating over the tag list
     * @see #removeTag(Tags) Remove a tag while not iterating over the tag list
     * @see #removeTagWhileIterating(Tags) Remove a tag while iterating over the tag list
     */
    public void addTag(Tags tag) {
        if(tag != null) {
            if(!tags.contains(tag)) {
                tags.add(tag);
            }
            gameObjectManager.addGameObjectToTag(tag, getID());
        }
    }

    /**
     * Adds the provided tag to another list and adds it when the iteration is finished.
     * This avoids a {@link ConcurrentModificationException} from occurring.
     * You need to {@link #finishedTagOperations() notify} the game object when the iteration has ended.
     * @param tag the tag to be added
     * @see #addTag(Tags) Add tag while not iterating over the tag list
     * @see #removeTag(Tags) Remove a tag while not iterating over the tag list
     * @see #removeTagWhileIterating(Tags) Remove a tag while iterating over the tag list
     */
    public void addTagWhileIterating(Tags tag) {
        tags.add(tag);
        tagsToBeAdded.add(tag);
    }

    /**
     * Removes the tag if it is not null.
     * @param tag the tag to be removed
     * @see #addTag(Tags) Add tag while not iterating over the tag list
     * @see #addTagWhileIterating(Tags) Add a tag while iterating over the tag list
     * @see #removeTagWhileIterating(Tags) Remove a tag while iterating over the tag list
     */
    public void removeTag(Tags tag) {
        if(tag != null) {
            tags.remove(tag);
            gameObjectManager.removeGameObjectFromTag(tag, getID());
        }
    }

    /**
     * Adds the provided tag to another list and removes it when the iteration is finished.
     * This avoids a {@link ConcurrentModificationException} from occurring.
     * You need to {@link #finishedTagOperations() notify} the game object when the iteration has ended.
     * @param tag the tag to be removed
     * @see #addTag(Tags) Add tag while not iterating over the tag list
     * @see #addTagWhileIterating(Tags) Add a tag while iterating over the tag list
     * @see #removeTag(Tags) Remove a tag while not iterating over the tag list
     */
    public void removeTagWhileIterating(Tags tag) {
        tags.remove(tag);
        tagsToBeRemoved.add(tag);
    }

    /**
     * Returns whether the game object has a certain tag.
     * @param tag the tag that should be searched for
     * @return whether it has the tag
     */
    public boolean hasTag(Tags tag) {
        return tags.contains(tag);
    }

    /**
     * Call this method to notify the game object when the iteration is finished and the tags added using
     * {@link #addTagWhileIterating(Tags)} should be added and the tags removed using
     * {@link #removeTagWhileIterating(Tags)} should be removed.
     */
    public void finishedTagOperations() {
        for(Tags tag: tagsToBeAdded) {
            gameObjectManager.removeGameObjectFromTag(tag, getID());
        }
        tagsToBeAdded.clear();
        for(Tags tag: tagsToBeRemoved) {
            gameObjectManager.removeGameObjectFromTag(tag, getID());
        }
        tagsToBeRemoved.clear();
    }

    public ArrayList<Tags> getTags() {
        return tags;
    }

    /**
     * Adds the component to the game object if it is not null.
     * @param component the component to be added
     * @see Component
     */
    public void addComponent(Component component) {
        if(component != null) {
            components.add(component);
            component.attachedTo(this);
        }
    }

    /**
     * Returns the first component found of the specified class.
     * @param componentClass the class of the component
     * @return the component, can be null
     * @see Component
     */
    public <T> T getComponent(Class<T> componentClass) {
        return components.getComponent(componentClass);
    }

    /**
     * Returns the first component found implementing the specified interface
     * @param interfaceClass the interface of the component
     * @return the component, can be null
     * @see Component
     */
    public <T> T getComponentByInterface(Class<T> interfaceClass) {
        return components.getComponentByInterface(interfaceClass);
    }

    /**
     * Returns the list of components of this game object.
     * @return the list
     * @see #getComponent(Class)
     * @see #getComponentByInterface(Class)
     * @see Component
     */
    public ArrayList<Component> getComponents() {
        return components.getList();
    }

    /**
     * Returns the position of the game object. This is equivalent to either {@link #getX()} or {@link #getY()}.
     * @return the game objects position
     */
    public Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }

    /**
     * Sets the position of the game object to the specified position. This is a shortcut for
     * {@link #setPosition(float, float)}.
     * @param position the new position
     */
    public void setPosition(Vector2 position) {
        setPosition(position.x, position.y);
    }

    /**
     * @return whether the game object is persistent, i.e. stays when the scene is switched
     */
    public boolean isPersistent() {
        return persistent;
    }

    /**
     * @param persistent whether the game object should be persistent, i.e. if it should stay when the scene is switched
     */
    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }

    /**
     * @return the string representation of the layer the game object is in. Possible values are "main", "foreground"
     * and "background".
     */
    public String getLayerAsString() {
        return layerAsString;
    }

    /**
     * Checks whether the game object contains the specified position. This works only for rectangles right now.
     * @param position the position
     * @return whether the position is contained
     */
    public boolean contains(Vector2 position) {
        return new Rectangle(getX(), getY(), getWidth(), getHeight()).contains(position);
    }

    /**
     * Updates the z position, i.e. the index in its parent, by comparing the y-values with its neighbours and swapping
     * if necessary until it has the right index.
     */
    private void updateZPosition() {
        if(getParent() == null) {
            return;
        }
        SnapshotArray<Actor> actors = getParent().getChildren();
        if(indexInParent == -1) {
            indexInParent = actors.indexOf(this, true);
        }
        while(indexInParent - 1 > 0 && (getY() + zPosition) > (actors.get(indexInParent - 1).getY() + ((GameObject) actors.get(indexInParent - 1)).getzPosition())) {
            getParent().swapActor(indexInParent, indexInParent - 1);
            ((GameObject) actors.get(indexInParent)).indexInParent = indexInParent;
            indexInParent--;
        }
        while(indexInParent < actors.size - 1 && (getY() + zPosition) < (actors.get(indexInParent + 1).getY() + ((GameObject) actors.get(indexInParent + 1)).getzPosition())) {
            getParent().swapActor(indexInParent, indexInParent + 1);
            ((GameObject) actors.get(indexInParent)).indexInParent = indexInParent;
            indexInParent++;
        }
    }

    /**
     * Returns the modifier used for calculating z position.
     * @return the modifier
     * @see #updateZPosition()
     */
    public float getzPosition() {
        return zPosition;
    }

    /**
     * Sets the modifier used for calculating z position.
     * @param zPosition the modifier
     * @see #updateZPosition()
     */
    public void setzPosition(float zPosition) {
        this.zPosition = zPosition;
    }

    /**
     * Sets the flip of the game object.
     * @param x whether the game object should be flipped around the x-axis
     * @param y whether the game object should be flipped around the y-axis
     */
    public void setFlip(boolean x, boolean y) {
        this.flipX = x;
        this.flipY = y;
    }

    /**
     * @return whether the sprite is flipped around the x-axis
     */
    public boolean isFlipX() {
        return flipX;
    }

    /**
     * @return whether the sprite is flipped around the y-axis
     */
    public boolean isFlipY() {
        return flipY;
    }
}
