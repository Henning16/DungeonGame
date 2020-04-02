package jnh.game.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import jnh.game.Global;
import jnh.game.gfx.Assets;
import jnh.game.stages.GameStage;

public class Player extends Entity {
    public Player(GameStage stage, Vector2 position) {
        super(stage, Assets.PLAYER, position, new Vector2(1, 1));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
