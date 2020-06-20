package jnh.game.gameObjects.construction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import jnh.game.gameObjects.GameObject;
import jnh.game.stages.GameStage;

import java.util.Base64;

public class SceneHandler {

    private GameStage stage;

    public SceneHandler(GameStage stage) {
        this.stage = stage;
    }

    public void saveScene(String name) {
        //TODO use parameters
        String fileString = "";
        GameObject[] gameObjects = stage.getGameObjectManager().getGameObjects();
        //iterate through all GameObjects
        for(int i = 0; i < gameObjects.length; i++) {

        }
        FileHandle file = Gdx.files.local("test.save");
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
