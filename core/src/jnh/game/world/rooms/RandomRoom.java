package jnh.game.world.rooms;

import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.roomBoundaries.FloorTile;
import jnh.game.gameObjects.roomBoundaries.Wall;
import jnh.game.gfx.Assets;
import jnh.game.stages.GameStage;
import jnh.game.world.Dungeon;

public class RandomRoom extends Room {

    public RandomRoom(GameStage stage, Dungeon dungeon) {
        super(stage, dungeon);
    }

    @Override
    protected void generate() {
        for(int y = 1; y < ROOM_WIDTH - 1; y++) {
            for(int x = 1; x < ROOM_HEIGHT - 1; x++) {
                getStage().addActor(new FloorTile(getStage(), new Vector2(x, y)));
            }
        }
        for(int x = 1; x < ROOM_WIDTH - 1; x++) {
            getStage().addActor(new Wall(getStage(), Assets.STONE_T, new Vector2(x, 0)));
        }
        for(int x = 1; x < ROOM_WIDTH - 1; x++) {
            getStage().addActor(new Wall(getStage(), Assets.STONE_B, new Vector2(x, ROOM_HEIGHT - 1)));
        }
        for(int y = 1; y < ROOM_HEIGHT - 1; y++) {
            getStage().addActor(new Wall(getStage(), Assets.STONE_R, new Vector2(0, y)));
        }
        for(int y = 1; y < ROOM_HEIGHT - 1; y++) {
            getStage().addActor(new Wall(getStage(), Assets.STONE_L, new Vector2(ROOM_WIDTH - 1, y)));
        }
    }
}
