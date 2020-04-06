package jnh.game.stages;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
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

    private Group backgroundLayer, mainLayer, foregroundLayer;
    private Dungeon dungeon;
    private Player player;

    public GameStage (GameScreen screen) {
        this.screen = screen;

        input = new InputHandler (this);
        lightUpdater = new LightUpdater ();

        backgroundLayer = new Group ();
        addActor (backgroundLayer);
        mainLayer = new Group ();
        addActor (mainLayer);
        foregroundLayer = new Group ();
        addActor (foregroundLayer);

        dungeon = new Dungeon (this, System.currentTimeMillis (), 1);

        player = new Player (this, new Vector2 (3, 5));
        getMainLayer ().addActor (player);

        //addActor(new LightObject(this, new Color(0.8f, 1.0f, 0.9f, 0.8f), new Vector2(8f, 8f), 6f, 2f, false));
        addActor (new LightObject (this, new Color (1f, 0.8f, 0.8f, 0.9f), new Vector2 (6f, 6f), 10, 0.5f, false));
        //addActor(new LightObject(this, new Color(1f, 0.7f, 1f, 1f), new Vector2(10f, 3f), 5f, 1f, false));
    }

    @Override
    public void act (float delta) {
        super.act (delta);
        input.act (delta);
        lightUpdater.update (delta);
    }

    @Override
    public void dispose () {
        super.dispose ();
    }

    public GameScreen getScreen () {
        return screen;
    }

    public RayHandler getRayHandler () {
        return getScreen ().getRayHandler ();
    }

    public Player getPlayer () {
        return player;
    }

    public LightUpdater getLightUpdater () {
        return lightUpdater;
    }

    //LAYERS

    public Group getBackgroundLayer () {
        return backgroundLayer;
    }

    public Group getMainLayer () {
        return mainLayer;
    }

    public Group getForegroundLayer () {
        return foregroundLayer;
    }
}
