package jnh.game.gameObjects.enemies;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.Entity;
import jnh.game.stages.GameStage;

public class Enemy extends Entity {

    public Enemy(GameStage stage, TextureRegion texture, Vector2 position, Vector2 dimension) {
        super(stage, texture, position, dimension);
    }
}
