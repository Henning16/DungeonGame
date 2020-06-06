package jnh.game.world.rooms;

import com.badlogic.gdx.math.Vector2;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.roomBoundaries.FloorTile;
import jnh.game.gameObjects.roomBoundaries.Wall;
import jnh.game.gameObjects.roomBoundaries.WallExtension;
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
                if(getDungeon().getGenerator().nextBoolean()) {
                    getStage().getBackgroundLayer().addActor(new FloorTile(getStage(), Assets.textures.FLOOR_TILE[FloorTile.Type.NORMAL], new Vector2(x, y)));
                } else if(getDungeon().getGenerator().nextBoolean()) {
                    if(getDungeon().getGenerator().nextBoolean()) {
                        getStage().getBackgroundLayer().addActor(new FloorTile(getStage(), Assets.textures.FLOOR_TILE[FloorTile.Type.LIGHT_CRACKS], new Vector2(x, y)));
                    } else {
                        getStage().getBackgroundLayer().addActor(new FloorTile(getStage(), Assets.textures.FLOOR_TILE[FloorTile.Type.HEAVY_CRACKS], new Vector2(x, y)));
                    }
                } else {
                    if(getDungeon().getGenerator().nextBoolean()) {
                        getStage().getBackgroundLayer().addActor(new FloorTile(getStage(), Assets.textures.FLOOR_TILE[FloorTile.Type.LIGHT_MOSS], new Vector2(x, y)));
                    } else {
                        getStage().getBackgroundLayer().addActor(new FloorTile(getStage(), Assets.textures.FLOOR_TILE[FloorTile.Type.HEAVY_MOSS], new Vector2(x, y)));
                    }
                }
            }
        }

        for(int x = 1; x < ROOM_WIDTH - 1; x++) {
            getStage().getForegroundLayer().addActor(new WallExtension(getStage(), Assets.textures.WALL[1][0], new Vector2(x, 1)));
        }
        for(int x = 1; x < ROOM_WIDTH - 1; x++) {
            getStage().getForegroundLayer().addActor(new Wall(getStage(), Assets.textures.WALL[1][1], new Vector2(x, 0)));
        }
        for(int x = 1; x < ROOM_WIDTH - 1; x++) {
            getStage().getBackgroundLayer().addActor(new WallExtension(getStage(), Assets.textures.WALL[1][1], new Vector2(x, ROOM_HEIGHT - 1)));
        }
        for(int x = 1; x < ROOM_WIDTH - 1; x++) {
            getStage().getForegroundLayer().addActor(new Wall(getStage(), Assets.textures.WALL[1][0], new Vector2(x, ROOM_HEIGHT)));
        }

        //LEFT
        getStage().getForegroundLayer().addActor(new Wall(getStage(), Assets.textures.WALL[0][3], new Vector2(0, 0)));
        getStage().getForegroundLayer().addActor(new Wall(getStage(), Assets.textures.WALL[0][2], new Vector2(0, 1)));
        for(int y = 2; y < ROOM_HEIGHT; y++) {
            getStage().getForegroundLayer().addActor(new Wall(getStage(), Assets.textures.WALL[0][1], new Vector2(0, y)));
        }
        getStage().getForegroundLayer().addActor(new Wall(getStage(), Assets.textures.WALL[0][0], new Vector2(0, ROOM_HEIGHT)));

        getStage().getForegroundLayer().addActor(new Wall(getStage(), Assets.textures.WALL[2][3], new Vector2(ROOM_WIDTH - 1, 0)));
        getStage().getForegroundLayer().addActor(new Wall(getStage(), Assets.textures.WALL[2][2], new Vector2(ROOM_WIDTH - 1, 1)));
        for(int y = 2; y < ROOM_HEIGHT; y++) {
            getStage().getForegroundLayer().addActor(new Wall(getStage(), Assets.textures.WALL[2][1], new Vector2(ROOM_WIDTH - 1, y)));
        }
        getStage().getForegroundLayer().addActor(new Wall(getStage(), Assets.textures.WALL[2][0], new Vector2(ROOM_WIDTH - 1, ROOM_HEIGHT)));
    }
}
