package jnh.game.world;

import jnh.game.assets.Assets;
import jnh.game.components.BodyComponent;
import jnh.game.gameObjects.GameObject;
import jnh.game.stages.GameStage;

public class RandomRoom extends Room {

    public RandomRoom(GameStage stage, int id, int x, int y) {
        super(stage, id, x, y);
    }

    @Override
    public void generate() {
        for(int y = 1; y < ROOM_WIDTH - 1; y++) {
            for(int x = 1; x < ROOM_HEIGHT - 1; x++) {
                GameObject tile = new GameObject(getStage(), Assets.blueprints.FLOOR);
                tile.setPosition(x, y);
            }
        }

        //LOWER WALLS
        for(int x = 1; x < ROOM_WIDTH - 1; x++) {
            GameObject wall;
            if(x != 7) {
                wall = new GameObject(getStage(), Assets.blueprints.WALL_HORIZONTAL);
            } else {
                wall = new GameObject(getStage(), Assets.blueprints.WALL_DOOR_DOWN);
            }
            wall.getComponent(BodyComponent.class).getBody().setTransform(x, 0, 0);
        }

        //UPPER WALLS
        for(int x = 1; x < ROOM_WIDTH - 1; x++) {
            GameObject wall;
            if(x != 7) {
                wall = new GameObject(getStage(), Assets.blueprints.WALL_HORIZONTAL);
            } else {
                wall = new GameObject(getStage(), Assets.blueprints.WALL_DOOR_UP);
            }
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
        for(int y = 1; y < ROOM_HEIGHT - 1; y++) {
            GameObject wall = new GameObject(getStage(), Assets.blueprints.WALL_VERTICAL);
            wall.getComponent(BodyComponent.class).getBody().setTransform(ROOM_WIDTH - 1, y, 0);
        }
        GameObject upperRightWall = new GameObject(getStage(), Assets.blueprints.WALL_UPPER_CORNER);
        upperRightWall.getComponent(BodyComponent.class).getBody().setTransform(ROOM_WIDTH - 1, ROOM_HEIGHT - 1, 0);

        GameObject crate = new GameObject(getStage(), Assets.blueprints.CRATE);
        crate.getComponent(BodyComponent.class).getBody().setTransform(8, 4, 0);
        new GameObject(getStage(), Assets.blueprints.LOGPILE).getComponent(BodyComponent.class).getBody().setTransform(6, 4, 0);

        for(int i = 0; i < 1; i++) {
            new GameObject(getStage(), Assets.blueprints.ZOMBIE).getComponent(BodyComponent.class).getBody().setTransform((float) (Math.random() * 6) + 3, (float) (Math.random() * 6) + 3, 0);
            new GameObject(getStage(), Assets.blueprints.TORCH).setPosition((float) Math.random() * 10 + 2,  (float) Math.random() * 10 + 2);
        }
        new GameObject(getStage(), Assets.blueprints.WAND).setPosition(4, 4);
        new GameObject(getStage(), Assets.blueprints.HEAL_POTION).setPosition(8, 8);
        new GameObject(getStage(), Assets.blueprints.HEAL_POTION).setPosition(10, 10);

    }
}