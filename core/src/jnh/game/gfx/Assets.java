package jnh.game.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.awt.image.BufferedImage;

public class Assets {

    public static Texture PLAYER;
    public static Texture FLOOR_TILE;
    public static Texture STONE_B, STONE_L, STONE_T, STONE_R, STONE_F, STONE_BL, STONE_BT, STONE_BR, STONE_BLT, STONE_BLR, STONE_BTR, STONE_BLTR, STONE_LT, STONE_LR, STONE_TR;

    public Assets() {
        PLAYER = new Texture(Gdx.files.internal("textures/player.png"));

        FLOOR_TILE = new Texture(Gdx.files.internal("textures/floor_normal.png"));
        STONE_B = new Texture(Gdx.files.internal("textures/Wall_Stonebrick_Bot.png"));
        STONE_L = new Texture(Gdx.files.internal("textures/Wall_Stonebrick_Left.png"));
        STONE_T = new Texture(Gdx.files.internal("textures/Wall_Stonebrick_Top.png"));
        STONE_R = new Texture(Gdx.files.internal("textures/Wall_Stonebrick_Right.png"));
        STONE_F = new Texture(Gdx.files.internal("textures/Wall_Stonebrick_Front.png"));
        STONE_BL = new Texture(Gdx.files.internal("textures/Wall_Stonebrick_Bot+Left.png"));
        STONE_BT = new Texture(Gdx.files.internal("textures/Wall_Stonebrick_Bot+Top.png"));
        STONE_BR = new Texture(Gdx.files.internal("textures/Wall_Stonebrick_Bot+Right.png"));
        STONE_BLT = new Texture(Gdx.files.internal("textures/Wall_Stonebrick_Bot+Left+Top.png"));
        STONE_BLR = new Texture(Gdx.files.internal("textures/Wall_Stonebrick_Bot+Left+Right.png"));
        STONE_BTR = new Texture(Gdx.files.internal("textures/Wall_Stonebrick_Bot+Top+Right.png"));
        STONE_BLTR = new Texture(Gdx.files.internal("textures/Wall_Stonebrick_Bot+Left+Top+Right.png"));
        STONE_LT = new Texture(Gdx.files.internal("textures/Wall_Stonebrick_Left+Top.png"));
        STONE_LR = new Texture(Gdx.files.internal("textures/Wall_Stonebrick_Left+Right.png"));
        STONE_TR = new Texture(Gdx.files.internal("textures/Wall_Stonebrick_Top+Right.png"));
    }

}