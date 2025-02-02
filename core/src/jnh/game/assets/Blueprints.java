package jnh.game.assets;

import jnh.game.gameObjects.construction.Blueprint;
import jnh.game.gameObjects.construction.BlueprintLoader;

public class Blueprints {

    private final BlueprintLoader loader;

    public Blueprint PLAYER, ZOMBIE;
    public Blueprint FLOOR;
    public Blueprint WALL_HORIZONTAL, WALL_VERTICAL, WALL_UPPER_CORNER, WALL_LOWER_CORNER, WALL_DOOR_UP, WALL_DOOR_DOWN;
    public Blueprint AXE, SWORD, TORCH, WAND;
    public Blueprint HEAL_POTION;
    public Blueprint LOGPILE, CRATE;

    public Blueprints() {
        loader = new BlueprintLoader();

        PLAYER = loader.loadBlueprintFromJson("player");
        ZOMBIE = loader.loadBlueprintFromJson("zombie");

        FLOOR = loader.loadBlueprintFromJson("floor");

        WALL_HORIZONTAL = loader.loadBlueprintFromJson("walls/wall_horizontal");
        WALL_VERTICAL = loader.loadBlueprintFromJson("walls/wall_vertical");
        WALL_UPPER_CORNER = loader.loadBlueprintFromJson("walls/wall_upper_corner");
        WALL_LOWER_CORNER = loader.loadBlueprintFromJson("walls/wall_lower_corner");
        WALL_DOOR_UP = loader.loadBlueprintFromJson("walls/wall_door_up");
        WALL_DOOR_DOWN = loader.loadBlueprintFromJson("walls/wall_door_down");

        AXE = loader.loadBlueprintFromJson("weapons/axe");
        SWORD = loader.loadBlueprintFromJson("weapons/sword");
        TORCH = loader.loadBlueprintFromJson("weapons/torch");
        WAND = loader.loadBlueprintFromJson("weapons/wand");

        HEAL_POTION = loader.loadBlueprintFromJson("items/healpotion");

        LOGPILE = loader.loadBlueprintFromJson("logpile");
        CRATE = loader.loadBlueprintFromJson("crate");
        CRATE = loader.loadBlueprintFromJson("crate");

    }
}
