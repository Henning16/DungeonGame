package jnh.game.world;

import jnh.game.stages.GameStage;

public class Dungeon {

    public static final int DUNGEON_SIZE = 5;
    private GameStage stage;
    private Room[][] rooms = new Room[DUNGEON_SIZE][DUNGEON_SIZE];
    private int currentRoomX, currentRoomY;

    public Dungeon(GameStage stage) {
        this.stage = stage;
        for(int y = 0; y < DUNGEON_SIZE; y++) {
            for(int x = 0; x < DUNGEON_SIZE; x++) {
                rooms[y][x] = new RandomRoom(stage, y * DUNGEON_SIZE + x, x, y);
            }
        }
    }

    public boolean setRoom(int y, int x, boolean saveCurrentRoom) {
        try {
            boolean newRoom = !stage.getWorld().sceneExists(rooms[y][x].getID());
            if(saveCurrentRoom) {
                stage.getWorld().switchScene(rooms[y][x].getID());
            } else {
                stage.getWorld().loadScene(rooms[y][x].getID());
            }
            if(newRoom) {
                rooms[y][x].generate();
            }
            currentRoomX = x;
            currentRoomY = y;
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public boolean setRoom(int y, int x) {
        return setRoom(y, x, true);
    }

    public int getCurrentRoomX() {
        return currentRoomX;
    }

    public int getCurrentRoomY() {
        return currentRoomY;
    }
}
