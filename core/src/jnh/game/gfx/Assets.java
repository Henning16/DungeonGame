package jnh.game.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    private static Texture sprites;
    public static TextureRegion PLAYER_UP, PLAYER_RIGHT, PLAYER_DOWN, PLAYER_LEFT, PLAYER_UP_WALK_1, PLAYER_RIGHT_WALK_1, PLAYER_DOWN_WALK_1, PLAYER_LEFT_WALK_1, PLAYER_UP_WALK_2, PLAYER_RIGHT_WALK_2, PLAYER_DOWN_WALK_2, PLAYER_LEFT_WALK_2;
    public static TextureRegion WALL_1_1, WALL_1_2, WALL_1_3, WALL_1_4, WALL_1_5, WALL_2_1, WALL_2_2, WALL_2_3, WALL_2_4, WALL_2_5, WALL_3_1, WALL_3_2, WALL_3_3, WALL_3_4, WALL_3_5, WALL_4_1, WALL_4_2, WALL_4_3, WALL_4_4, WALL_4_5, WALL_5_1, WALL_5_2, WALL_5_3, WALL_5_4, WALL_5_5;
    public static TextureRegion FLOOR_TILE, FLOOR_TILE_LIGHT_CRACKS, FLOOR_TILE_HEAVY_CRACKS, FLOOR_TILE_LIGHT_MOSS, FLOOR_TILE_HEAVY_MOSS;
    public static TextureRegion STONE_B, STONE_L, STONE_T, STONE_R, STONE_F, STONE_BL, STONE_BT, STONE_BR, STONE_BLT, STONE_BLR, STONE_BTR, STONE_BLTR, STONE_LT, STONE_LR, STONE_TR;

    public Assets () {
        sprites = new Texture (Gdx.files.internal ("textures/textures.png"));
        new TextureRegion (sprites);

        PLAYER_UP = extract (16, 0, 11, 16);
        PLAYER_RIGHT = extract (32, 0, 11, 16);
        PLAYER_DOWN = extract (0, 0, 11, 16);
        PLAYER_LEFT = extract (48, 0, 11, 16);
        PLAYER_UP_WALK_1 = extract (16, 16, 11, 16);
        PLAYER_RIGHT_WALK_1 = extract (32, 16, 11, 16);
        PLAYER_DOWN_WALK_1 = extract (0, 16, 11, 16);
        PLAYER_LEFT_WALK_1 = extract (48, 16, 11, 16);
        PLAYER_UP_WALK_2 = extract (16, 32, 11, 16);
        PLAYER_RIGHT_WALK_2 = extract (32, 32, 11, 16);
        PLAYER_DOWN_WALK_2 = extract (0, 32, 11, 16);
        PLAYER_LEFT_WALK_2 = extract (48, 32, 11, 16);

        FLOOR_TILE = new TextureRegion (new Texture (Gdx.files.internal ("textures/floor_normal.png")));
        FLOOR_TILE_LIGHT_CRACKS = new TextureRegion (new Texture (Gdx.files.internal ("textures/floor_light_cracks.png")));
        FLOOR_TILE_HEAVY_CRACKS = new TextureRegion (new Texture (Gdx.files.internal ("textures/floor_heavy_cracks.png")));
        FLOOR_TILE_LIGHT_MOSS = new TextureRegion (new Texture (Gdx.files.internal ("textures/floor_light_moss.png")));
        FLOOR_TILE_HEAVY_MOSS = new TextureRegion (new Texture (Gdx.files.internal ("textures/floor_heavy_moss.png")));

        WALL_1_1 = extract (96, 0, 16, 16);
        WALL_1_2 = extract (96, 16, 16, 16);
        WALL_1_3 = extract (96, 32, 16, 16);
        WALL_1_4 = extract (96, 48, 16, 16);

        WALL_2_1 = extract (112, 0, 16, 16);
        WALL_2_2 = extract (112, 16, 16, 16);
        WALL_2_3 = extract (112, 32, 16, 16);
        WALL_2_4 = extract (112, 48, 16, 16);

        WALL_3_1 = extract (128, 0, 16, 16);
        WALL_3_2 = extract (128, 16, 16, 16);
        WALL_3_3 = extract (128, 32, 16, 16);
        WALL_3_4 = extract (128, 48, 16, 16);

        WALL_4_1 = extract (144, 0, 16, 16);
        WALL_4_2 = extract (144, 16, 16, 16);
        WALL_4_3 = extract (144, 32, 16, 16);
        WALL_4_4 = extract (144, 48, 16, 16);

        WALL_5_1 = extract (160, 0, 16, 16);
        WALL_5_2 = extract (160, 16, 16, 16);
        WALL_5_3 = extract (160, 32, 16, 16);
        WALL_5_4 = extract (160, 48, 16, 16);

        STONE_B = new TextureRegion (new Texture (Gdx.files.internal ("textures/Wall_Stonebrick_Bot.png")));
        STONE_L = new TextureRegion (new Texture (Gdx.files.internal ("textures/Wall_Stonebrick_Left.png")));
        STONE_T = new TextureRegion (new Texture (Gdx.files.internal ("textures/Wall_Stonebrick_Top.png")));
        STONE_R = new TextureRegion (new Texture (Gdx.files.internal ("textures/Wall_Stonebrick_Right.png")));
        STONE_F = new TextureRegion (new Texture (Gdx.files.internal ("textures/Wall_Stonebrick_Front.png")));
        STONE_BL = new TextureRegion (new Texture (Gdx.files.internal ("textures/Wall_Stonebrick_Bot+Left.png")));
        STONE_BT = new TextureRegion (new Texture (Gdx.files.internal ("textures/Wall_Stonebrick_Bot+Top.png")));
        STONE_BR = new TextureRegion (new Texture (Gdx.files.internal ("textures/Wall_Stonebrick_Bot+Right.png")));
        STONE_BLT = new TextureRegion (new Texture (Gdx.files.internal ("textures/Wall_Stonebrick_Bot+Left+Top.png")));
        STONE_BLR = new TextureRegion (new Texture (Gdx.files.internal ("textures/Wall_Stonebrick_Bot+Left+Right.png")));
        STONE_BTR = new TextureRegion (new Texture (Gdx.files.internal ("textures/Wall_Stonebrick_Bot+Top+Right.png")));
        STONE_BLTR = new TextureRegion (new Texture (Gdx.files.internal ("textures/Wall_Stonebrick_Bot+Left+Top+Right.png")));
        STONE_LT = new TextureRegion (new Texture (Gdx.files.internal ("textures/Wall_Stonebrick_Left+Top.png")));
        STONE_LR = new TextureRegion (new Texture (Gdx.files.internal ("textures/Wall_Stonebrick_Left+Right.png")));
        STONE_TR = new TextureRegion (new Texture (Gdx.files.internal ("textures/Wall_Stonebrick_Top+Right.png")));
    }

    private TextureRegion extract (int x, int y, int width, int height) {
        return new TextureRegion (sprites, x, y, width, height);
    }

}