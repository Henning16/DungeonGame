package jnh.game.world;

import jnh.game.stages.GameStage;

public class Room {

    public static final int ROOM_WIDTH = 15;
    public static final int ROOM_HEIGHT = 15;
    private int id, x, y;

    private transient GameStage stage;

    public Room(GameStage stage, int id, int x, int y) {
        this.stage = stage;
        this.id = id;
        this.x = x;
        this.y = y;
    }

    protected void generate() {

    }

    protected GameStage getStage() {
        return stage;
    }

    public int getID() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
