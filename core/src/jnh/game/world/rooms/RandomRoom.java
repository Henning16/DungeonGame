package jnh.game.world.rooms;

import com.badlogic.gdx.math.Vector2;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.components.BodyComponent;
import jnh.game.gameObjects.components.Indexed2TextureComponent;
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
                GameObject tile = new GameObject(getStage(), Assets.blueprints.FLOOR);
                tile.setPosition(x, y);
            }
        }

        //LOWER WALLS
        for(int x = 1; x < ROOM_WIDTH - 1; x++) {
            GameObject wall = new GameObject(getStage(), Assets.blueprints.WALL_HORIZONTAL);
            wall.getComponent(BodyComponent.class).getBody().setTransform(x, 0, 0);
        }

        //UPPER WALLS
        for(int x = 1; x < ROOM_WIDTH - 1; x++) {
            GameObject wall = new GameObject(getStage(), Assets.blueprints.WALL_HORIZONTAL);
            wall.getComponent(BodyComponent.class).getBody().setTransform(x, ROOM_HEIGHT - 1, 0);
        }

        //LEFT
        GameObject lowerLeftWall = new GameObject(getStage(), Assets.blueprints.WALL_LOWER_CORNER);
        lowerLeftWall.getComponent(BodyComponent.class).getBody().setTransform(0.6875f, 0, 0);
        for(int y = 1; y < ROOM_HEIGHT - 1; y++) {
            GameObject wall = new GameObject(getStage(), Assets.blueprints.WALL_VERTICAL);
            wall.getComponent(BodyComponent.class).getBody().setTransform(0.6875f, y, 0);
        }
        GameObject upperLeftWall = new GameObject(getStage(), Assets.blueprints.WALL_UPPER_CORNER);
        upperLeftWall.getComponent(BodyComponent.class).getBody().setTransform(0.6875f, ROOM_HEIGHT - 1, 0);

        //RIGHT
        GameObject lowerRightWall = new GameObject(getStage(), Assets.blueprints.WALL_LOWER_CORNER);
        lowerRightWall.getComponent(BodyComponent.class).getBody().setTransform(ROOM_WIDTH - 1, 0, 0);
        for(int y = 2; y < ROOM_HEIGHT; y++) {
            GameObject wall = new GameObject(getStage(), Assets.blueprints.WALL_VERTICAL);
            wall.getComponent(BodyComponent.class).getBody().setTransform(ROOM_WIDTH - 1, y, 0);
        }
        GameObject upperRightWall = new GameObject(getStage(), Assets.blueprints.WALL_UPPER_CORNER);
        upperRightWall.getComponent(BodyComponent.class).getBody().setTransform(ROOM_WIDTH - 1, ROOM_HEIGHT - 1, 0);
    }
}
