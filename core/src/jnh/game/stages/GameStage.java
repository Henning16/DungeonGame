package jnh.game.stages;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.entities.MovementState;
import jnh.game.gameObjects.entities.Player;
import jnh.game.gameObjects.items.Item;
import jnh.game.gameObjects.lightObjects.LightObject;
import jnh.game.gameObjects.lightObjects.LightUpdater;
import jnh.game.input.InputHandler;
import jnh.game.screens.GameScreen;
import jnh.game.utils.Direction;
import jnh.game.world.Dungeon;

import java.util.LinkedList;

public class GameStage extends Stage {

    private GameScreen screen;
    private InputHandler input;
    private LightUpdater lightUpdater;

    private Group backgroundLayer, mainLayer, foregroundLayer;
    private LinkedList<Item> items;

    private Dungeon dungeon;
    private Player player;



    public GameStage(GameScreen screen) {
        this.screen = screen;

        input = new InputHandler(this);
        lightUpdater = new LightUpdater();

        backgroundLayer = new Group();
        addActor(backgroundLayer);
        mainLayer = new Group();
        addActor(mainLayer);
        foregroundLayer = new Group();
        addActor(foregroundLayer);

        items = new LinkedList<>();

        dungeon = new Dungeon(this, System.currentTimeMillis(), 1);

        player = new Player(this, new Vector2(3, 5));
        getMainLayer().addActor(player);

        addActor(new LightObject(this, new Color(0.8f, 1f, 0.9f, 0.9f), new Vector2(8f, 8f ), 14, 0.1f, false));
        addActor(new LightObject(this, new Color(1f, 0.7f, 0.3f, 0.8f), new Vector2(2f, 2f ), 9, 0.1f, false));
        addActor(new LightObject(this, new Color(0.9f, 0.8f, 1f, 0.8f), new Vector2(1f, 9f ), 10, 0.1f, false));

        addActor(new Item(this, Assets.textures.PLAYER[Direction.UP][MovementState.WALK], Assets.textures.PLAYER[Direction.UP][MovementState.WALK], new Vector2(4,4)));
        addActor(new Item(this, Assets.textures.PLAYER[Direction.UP][MovementState.WALK], Assets.textures.PLAYER[Direction.UP][MovementState.WALK], new Vector2(5,4)));
        addActor(new Item(this, Assets.textures.PLAYER[Direction.UP][MovementState.WALK], Assets.textures.PLAYER[Direction.UP][MovementState.WALK], new Vector2(9,7)));

        GameObject g = new GameObject(this, Assets.blueprints.PLAYER);
        getMainLayer().addActor(g);
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

    public LinkedList<Item> getItems() {
        return items;
    }

    //LAYERS

    public Group getBackgroundLayer() {
        return backgroundLayer;
    }

    public Group getMainLayer() {
        return mainLayer;
    }

    public Group getForegroundLayer() {
        return foregroundLayer;
    }
}
