package jnh.game.gameObjects.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.Entity;
import jnh.game.stages.GameStage;

public class Enemy extends Entity {

    public Enemy(GameStage stage, Texture texture, Vector2 position, Vector2 dimension) {
        super(stage, texture, position, dimension);
    }
}
