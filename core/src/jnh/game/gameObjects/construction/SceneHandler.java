package jnh.game.gameObjects.construction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import jnh.game.stages.GameStage;

import java.util.Base64;

public class SceneHandler {

    private GameStage stage;
    private Json json;

    public SceneHandler(GameStage stage) {
        this.stage = stage;
    }

    public void saveScene(String name) {

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
