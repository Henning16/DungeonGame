package jnh.game.world.rooms;

import jnh.game.stages.GameStage;
import jnh.game.world.Dungeon;

public abstract class Room {

    public static final int ROOM_WIDTH = 18;
    public static final int ROOM_HEIGHT = 18;

    private GameStage stage;
    private Dungeon dungeon;

    private RoomType type;
    private Room top, right, bottom, left;

    public Room(GameStage stage, Dungeon dungeon) {
        this.stage = stage;
        this.dungeon = dungeon;
        generate();
    }

    protected abstract void generate();

    protected GameStage getStage() {
        return stage;
    }

    protected Dungeon getDungeon() {
        return dungeon;
    }
}
