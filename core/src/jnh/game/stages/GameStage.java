package jnh.game.stages;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import jnh.game.Global;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.GameObjectManager;
import jnh.game.gameObjects.Layer;
import jnh.game.gameObjects.construction.SceneHandler;
import jnh.game.screens.GameScreen;
import jnh.game.utils.TimeHandler;
import jnh.game.world.Dungeon;

public class GameStage extends Stage {

    private SceneHandler sceneHandler = new SceneHandler(this);

    private GameScreen screen;

    private GameObjectManager gameObjectManager;
    private Group backgroundLayer;
    private Group mainLayer;
    private Group foregroundLayer;

    private Dungeon dungeon;


    public GameStage(GameScreen screen) {
        this.screen = screen;

        gameObjectManager = new GameObjectManager(this);

        backgroundLayer = new Layer();
        
        addActor(backgroundLayer);
        mainLayer = new Layer();
        addActor(mainLayer);
        foregroundLayer = new Layer();
        addActor(foregroundLayer);

        dungeon = new Dungeon(this, System.currentTimeMillis(), 1);

        for(int i = 0; i < 10; i++) {
            GameObject g = new GameObject(this, Assets.blueprints.AXE);
            g.setPosition((float) (Math.random() * 7) + 6, (float) (Math.random() * 7) + 6);
        }
        gameObjectManager.playerID = new GameObject(this, Assets.blueprints.PLAYER).getID();

        new GameObject(this, Assets.blueprints.ZOMBIE);
        //GameObject g = new GameObject(this, Assets.blueprints.LOGPILE);

        getMainLayer().setDebug(true, true);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        TimeHandler.tick(delta);
        gameObjectManager.update();
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

    public Vector2 convertScreenPositionToWorldPosition(Vector2 screenPosition) {
        Vector2 worldPosition = new Vector2(
                ((screenPosition.x / Gdx.graphics.getWidth()) - 0.5f) * (screen.VIEWPORT_WIDTH / Global.UNIT) + (screen.getGameCamera().position.x / Global.UNIT),
                ((- screenPosition.y / Gdx.graphics.getHeight()) + 0.5f) * (screen.VIEWPORT_HEIGHT / Global.UNIT) + (screen.getGameCamera().position.y / Global.UNIT));
        return new Vector2(worldPosition.x, worldPosition.y);
    }
}
