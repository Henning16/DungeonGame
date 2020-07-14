package jnh.game.world;

import jnh.game.stages.GameStage;

public class Room {

    public static final int ROOM_WIDTH = 15;
    public static final int ROOM_HEIGHT = 15;
    private int id;

    private transient GameStage stage;

    public Room(GameStage stage, int id) {
        this.stage = stage;
        this.id = id;
    }

    protected void generate() {

    }

    protected GameStage getStage() {
        return stage;
    }

    public int getID() {
        return id;
    }
}
