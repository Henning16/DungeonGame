package jnh.game.gameObjects.roomBoundaries;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;
import jnh.game.stages.GameStage;

public class FloorTile extends GameObject {

    public FloorTile (GameStage stage, TextureRegion texture, Vector2 position) {
        super (stage, texture, position, new Vector2 (1, 1));
    }
}
