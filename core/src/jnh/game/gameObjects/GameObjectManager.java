package jnh.game.gameObjects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Group;
import jnh.game.stages.GameStage;

import java.util.ArrayList;

public class GameObjectManager {

    private transient GameStage stage;

    private boolean doDebug = false;
    public static final transient int MAX_GAME_OBJECT_NUMBER = 10000;

    private GameObject[] gameObjects = new GameObject[MAX_GAME_OBJECT_NUMBER];

    private transient ArrayList<Integer> toBeRemoved = new ArrayList<>();

    public transient ArrayList<Integer> items = new ArrayList<>();
    public transient ArrayList<Integer> destroyables = new ArrayList<>();

    public int playerID;

    private int firstFreeID = 0;

    public GameObjectManager(GameStage stage) {
        this.stage = stage;
    }

    public int add(GameObject gameObject) {
        return add(gameObject, stage.getMainLayer());
    }

    public int add(GameObject gameObject, Group parent) {
        if(firstFreeID < gameObjects.length) {
            gameObjects[firstFreeID] = gameObject;
            parent.addActor(gameObject);
            int id = firstFreeID;
            firstFreeID++;
            return id;
        } else {
            System.err.println("GameObjectManager: GameObject could not be added. Max GameObjectNumber is too high.");
            return -1;
        }
    }

    public boolean remove(int id) {
        try {
            Group previousParent = gameObjects[id].getParent();
            int oldIndex = gameObjects[id].indexInParent;
            boolean result = gameObjects[id].remove();
            if(result) {
                for(int i = oldIndex; i < previousParent.getChildren().size; i++) {
                    ((GameObject) previousParent.getChildren().get(i)).indexInParent = i;
                }
            }
            return result;
        } catch(ArrayIndexOutOfBoundsException e) {
            if(doDebug) {
                System.err.println("GameObjectManager: Invalid ID");
            }
            return false;
        }
    }

    public GameObject getGameObject(int id) {
        try {
            return gameObjects[id];
        } catch(ArrayIndexOutOfBoundsException e) {
            if(doDebug) {
                System.err.println("GameObjectManager: Invalid ID");
            }
            return null;
        }
    }

    public void requestRemove(int id) {
        toBeRemoved.add(id);
    }

    public void update() {
        for(int id: toBeRemoved) {
            remove(id);
            items.remove((Object) id);
        }
        toBeRemoved.clear();
        for(GameObject gameObject: gameObjects) {
            if(gameObject != null) {
                gameObject.setAlreadyActed(false);
            }
        }
    }

    public GameObject[] getGameObjects() {
        return gameObjects;
    }

}
