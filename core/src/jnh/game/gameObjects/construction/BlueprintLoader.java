package jnh.game.gameObjects.construction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

public class BlueprintLoader {

    public Blueprint loadBlueprintFromJson(String name) {
        String blueprintRepresentation = Gdx.files.internal("blueprints/"+name+".json").readString();
        Json json = new Json();
        json.setEnumNames(false);
        Blueprint blueprint = json.fromJson(Blueprint.class, blueprintRepresentation);
        return blueprint;
    }
}