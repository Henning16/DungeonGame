package jnh.game.stages;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.SnapshotArray;
import jnh.game.Global;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.GameObjectManager;
import jnh.game.gameObjects.components.BodyComponent;
import jnh.game.screens.GameScreen;
import jnh.game.ui.GameUI;
import jnh.game.ui.notifications.Notification;
import jnh.game.ui.notifications.NotificationHandler;
import jnh.game.utils.TimeHandler;
import jnh.game.world.Dungeon;
import jnh.game.world.Room;
import jnh.game.world.World;

import java.io.FileNotFoundException;

/**
 * This class is used for the actual stages for the game where actors like the player or other objects of the world will be placed in.
 */
public class GameStage extends Stage {

    private final GameScreen screen;

    private GameObjectManager gameObjectManager;
    private final Group backgroundLayer;
    private final Group mainLayer;
    private final Group foregroundLayer;

    private World world;
    private final Dungeon dungeon;
    @Deprecated
    private Room room;


    /**
     * TODO this constructor needs to be cleaned up or seperated.
     * Creates a new GameStage
     * @param screen the reference to the game screen, which handles drawing and more.
     */
    public GameStage(GameScreen screen) {
        this.screen = screen;

        gameObjectManager = new GameObjectManager(this);
        backgroundLayer = new Group();
        addActor(backgroundLayer);
        mainLayer = new Group();
        addActor(mainLayer);
        foregroundLayer = new Group();
        addActor(foregroundLayer);
        dungeon = new Dungeon(this);
    }

    public void setWorld(World world) {
        this.world = world;

        final int x = world.getSceneID() % Dungeon.DUNGEON_SIZE;
        final int y = (world.getSceneID() - x) / Dungeon.DUNGEON_SIZE;
        dungeon.setRoom(y, x, false);

        //TEMP
        if(gameObjectManager.playerID == null) {
            gameObjectManager.playerID = new GameObject(this, Assets.blueprints.PLAYER).getID();
            gameObjectManager.getGameObject(gameObjectManager.playerID).setPersistent(true);
        }


        try {
            world.save();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        TimeHandler.tick(delta);
        gameObjectManager.update();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            try {
                world.save();
                NotificationHandler.addNotification(new Notification("Save", "World sucessfully saved."));
            } catch (FileNotFoundException e) {
                NotificationHandler.addNotification(new Notification("Save", "World not correctly saved."));
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    /**
     * Returns the game screen as it is provided via {@link GameStage#GameStage(GameScreen)}.
     * @return the game screen
     */
    public GameScreen getScreen() {
        return screen;
    }

    public World getWorld() {
        return world;
    }

    /**
     * Returns the ray handler handling lights.
     * This has the same effect as using {@link GameScreen#getRayHandler()}.
     * @return the ray handler
     */
    public RayHandler getRayHandler() {
        return getScreen().getRayHandler();
    }

    /**
     * Returns the game object manager, which handles ids for every game object.
     * @return the game object manager
     */
    public GameObjectManager getGameObjectManager() {
        return gameObjectManager;
    }

    public void setGameObjectManager(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
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

    /**
     * TODO move to a different position?
     * Converts the provided screen position into a world position by taking the screen size, the viewport, the camera position as well as the scale into account.
     * @param screenPosition the position in screen coordinates
     * @return the position in world coordinates
     */
    public Vector2 convertScreenPositionToWorldPosition(Vector2 screenPosition) {
        Vector2 worldPosition = new Vector2(
                ((screenPosition.x / Gdx.graphics.getWidth()) - 0.5f) * (screen.VIEWPORT_WIDTH / Global.UNIT) + (screen.getGameCamera().position.x / Global.UNIT),
                ((- screenPosition.y / Gdx.graphics.getHeight()) + 0.5f) * (screen.VIEWPORT_HEIGHT / Global.UNIT) + (screen.getGameCamera().position.y / Global.UNIT));
        return new Vector2(worldPosition.x, worldPosition.y);
    }

    /**
     * Returns the object handling ui.
     * This has the same effect as {@link GameScreen#getUI()}.
     * @return the ui
     */
    public GameUI getUI() {
        return screen.getUI();
    }

    @Deprecated
    public void setRoom(Room room) {
        this.room = room;
    }

    @Deprecated
    public Room getRoom() {
        return room;
    }

}
