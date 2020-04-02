package jnh.game.world;

import jnh.game.stages.GameStage;
import jnh.game.world.rooms.RandomRoom;
import jnh.game.world.rooms.Room;

import java.util.Random;

public class Dungeon {

    private GameStage stage;

    private Random generator;
    private long seed;

    public static final int DUNGEON_SIZE = 7;
    private int difficulty;

    private Room currentRoom;
    private Room[][] rooms;

    public Dungeon(GameStage stage, long seed, int difficulty) {
        this.stage = stage;
        this.seed = seed;
        this.difficulty = difficulty;
        generate();
    }

    private void generate() {
        rooms = new Room[DUNGEON_SIZE][DUNGEON_SIZE];
        generator = new Random(seed);
        rooms[DUNGEON_SIZE / 2][DUNGEON_SIZE / 2] = new RandomRoom(stage, this);
        currentRoom = rooms[DUNGEON_SIZE / 2][DUNGEON_SIZE / 2];
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public Room getRoomAt(int x, int y) {
        return rooms[x][y];
    }

    public Random getGenerator() {
        return generator;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
