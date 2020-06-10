package jnh.game.assets;

import jnh.game.gameObjects.construction.Blueprint;
import jnh.game.gameObjects.construction.BlueprintLoader;

public class Blueprints {

    private BlueprintLoader loader;

    public Blueprint PLAYER, ZOMBIE;
    public Blueprint FLOOR;

    public Blueprints() {
        loader = new BlueprintLoader();

        PLAYER = loader.loadBlueprint("player");
        ZOMBIE = loader.loadBlueprint("zombie");

        FLOOR = loader.loadBlueprint("floor");
    }
}
