package jnh.game.gameObjects.roomBoundaries;

import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;
import jnh.game.gfx.Assets;
import jnh.game.stages.GameStage;

public class FloorTile extends GameObject {

    public FloorTile(GameStage stage, Vector2 position) {
        super(stage, Assets.FLOOR_TILE, position, new Vector2(1, 1));
    }
}
