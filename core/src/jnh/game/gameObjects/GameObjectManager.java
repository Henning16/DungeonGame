package jnh.game.gameObjects;

import com.badlogic.gdx.scenes.scene2d.Group;
import jnh.game.stages.GameStage;

import java.util.*;

public class GameObjectManager {

    private transient GameStage stage;

    private GameObject[] gameObjects = new GameObject[100];



    private ArrayList<ID> toBeRemoved = new ArrayList<>();

    public ArrayList<ID> items = new ArrayList<>();
    public ArrayList<ID> destroyables = new ArrayList<>();

    public ID playerID;

    private Queue<Integer> freeSlots = new LinkedList<>();
    private int firstFreeSlot = 0;
    private transient long uniqueIDCounter = 0;

    public GameObjectManager() {

    }

    public GameObjectManager(GameStage stage) {
        this.stage = stage;
    }

    public ID add(GameObject gameObject) {
        return add(gameObject, stage.getMainLayer());
    }

    public ID add(GameObject gameObject, Group parent) {
        return set(createID(gameObject), gameObject, parent);
    }

    private ID createID(GameObject gameObject) {
        long globalID;
        if(gameObject.getID().getGlobalID() != -1) {
            globalID = gameObject.getID().getGlobalID();
        } else {
            globalID = uniqueIDCounter;
            uniqueIDCounter++;
        }
        if(freeSlots.size() != 0) {
            ID id = new ID(freeSlots.poll(), globalID);
            return id;
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
            items.remove(id);
            destroyables.remove(id);
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
