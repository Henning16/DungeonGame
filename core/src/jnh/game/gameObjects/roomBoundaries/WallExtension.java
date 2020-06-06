package jnh.game.gameObjects.roomBoundaries;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;
import jnh.game.stages.GameStage;

public class WallExtension extends GameObject {

    public WallExtension(GameStage stage, Animation<TextureRegion> animation, Vector2 position) {
        super(stage, animation, position, new Vector2(1f, 1f));
        getAnimator().play(animation, true);
    }

}
