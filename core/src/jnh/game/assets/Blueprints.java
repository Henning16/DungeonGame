package jnh.game.assets;

import jnh.game.gameObjects.construction.Blueprint;
import jnh.game.gameObjects.construction.BlueprintLoader;

public class Blueprints {

    private BlueprintLoader loader;

    public Blueprint TEST, PLAYER;

    public Blueprints() {
        loader = new BlueprintLoader();

        //TEST = loader.loadBlueprint("test");
        PLAYER = loader.loadBlueprint("player");
    }
}
