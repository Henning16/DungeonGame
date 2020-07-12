package jnh.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.*;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.GameObjectJson;
import jnh.game.gameObjects.GameObjectManager;
import jnh.game.stages.GameStage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class World {

    private transient GameStage stage;

    private String name;
    private String fileName;
    private Vector2 respawnPosition = new Vector2(12, 12);
    private int respawnSceneID = 0;
    private transient int sceneID = 0;
    private long uniqueIDCounter = 0;

    private World() {

    }

    public void loadScene(int id) throws FileNotFoundException {
        FileHandle sceneFile = Gdx.files.external(World.getSaveFolder().path()+"/"+fileName+"/"+"scene"+id+".json");
        if(!sceneFile.exists()) {
            newScene(id);
            return;
        }
        String sceneString = sceneFile.readString();
        stage.getGameObjectManager().removeAll();
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        json.setSerializer(GameObject.class, new Json.Serializer<GameObject>() {
            @Override
            public void write(Json json, GameObject object, Class knownType) {
            }

            @Override
            public GameObject read(Json json, JsonValue jsonData, Class type) {
                if(jsonData.type() == JsonValue.ValueType.nullValue) {
                    return null;
                }
                return new GameObject(stage, json.fromJson(GameObjectJson.class, jsonData.toString()));
            }
        });
        uniqueIDCounter = stage.getGameObjectManager().getUniqueIDCounter();
        stage.setGameObjectManager(json.fromJson(GameObjectManager.class, sceneString));
        stage.getGameObjectManager().setStage(stage);
        stage.getGameObjectManager().setUniqueIDCounter(uniqueIDCounter);
        sceneID = id;
    }

    public void addScene(int id) throws FileNotFoundException {
        FileHandle sceneFile = Gdx.files.external(World.getSaveFolder().path()+"/"+fileName+"/"+"scene"+id+".json");
        sceneFile.writeString("{}", false);
    }

    public void newScene(int id) throws FileNotFoundException {
        stage.getGameObjectManager().removeAll();
        uniqueIDCounter = stage.getGameObjectManager().getUniqueIDCounter();
        stage.setGameObjectManager(new GameObjectManager());
        stage.getGameObjectManager().setStage(stage);
        stage.getGameObjectManager().setUniqueIDCounter(uniqueIDCounter);
        sceneID = id;
        saveScene(id);
    }

    public void switchScene(int id) throws FileNotFoundException {
        List<GameObject> persistentGameObjects = stage.getGameObjectManager().stashPersistent();
        saveScene(sceneID);
        loadScene(id);
        stage.getGameObjectManager().unstash(persistentGameObjects);
    }

    public void saveScene(int id) throws FileNotFoundException {
        Json json = new Json();
        String fileString = "";
        json.setOutputType(JsonWriter.OutputType.json);
        json.setSerializer(GameObject.class, new Json.Serializer<GameObject>() {
            @Override
            public void write(Json json, GameObject object, Class knownType) {
                json.writeValue(new GameObjectJson(object));
            }

            @Override
            public GameObject read(Json json, JsonValue jsonData, Class type) {
                return null;
            }
        });
        fileString = json.prettyPrint(json.toJson(stage.getGameObjectManager()));
        FileHandle sceneFile = Gdx.files.external(World.getSaveFolder().path()+"/"+fileName+"/"+"scene"+id+".json");
        sceneFile.writeString(fileString, false);
    }

    public void save() {
        try {
            saveScene(sceneID);
            FileHandle meta = Gdx.files.external(getSaveFolder().path()+"/"+fileName+"/"+"meta.json");
            Json json = new Json();
            json.setOutputType(JsonWriter.OutputType.json);
            meta.writeString(json.prettyPrint(json.toJson(this)), false);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static World newWorld(GameStage stage, String name) {
        World world = new World();
        world.stage = stage;
        world.name = name;
        world.fileName = name;
        world.save();
        return world;
    }

    public static World loadWorld(GameStage stage, String name) throws FileNotFoundException {
        FileHandle worldFolder = getSaveFolder().child(name);
        Json json = new Json();
        World world;
        try {
            world = json.fromJson(World.class, worldFolder.child("meta.json").readString());
        } catch(Exception e) {
            throw new FileNotFoundException("World corrupted.");
        }
        world.stage = stage;
        return world;
    }

    public static List<String> getWorlds() throws FileNotFoundException {
        ArrayList<String> worlds = new ArrayList<>();
        for(FileHandle fileHandle : getSaveFolder().list()) {
            if(fileHandle.child("meta.json").exists()) {
                worlds.add(fileHandle.name());
            }
        }
        return worlds;
    }

    private static FileHandle getSaveFolder() throws FileNotFoundException {
        try {
            FileHandle userFolder = Gdx.files.external("");
            if(!userFolder.exists()) {
                throw new FileNotFoundException("User folder was not found and could not be created.");
            }
            return userFolder.child(".dungeongame/saves");
        } catch(Exception e) {
            throw new FileNotFoundException("Save folder was not found and could not be created.");
        }
    }

    public Vector2 getRespawnPosition() {
        return respawnPosition;
    }

    public void setRespawnPosition(Vector2 respawnPosition) {
        this.respawnPosition = respawnPosition;
    }

    public int getRespawnSceneID() {
        return respawnSceneID;
    }

    public void setRespawnSceneID(int respawnSceneID) {
        this.respawnSceneID = respawnSceneID;
    }
}
