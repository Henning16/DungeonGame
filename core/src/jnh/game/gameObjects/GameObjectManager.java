package jnh.game.gameObjects;

import com.badlogic.gdx.scenes.scene2d.Group;
import jnh.game.assets.Tags;
import jnh.game.stages.GameStage;

import java.util.*;

/**
 * The GameObjectManager handles the references to all game objects in the given scene, no matter if they are still
 * in the stage or already removed.
 */
public class GameObjectManager {

    private transient GameStage stage;

    private GameObject[] gameObjects = new GameObject[100];

    private final ArrayList<ID> toBeRemoved = new ArrayList<>();

    private transient final Map<Tags, ArrayList<ID>> tags = new LinkedHashMap<>();

    public ID playerID;

    private final Queue<Integer> freeSlots = new LinkedList<>();
    private int firstFreeSlot = 0;
    private transient long uniqueIDCounter = 0;

    /**
     * Creates a new instance. If this constructor is used, the stage needs to bet set using {@link #setStage(GameStage)}.
     * @see #GameObjectManager(GameStage)
     */
    public GameObjectManager() {

    }

    /**
     * Creates a new instance.
     * @see #GameObjectManager()
     */
    public GameObjectManager(GameStage stage) {
        this.stage = stage;
    }

    /**
     * Adds the specified game object to the main layer.
     * @param gameObject the game object to be added
     * @return the newly calculated id which is associated with the game object
     * @see #add(GameObject, Group)
     */
    public ID add(GameObject gameObject) {
        return add(gameObject, stage.getMainLayer());
    }

    /**
     * Adds the specified game object to the given layer.
     * @param gameObject the game object to be added
     * @param parent the layer
     * @return the newly calculated id which is associated with the game object
     * @see #add(GameObject, Group)
     */
    public ID add(GameObject gameObject, Group parent) {
        return set(createID(gameObject), gameObject, parent);
    }

    /**
     * Creates a new id. For this the unique identiy of the game object, if it has one, is kept. Furthermore,
     * free slots are recycled if that is possible. In addition, this method might resize the game objects
     * array to be able to fit a larger amount of game objects.
     * @return the newly calculated id
     */
    private ID createID(GameObject gameObject) {
        long globalID;
        if(gameObject.getID().getGlobalID() != -1) {
            globalID = gameObject.getID().getGlobalID();
        } else {
            globalID = uniqueIDCounter;
            uniqueIDCounter++;
        }
        if(freeSlots.size() != 0) {
            return new ID(freeSlots.poll(), globalID);
        } else if(firstFreeSlot < gameObjects.length) {
            ID id = new ID(firstFreeSlot, globalID);
            firstFreeSlot++;
            return id;
        }
        gameObjects = Arrays.copyOf(gameObjects, gameObjects.length + 100);
        return createID(gameObject);
    }

    private ID set(ID id, GameObject gameObject, Group parent) {
        gameObjects[id.getSceneID()] = gameObject;
        parent.addActor(gameObject);
        return id;
    }

    public boolean remove(ID id) {
        try {
            Group previousParent = gameObjects[id.getSceneID()].getParent();
            int oldIndex = gameObjects[id.getSceneID()].indexInParent;
            boolean result = gameObjects[id.getSceneID()].remove();
            if(result) {
                for(int i = oldIndex; i < previousParent.getChildren().size; i++) {
                    ((GameObject) previousParent.getChildren().get(i)).indexInParent = i;
                }
            }
            return result;
        } catch(ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public GameObject getGameObject(ID id) {
        try {
            GameObject gameObject = gameObjects[id.getSceneID()];
            if(gameObject != null) {
                if(gameObject.getID().equals(id)) {
                    return gameObject;
                }
            }
            if(id.getSceneID() != -1) {
                id.setSceneID(findSceneIDOf(id.getGlobalID()));
                return getGameObject(id);
            }
            return null;
        } catch(ArrayIndexOutOfBoundsException e) {
            return null;
        }

    }

    private int findSceneIDOf(long globalID) {
        for(GameObject gameObject: gameObjects) {
            if(gameObject != null && gameObject.getID().getGlobalID() == globalID) {
                return gameObject.getID().getSceneID();
            }
        }
        return -1;
    }

    public void requestRemove(ID id) {
        toBeRemoved.add(id);
    }

    /**
     * Removes all GameObjects which are not marked with {@link GameObject#setPersistent(boolean)} set to true.
     */
    public void removeAll() {
        for(GameObject gameObject: gameObjects) {
            if(gameObject != null) {
                remove(gameObject.getID());
            }
        }
    }

    public void update() {
        for(ID id: toBeRemoved) {
            remove(id);
        }
        toBeRemoved.clear();
        for(GameObject gameObject: gameObjects) {
            if(gameObject != null) {
                gameObject.finishedTagOperations();
                gameObject.setAlreadyActed(false);
            }
        }
    }

    public GameObject stash(ID id) {
        if(id.getSceneID() < -1 && id.getSceneID() >= gameObjects.length) {
            System.err.println("Invalid ID");
        }
        GameObject gameObject = gameObjects[id.getSceneID()];
        gameObjects[id.getSceneID()] = null;
        freeSlots.add(id.getSceneID());
        return gameObject;
    }

    public List<GameObject> stashPersistent() {
        ArrayList<GameObject> list = new ArrayList<>();
        for(GameObject gameObject: gameObjects) {
            if(gameObject != null && gameObject.isPersistent()) {
                list.add(stash(gameObject.getID()));
            }
        }
        return list;
    }

    public void unstash(List<GameObject> unstashGameObjects) {
        for(GameObject gameObject: unstashGameObjects) {
            ID id = createID(gameObject);
            gameObjects[id.getSceneID()] = gameObject;
            gameObject.setID(id);
            for(Tags tag : gameObject.getTags()) {
                addGameObjectToTag(tag, id);
            }
            if(gameObject.getType().equals("PLAYER")) {
                playerID = gameObject.getID();
            }
        }
    }

    public GameObject[] getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(GameObject[] gameObjects) {
        this.gameObjects = gameObjects;
    }

    public List<ID> getGameObjectsByTag(Tags tag) {
        if(tags.containsKey(tag)) {
            return tags.get(tag);
        } else {
            return new ArrayList<>();
        }
    }

    public void addGameObjectToTag(Tags tag, ID id) {
        if(tags.containsKey(tag)) {
            tags.get(tag).add(id);
        } else {
            tags.put(tag, new ArrayList<ID>());
            addGameObjectToTag(tag, id);
        }
    }

    public boolean removeGameObjectFromTag(Tags tag, ID id) {
        if(tags.containsKey(tag)) {
            tags.get(tag).remove(id);
            return true;
        }
        return false;
    }

    public void setStage(GameStage stage) {
        this.stage = stage;
    }

    public long getUniqueIDCounter() {
        return uniqueIDCounter;
    }

    public void setUniqueIDCounter(long uniqueIDCounter) {
        this.uniqueIDCounter = uniqueIDCounter;
    }
}
