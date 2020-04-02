package jnh.game.stages;

import box2dLight.RayHandler;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import jnh.game.gameObjects.Player;
import jnh.game.input.InputHandler;
import jnh.game.screens.GameScreen;
import jnh.game.world.Dungeon;

public class GameStage extends Stage {

    private GameScreen screen;
    private InputHandler input;

    private Dungeon dungeon;
    private Player player;

    public GameStage(GameScreen screen) {
        this.screen = screen;

        input = new InputHandler(this);

        dungeon = new Dungeon(this, System.currentTimeMillis(), 1);
        player = new Player(this, new Vector2(3, 5));
        addActor(player);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        input.act(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public GameScreen getScreen() {
        return screen;
    }

    public RayHandler getRayHandler() {
        return getScreen().getRayHandler();
    }

    public Player getPlayer() {
        return player;
    }
}
