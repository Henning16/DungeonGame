package jnh.game.gameObjects.entities.enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.entities.Entity;
import jnh.game.stages.GameStage;

public class Enemy extends Entity {

    public Enemy(GameStage stage, Animation<TextureRegion> animation, Vector2 position, Vector2 dimension) {
        super(stage, animation, position, dimension);
    }
}
