package jnh.game.world;

import jnh.game.stages.GameStage;

public class Dungeon {

    public static final int DUNGEON_SIZE = 5;
    private GameStage stage;
    private Room[][] rooms = new Room[DUNGEON_SIZE][DUNGEON_SIZE];

    public Dungeon(GameStage stage) {
        this.stage = stage;
        for(int y = 0; y < DUNGEON_SIZE; y++) {
            for(int x = 0; x < DUNGEON_SIZE; x++) {
                rooms[y][x] = new RandomRoom(stage, y * DUNGEON_SIZE + x);
            }
        }
    }

    public boolean setRoom(int y, int x) {
        try {
            stage.getWorld().switchScene(rooms[y][x].getID());
            rooms[y][x].load();
            return true;
        } catch(Exception e) {
            return false;
        }
    }

}
