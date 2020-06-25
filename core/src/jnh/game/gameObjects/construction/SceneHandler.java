package jnh.game.gameObjects.construction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.components.HealthComponent;
import jnh.game.stages.GameStage;

import java.util.Base64;

public class SceneHandler {

    private GameStage stage;

    public SceneHandler(GameStage stage) {
        this.stage = stage;
    }

    public void saveScene(String name) {
        String fileString = new Json().toJson(stage.getGameObjectManager());
        FileHandle file = Gdx.files.local("test.json");
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
