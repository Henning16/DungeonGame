package jnh.game.stages;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.GameObjectManager;
import jnh.game.gameObjects.Layer;
import jnh.game.gameObjects.construction.SceneHandler;
import jnh.game.gameObjects.lightObjects.LightObject;
import jnh.game.gameObjects.lightObjects.LightUpdater;
import jnh.game.screens.GameScreen;
import jnh.game.utils.TimeHandler;
import jnh.game.world.Dungeon;

public class GameStage extends Stage {

    private SceneHandler sceneHandler = new SceneHandler(this);

    private GameScreen screen;
    @Deprecated
    private LightUpdater lightUpdater;

    private GameObjectManager gameObjectManager;
    private Layer backgroundLayer, mainLayer, foregroundLayer;

    private Dungeon dungeon;


    public GameStage(GameScreen screen) {
        this.screen = screen;

        lightUpdater = new LightUpdater();

        gameObjectManager = new GameObjectManager(this);

        backgroundLayer = new Layer();
        addActor(backgroundLayer);
        mainLayer = new Layer();
        addActor(mainLayer);
        foregroundLayer = new Layer();
        addActor(foregroundLayer);

        dungeon = new Dungeon(this, System.currentTimeMillis(), 1);

        addActor(new LightObject(this, new Color(0.4f, 0.8f, 1f, 0.8f), new Vector2(9f, 9f ), 9, 0.1f, false));
        addActor(new LightObject(this, new Color(1f, 0.7f, 0.3f, 0.8f), new Vector2(3f, 3 ), 9, 0.1f, false));

        for(int i = 0; i < 1000; i++) {
            GameObject g = new GameObject(this, Assets.blueprints.AXE);
            g.setPosition((float) (Math.random() * 8) + 2, (float) (Math.random() * 8) + 2);
        }
        gameObjectManager.playerID = new GameObject(this, Assets.blueprints.PLAYER).getID();

        new GameObject(this, Assets.blueprints.ZOMBIE);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        TimeHandler.tick(delta);
        gameObjectManager.update();
        lightUpdater.update(delta);
        //if(Math.random() < 0.005f) {
            //sceneHandler.saveScene("test");
            //sceneHandler.loadScene("test");
        //}
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

    @Deprecated
    public LightUpdater getLightUpdater() {
        return lightUpdater;
    }

    public GameObjectManager getGameObjectManager() {
        return gameObjectManager;
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
