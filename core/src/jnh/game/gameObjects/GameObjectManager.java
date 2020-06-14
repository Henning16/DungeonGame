package jnh.game.gameObjects;

import com.badlogic.gdx.scenes.scene2d.Group;
import jnh.game.stages.GameStage;

import java.util.ArrayList;

public class GameObjectManager {

    private GameStage stage;

    public static final int MAX_GAME_OBJECT_NUMBER = 10000;

    private GameObject[] gameObjects = new GameObject[MAX_GAME_OBJECT_NUMBER];

    private ArrayList<Integer> toBeRemoved = new ArrayList<>();
    public ArrayList<Integer> items = new ArrayList<>();

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
            return gameObjects[id].remove();
        } catch(ArrayIndexOutOfBoundsException e) {
            System.err.println("GameObjectManager: Invalid ID");
            return false;
        }
    }

    public GameObject getGameObject(int id) {
        try {
            return gameObjects[id];
        } catch(ArrayIndexOutOfBoundsException e) {
            System.err.println("GameObjectManager: Invalid ID");
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
    }

    public GameObject[] getGameObjects() {
        return gameObjects;
    }
}
