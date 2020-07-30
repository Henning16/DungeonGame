package jnh.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jnh.game.gameObjects.entities.MovementState;
import jnh.game.utils.Direction;

public class Textures {

    private final Texture sprites, PLAYER_SHEET, ZOMBIE_SHEET, FLOOR_SHEET, WALL_SHEET, WEAPONS_SHEET, PROPS_SHEET, PARTICLE_SHEET;
    public final Animation ERROR;
    public final Animation<TextureRegion>[][] PLAYER, ZOMBIE;
    public final Animation<TextureRegion>[] FLOOR_TILE;
    public final Animation<TextureRegion>[][] WALL;
    public final Animation<TextureRegion>[][] WALL_NEW;
    public final Animation AXE, SWORD;
    public final Animation LOGPILE, CRATE;
    public final Animation BLOOD;

    public Textures() {
        sprites = new Texture(Gdx.files.internal("textures/textures.png"));

        ERROR = new Animation<>(1f,
                get(sprites, 496, 496, 16, 16));

        PLAYER_SHEET = new Texture(Gdx.files.internal("textures/playeralt.png"));
        ZOMBIE_SHEET = new Texture(Gdx.files.internal("textures/zombie.png"));
        FLOOR_SHEET = new Texture(Gdx.files.internal("textures/floor.png"));
        WALL_SHEET = new Texture(Gdx.files.internal("textures/wall.png"));
        WEAPONS_SHEET = new Texture(Gdx.files.internal("textures/weapons.png"));
        PROPS_SHEET = new Texture(Gdx.files.internal("textures/props.png"));
        PARTICLE_SHEET = new Texture(Gdx.files.internal("textures/particle.png"));

        PLAYER = new Animation[4][2];
        ZOMBIE = new Animation[4][2];
        FLOOR_TILE = new Animation[8];
        WALL = new Animation[5][4];
        WALL_NEW = new Animation[5][4];

        PLAYER[Direction.UP][MovementState.IDLE] = new Animation<>(1f,
                get(PLAYER_SHEET, 13, 2, 9, 16));
                PLAYER[Direction.UP][MovementState.IDLE].setPlayMode(Animation.PlayMode.LOOP);
        PLAYER[Direction.RIGHT][MovementState.IDLE] = new Animation<>(1f,
                get(PLAYER_SHEET, 24, 2, 9, 16));
                PLAYER[Direction.RIGHT][MovementState.IDLE].setPlayMode(Animation.PlayMode.LOOP);
        PLAYER[Direction.DOWN][MovementState.IDLE] = new Animation<>(1f,
                get(PLAYER_SHEET, 2, 2, 9, 16));
                PLAYER[Direction.DOWN][MovementState.IDLE].setPlayMode(Animation.PlayMode.LOOP);
        PLAYER[Direction.LEFT][MovementState.IDLE] = new Animation<>(1f,
                get(PLAYER_SHEET, 35, 2, 9, 16));
                PLAYER[Direction.LEFT][MovementState.IDLE].setPlayMode(Animation.PlayMode.LOOP);
        PLAYER[Direction.UP][MovementState.WALK] = new Animation<>(0.2f,
                get(PLAYER_SHEET, 13, 2, 9, 16),
                get(PLAYER_SHEET, 13, 20, 9, 16),
                get(PLAYER_SHEET, 13, 38, 9, 16));
                PLAYER[Direction.UP][MovementState.WALK].setPlayMode(Animation.PlayMode.LOOP);
        PLAYER[Direction.RIGHT][MovementState.WALK] = new Animation<>(0.2f,
                get(PLAYER_SHEET, 24, 2, 9, 16),
                get(PLAYER_SHEET, 24, 20, 9, 16),
                get(PLAYER_SHEET, 24, 38, 9, 16));
                PLAYER[Direction.RIGHT][MovementState.WALK].setPlayMode(Animation.PlayMode.LOOP);
        PLAYER[Direction.DOWN][MovementState.WALK] = new Animation<>(0.2f,
                get(PLAYER_SHEET, 2, 2, 9, 16),
                get(PLAYER_SHEET, 2, 20, 9, 16),
                get(PLAYER_SHEET, 2, 38, 9, 16));
                PLAYER[Direction.DOWN][MovementState.WALK].setPlayMode(Animation.PlayMode.LOOP);
        PLAYER[Direction.LEFT][MovementState.WALK] = new Animation<>(0.2f,
                get(PLAYER_SHEET, 35, 2, 9, 16),
                get(PLAYER_SHEET, 35, 20, 9, 16),
                get(PLAYER_SHEET, 35, 38, 9, 16));
                PLAYER[Direction.LEFT][MovementState.WALK].setPlayMode(Animation.PlayMode.LOOP);

        ZOMBIE[Direction.UP][MovementState.IDLE] = new Animation<>(1f,
                get(ZOMBIE_SHEET, 13, 2, 9, 16));
        ZOMBIE[Direction.UP][MovementState.IDLE].setPlayMode(Animation.PlayMode.LOOP);
        ZOMBIE[Direction.RIGHT][MovementState.IDLE] = new Animation<>(1f,
                get(ZOMBIE_SHEET, 24, 2, 9, 16));
        ZOMBIE[Direction.RIGHT][MovementState.IDLE].setPlayMode(Animation.PlayMode.LOOP);
        ZOMBIE[Direction.DOWN][MovementState.IDLE] = new Animation<>(1f,
                get(ZOMBIE_SHEET, 2, 2, 9, 16));
        ZOMBIE[Direction.DOWN][MovementState.IDLE].setPlayMode(Animation.PlayMode.LOOP);
        ZOMBIE[Direction.LEFT][MovementState.IDLE] = new Animation<>(1f,
                get(ZOMBIE_SHEET, 35, 2, 9, 16));
        ZOMBIE[Direction.LEFT][MovementState.IDLE].setPlayMode(Animation.PlayMode.LOOP);
        ZOMBIE[Direction.UP][MovementState.WALK] = new Animation<>(0.2f,
                get(ZOMBIE_SHEET, 13, 2, 9, 16),
                get(ZOMBIE_SHEET, 13, 20, 9, 16),
                get(ZOMBIE_SHEET, 13, 38, 9, 16));
        ZOMBIE[Direction.UP][MovementState.WALK].setPlayMode(Animation.PlayMode.LOOP);
        ZOMBIE[Direction.RIGHT][MovementState.WALK] = new Animation<>(0.2f,
                get(ZOMBIE_SHEET, 24, 2, 9, 16),
                get(ZOMBIE_SHEET, 24, 20, 9, 16),
                get(ZOMBIE_SHEET, 24, 38, 9, 16));
        ZOMBIE[Direction.RIGHT][MovementState.WALK].setPlayMode(Animation.PlayMode.LOOP);
        ZOMBIE[Direction.DOWN][MovementState.WALK] = new Animation<>(0.2f,
                get(ZOMBIE_SHEET, 2, 2, 9, 16),
                get(ZOMBIE_SHEET, 2, 20, 9, 16),
                get(ZOMBIE_SHEET, 2, 38, 9, 16));
        ZOMBIE[Direction.DOWN][MovementState.WALK].setPlayMode(Animation.PlayMode.LOOP);
        ZOMBIE[Direction.LEFT][MovementState.WALK] = new Animation<>(0.2f,
                get(ZOMBIE_SHEET, 35, 2, 9, 16),
                get(ZOMBIE_SHEET, 35, 20, 9, 16),
                get(ZOMBIE_SHEET, 35, 38, 9, 16));
        ZOMBIE[Direction.LEFT][MovementState.WALK].setPlayMode(Animation.PlayMode.LOOP);

        FLOOR_TILE[0] = new Animation<>(1f,
                get(FLOOR_SHEET, 0 , 0, 16, 16));
                FLOOR_TILE[0].setPlayMode(Animation.PlayMode.NORMAL);
        FLOOR_TILE[1] = new Animation<>(1f,
                get(FLOOR_SHEET, 16 , 0, 16, 16));
                FLOOR_TILE[1].setPlayMode(Animation.PlayMode.NORMAL);
        FLOOR_TILE[2] = new Animation<>(1f,
                get(FLOOR_SHEET, 32 , 0, 16, 16));
                FLOOR_TILE[2].setPlayMode(Animation.PlayMode.NORMAL);
        FLOOR_TILE[3] = new Animation<>(1f,
                get(FLOOR_SHEET, 48 , 0, 16, 16));
                FLOOR_TILE[3].setPlayMode(Animation.PlayMode.NORMAL);
        FLOOR_TILE[4] = new Animation<>(1f,
                get(FLOOR_SHEET, 64 , 0, 16, 16));
                FLOOR_TILE[4].setPlayMode(Animation.PlayMode.NORMAL);
        FLOOR_TILE[5] = new Animation<>(1f,
                get(FLOOR_SHEET, 80 , 0, 16, 16));
                FLOOR_TILE[5].setPlayMode(Animation.PlayMode.NORMAL);
        FLOOR_TILE[6] = new Animation<>(1f,
                get(FLOOR_SHEET, 96 , 0, 16, 16));
                FLOOR_TILE[6].setPlayMode(Animation.PlayMode.NORMAL);
        FLOOR_TILE[7] = new Animation<>(1f,
                get(FLOOR_SHEET, 112 , 0, 16, 16));
                FLOOR_TILE[7].setPlayMode(Animation.PlayMode.NORMAL);

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

        WALL_NEW[0][0] = new Animation<>(1f,
                get(WALL_SHEET, 2, 2, 5, 20));
        WALL_NEW[0][1] = new Animation<>(1f,
                get(WALL_SHEET, 2, 24, 5, 16));
        WALL_NEW[0][2] = new Animation<>(1f,
                get(WALL_SHEET, 2, 42, 5, 20));

        WALL_NEW[1][0] = new Animation<>(1f,
                get(WALL_SHEET, 9, 2, 16, 20));
        WALL_NEW[1][1] = new Animation<>(1f,
                get(WALL_SHEET, 9, 24, 16, 12));
        WALL_NEW[1][2] = new Animation<>(1f,
                get(WALL_SHEET, 9, 42, 16, 20));

        WALL_NEW[2][0] = new Animation<>(1f,
                get(WALL_SHEET, 27, 2, 5, 20));
        WALL_NEW[2][1] = new Animation<>(1f,
                get(WALL_SHEET, 27, 24, 5, 16));
        WALL_NEW[2][2] = new Animation<>(1f,
                get(WALL_SHEET, 27, 42, 5, 20));

        AXE = new Animation(1f,
                get(WEAPONS_SHEET, 0, 0, 5, 12));
        SWORD = new Animation(1f,
                get(WEAPONS_SHEET, 5, 0, 5, 12));
        LOGPILE = new Animation(1f,
                get(PROPS_SHEET, 2, 2, 13, 16));
        CRATE = new Animation(1f,
                get(PROPS_SHEET, 17, 2, 13, 17));
        BLOOD = new Animation(1f,
                get(PARTICLE_SHEET, 0, 0, 1, 1));
    }

    private TextureRegion get(Texture texture, int x, int y, int width, int height) {
        return new TextureRegion(texture, x, y, width, height);
    }

    public Animation<TextureRegion> getTexture(String name) {
        try {
            return (Animation<TextureRegion>) this.getClass().getField(name).get(this);
        } catch(Exception e) {
            System.err.println("[TextureComponent]: "+ name +" not found.");
            return ERROR;
        }
    }

    public Animation<TextureRegion>[] getTextureSet(String name) {
        try {
            return (Animation<TextureRegion>[]) this.getClass().getField(name).get(this);
        } catch(Exception e) {
            System.err.println("[TextureComponent]: "+ name +" not found.");
            return new Animation[] {ERROR};
        }
    }

    public Animation<TextureRegion>[][] getTextureSet2(String name) {
        try {
            return (Animation<TextureRegion>[][]) this.getClass().getField(name).get(this);
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("[TextureComponent]: "+ name +" not found.");
            return new Animation[][] {{ERROR}};
        }
    }

    public Animation<TextureRegion>[][][] getTextureSet3(String name) {
        try {
            return (Animation<TextureRegion>[][][]) this.getClass().getField(name).get(this);
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("[TextureComponent]: "+ name +" not found.");
            return new Animation[][][] {{{ERROR}}};
        }
    }

    public void dispose() {
        sprites.dispose();
        PLAYER_SHEET.dispose();
        ZOMBIE_SHEET.dispose();
        WEAPONS_SHEET.dispose();
        PROPS_SHEET.dispose();
        FLOOR_SHEET.dispose();
        WALL_SHEET.dispose();
        PARTICLE_SHEET.dispose();
    }

}
