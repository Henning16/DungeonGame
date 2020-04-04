package jnh.game.stages;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import jnh.game.gameObjects.Player;
import jnh.game.gameObjects.lightObjects.LightObject;
import jnh.game.gameObjects.lightObjects.LightUpdater;
import jnh.game.input.InputHandler;
import jnh.game.screens.GameScreen;
import jnh.game.world.Dungeon;

public class GameStage extends Stage {

    private GameScreen screen;
    private InputHandler input;
    private LightUpdater lightUpdater;

    private Dungeon dungeon;
    private Player player;

    public GameStage(GameScreen screen) {
        this.screen = screen;

        input = new InputHandler(this);
        lightUpdater = new LightUpdater();

        dungeon = new Dungeon(this, System.currentTimeMillis(), 1);
        player = new Player(this, new Vector2(3, 5));
        addActor(player);

        addActor(new LightObject(this, new Color(0.8f, 1.0f, 0.9f, 0.8f), new Vector2(7f, 7f), 6f, 1f, false));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        input.act(delta);
        lightUpdater.update(delta);
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

    public LightUpdater getLightUpdater() {
        return lightUpdater;
    }
}
