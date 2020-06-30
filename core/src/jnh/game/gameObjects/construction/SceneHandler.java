package jnh.game.gameObjects.construction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.GameObjectManager;
import jnh.game.gameObjects.components.HealthComponent;
import jnh.game.stages.GameStage;
import jnh.game.ui.notifications.Notification;
import jnh.game.ui.notifications.NotificationHandler;

import java.util.Base64;

public class SceneHandler {

    private GameStage stage;
    private Json json;

    public SceneHandler(GameStage stage) {
        this.stage = stage;
    }

    public void saveScene(String name) {
        Json json = new Json();
        String fileString = "";
        json.setOutputType(JsonWriter.OutputType.json);
        json.setSerializer(GameObject.class, new Json.Serializer<GameObject>() {
            @Override
            public void write(Json json, GameObject object, Class knownType) {
                json.writeObjectStart();
                json.writeValue("type", object.getType());
                json.writeValue("position", new Vector2(object.getX(), object.getY()));
                json.writeValue("dimension", new Vector2(object.getWidth(), object.getHeight()));
                json.writeValue("components", object.getComponents());
                json.writeObjectEnd();
            }

            @Override
            public GameObject read(Json json, JsonValue jsonData, Class type) {
                return null;
            }
        });
        json.setSerializer(GameObjectManager.class, new Json.Serializer<GameObjectManager>() {
            @Override
            public void write(Json json, GameObjectManager object, Class knownType) {
                json.writeObjectStart();
                for(GameObject gameObject: object.getGameObjects()) {
                    if(gameObject != null) {
                        json.writeValue(String.valueOf(gameObject.getID()), gameObject);
                    }
                }
                json.writeObjectEnd();
            }

            @Override
            public GameObjectManager read(Json json, JsonValue jsonData, Class type) {
                return null;
            }
        });
        fileString = json.prettyPrint(json.toJson(stage.getGameObjectManager()));
        FileHandle file = Gdx.files.local(name+".json");
        file.writeString(fileString, false);
    }

    public void loadScene(String name) {
        String fileString = Gdx.files.local(name+".save").readString();

    }

    private String decode(String input) {
        return new String(Base64.getDecoder().decode(input.getBytes()));
    }

    private String encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }
}
