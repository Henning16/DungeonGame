package jnh.game.assets;

import jnh.game.gameObjects.construction.Blueprint;
import jnh.game.gameObjects.construction.BlueprintLoader;

public class Blueprints {

    private BlueprintLoader loader;

    public Blueprint PLAYER, ZOMBIE;
    public Blueprint FLOOR;
    public Blueprint AXE;
    public Blueprint LOGPILE;

    public Blueprints() {
        loader = new BlueprintLoader();

        PLAYER = loader.loadBlueprintFromJson("player");
        ZOMBIE = loader.loadBlueprintFromJson("zombie");

        FLOOR = loader.loadBlueprintFromJson("floor");

        AXE = loader.loadBlueprintFromJson("axe");

        LOGPILE = loader.loadBlueprintFromJson("logpile");

    }
}
