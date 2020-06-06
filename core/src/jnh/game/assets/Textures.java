package jnh.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jnh.game.gameObjects.entities.EntityState;
import jnh.game.gameObjects.roomBoundaries.FloorTile;
import jnh.game.utils.Direction;

public class Textures {

    private final Texture sprites, PLAYER_SHEET, FLOOR_SHEET, WALL_SHEET;
    public final TextureRegion ERROR;
    public final Animation<TextureRegion>[][] PLAYER;
    public final Animation<TextureRegion>[] FLOOR_TILE;
    public final Animation[][] WALL;

    public Textures() {
        sprites = new Texture(Gdx.files.internal("textures/textures.png"));

        ERROR = get(sprites, 496, 496, 16, 16);

        PLAYER_SHEET = new Texture(Gdx.files.internal("textures/player.png"));
        FLOOR_SHEET = new Texture(Gdx.files.internal("textures/floor.png"));
        WALL_SHEET = new Texture(Gdx.files.internal("textures/wall.png"));

        PLAYER = new Animation[4][2];
        FLOOR_TILE = new Animation[5];
        WALL = new Animation[5][4];

        PLAYER[Direction.UP][EntityState.IDLE] = new Animation<>(1f,
                get(PLAYER_SHEET, 11, 0, 11, 16));
                PLAYER[Direction.UP][EntityState.IDLE].setPlayMode(Animation.PlayMode.LOOP);
        PLAYER[Direction.RIGHT][EntityState.IDLE] = new Animation<>(1f,
                get(PLAYER_SHEET, 22, 0, 11, 16));
                PLAYER[Direction.RIGHT][EntityState.IDLE].setPlayMode(Animation.PlayMode.LOOP);
        PLAYER[Direction.DOWN][EntityState.IDLE] = new Animation<>(1f,
                get(PLAYER_SHEET, 0, 0, 11, 16));
                PLAYER[Direction.DOWN][EntityState.IDLE].setPlayMode(Animation.PlayMode.LOOP);
        PLAYER[Direction.LEFT][EntityState.IDLE] = new Animation<>(1f,
                get(PLAYER_SHEET, 33, 0, 11, 16));
                PLAYER[Direction.LEFT][EntityState.IDLE].setPlayMode(Animation.PlayMode.LOOP);
        PLAYER[Direction.UP][EntityState.WALK] = new Animation<>(0.2f,
                get(PLAYER_SHEET, 11, 0, 11, 16),
                get(PLAYER_SHEET, 11, 16, 11, 16),
                get(PLAYER_SHEET, 11, 0, 11, 16),
                get(PLAYER_SHEET, 11, 32, 11, 16));
                PLAYER[Direction.UP][EntityState.WALK].setPlayMode(Animation.PlayMode.LOOP);
        PLAYER[Direction.RIGHT][EntityState.WALK] = new Animation<>(0.2f,
                get(PLAYER_SHEET, 22, 0, 11, 16),
                get(PLAYER_SHEET, 22, 16, 11, 16),
                get(PLAYER_SHEET, 22, 0, 11, 16),
                get(PLAYER_SHEET, 22, 32, 11, 16));
                PLAYER[Direction.RIGHT][EntityState.WALK].setPlayMode(Animation.PlayMode.LOOP);
        PLAYER[Direction.DOWN][EntityState.WALK] = new Animation<>(0.2f,
                get(PLAYER_SHEET, 0, 0, 11, 16),
                get(PLAYER_SHEET, 0, 16, 11, 16),
                get(PLAYER_SHEET, 0, 0, 11, 16),
                get(PLAYER_SHEET, 0, 32, 11, 16));
                PLAYER[Direction.DOWN][EntityState.WALK].setPlayMode(Animation.PlayMode.LOOP);
        PLAYER[Direction.LEFT][EntityState.WALK] = new Animation<>(0.2f,
                get(PLAYER_SHEET, 33, 0, 11, 16),
                get(PLAYER_SHEET, 33, 16, 11, 16),
                get(PLAYER_SHEET, 33, 0, 11, 16),
                get(PLAYER_SHEET, 33, 32, 11, 16));
                PLAYER[Direction.LEFT][EntityState.WALK].setPlayMode(Animation.PlayMode.LOOP);

        FLOOR_TILE[FloorTile.Type.NORMAL] = new Animation<>(1f,
                get(FLOOR_SHEET, 0 , 0, 16, 16));
                FLOOR_TILE[FloorTile.Type.NORMAL].setPlayMode(Animation.PlayMode.NORMAL);
        FLOOR_TILE[FloorTile.Type.LIGHT_CRACKS] = new Animation<>(1f,
                get(FLOOR_SHEET, 16 , 0, 16, 16));
                FLOOR_TILE[FloorTile.Type.LIGHT_CRACKS].setPlayMode(Animation.PlayMode.NORMAL);
        FLOOR_TILE[FloorTile.Type.HEAVY_CRACKS] = new Animation<>(1f,
                get(FLOOR_SHEET, 32 , 0, 16, 16));
                FLOOR_TILE[FloorTile.Type.HEAVY_CRACKS].setPlayMode(Animation.PlayMode.NORMAL);
        FLOOR_TILE[FloorTile.Type.LIGHT_MOSS] = new Animation<>(1f,
                get(FLOOR_SHEET, 48 , 0, 16, 16));
                FLOOR_TILE[FloorTile.Type.LIGHT_MOSS].setPlayMode(Animation.PlayMode.NORMAL);
        FLOOR_TILE[FloorTile.Type.HEAVY_MOSS] = new Animation<>(1f,
                get(FLOOR_SHEET, 64 , 0, 16, 16));
                FLOOR_TILE[FloorTile.Type.HEAVY_MOSS].setPlayMode(Animation.PlayMode.NORMAL);

        WALL[0][0] = new Animation<>(1f,
                get(WALL_SHEET, 0, 0, 16, 16));
        WALL[0][1] = new Animation<>(1f,
                get(WALL_SHEET, 0, 16, 16, 16));
        WALL[0][2] = new Animation<>(1f,
                get(WALL_SHEET, 0, 32, 16, 16));
        WALL[0][3] = new Animation<>(1f,
                get(WALL_SHEET, 0, 48, 16, 16));
        WALL[1][0] = new Animation<>(1f,
                get(WALL_SHEET, 16, 0, 16, 16));
        WALL[1][1] = new Animation<>(1f,
                get(WALL_SHEET, 16, 16, 16, 16));
        WALL[1][2] = new Animation<>(1f,
                get(WALL_SHEET, 16, 32, 16, 16));
        WALL[1][3] = new Animation<>(1f,
                get(WALL_SHEET, 16, 48, 16, 16));
        WALL[2][0] = new Animation<>(1f,
                get(WALL_SHEET, 32, 0, 16, 16));
        WALL[2][1] = new Animation<>(1f,
                get(WALL_SHEET, 32, 16, 16, 16));
        WALL[2][2] = new Animation<>(1f,
                get(WALL_SHEET, 32, 32, 16, 16));
        WALL[2][3] = new Animation<>(1f,
                get(WALL_SHEET, 32, 48, 16, 16));
        WALL[3][0] = new Animation<>(1f,
                get(WALL_SHEET, 48, 0, 16, 16));
        WALL[3][1] = new Animation<>(1f,
                get(WALL_SHEET, 48, 16, 16, 16));
        WALL[3][2] = new Animation<>(1f,
                get(WALL_SHEET, 48, 32, 16, 16));
        WALL[3][3] = new Animation<>(1f,
                get(WALL_SHEET, 48, 48, 16, 16));
        WALL[4][0] = new Animation<>(1f,
                get(WALL_SHEET, 64, 0, 16, 16));
        WALL[4][1] = new Animation<>(1f,
                get(WALL_SHEET, 64, 16, 16, 16));
        WALL[4][2] = new Animation<>(1f,
                get(WALL_SHEET, 64, 32, 16, 16));
        WALL[4][3] = new Animation<>(1f,
                get(WALL_SHEET, 64, 48, 16, 16));
    }

    private TextureRegion get(Texture texture, int x, int y, int width, int height) {
        return new TextureRegion(texture, x, y, width, height);
    }

}
