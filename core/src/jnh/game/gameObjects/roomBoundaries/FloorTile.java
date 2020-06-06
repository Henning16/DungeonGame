package jnh.game.gameObjects.roomBoundaries;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;
import jnh.game.stages.GameStage;

public class FloorTile extends GameObject {

    public static class Type {
        public static final int NORMAL = 0, LIGHT_CRACKS = 1, HEAVY_CRACKS = 2, LIGHT_MOSS = 3, HEAVY_MOSS = 4;
    }

    public FloorTile(GameStage stage, Animation<TextureRegion> animation, Vector2 position) {
        super(stage, animation, position, new Vector2(1, 1));
        getAnimator().play(animation, true);
    }
}