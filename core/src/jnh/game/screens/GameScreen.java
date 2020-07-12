package jnh.game.screens;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import jnh.game.DungeonGame;
import jnh.game.Global;
import jnh.game.gfx.GameCamera;
import jnh.game.settings.Settings;
import jnh.game.settings.SettingsHotkeyHandler;
import jnh.game.stages.GameStage;
import jnh.game.ui.GameUI;


public class GameScreen implements Screen {

    private final DungeonGame game;

    private SpriteBatch batch;

    private GameCamera camera;
    private RayHandler rayHandler;
    private World world;
    private GameStage stage;

    private GameUI ui;
    private FPSLogger logger;

    private boolean paused = false;

    public float VIEWPORT_WIDTH = 24, VIEWPORT_HEIGHT = 24;

    public GameScreen(DungeonGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        //UI
        ui = new GameUI(this);

        //Camera
        camera = new GameCamera(this);
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        //World for physics
        world = new World(new Vector2(0f, 0f), true);

        //RayHandler
        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(new Color(1, 1, 1, 0));
        rayHandler.setBlur(true);
        rayHandler.setBlurNum(1);

        //Stage
        stage = new GameStage(this);
        stage.getViewport().setCamera(camera);

        batch = (SpriteBatch) stage.getBatch();

        logger = new FPSLogger();

        //Input
        InputMultiplexer multiplexer = new InputMultiplexer(stage, ui.getStage());
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void update(float delta) {
        if(!paused) {
            Global.elapsedTime += delta;
            stage.act(Gdx.graphics.getDeltaTime());
            world.step(1 / 60f, 6, 2);
            rayHandler.update();
        }

        camera.act(delta);

        if(Settings.isShowingUI()) {
            ui.act(delta);
            ui.getStage().getCamera().update();
        }

        SettingsHotkeyHandler.update();
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined.scl(Global.UNIT));
        batch.enableBlending();
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.begin();
        stage.getRoot().draw(batch, 1);
        batch.end();

        if(Settings.isRenderingLights()) {
            rayHandler.setCombinedMatrix(camera.combined.translate(0.5f, 0.5f, 0f));
            rayHandler.updateAndRender();
        }

        if(Settings.isShowingUI()) {
            ui.getStage().draw();
        }

        ui.updateFPS(Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void resize(int width, int height) {
        camera.resize(width, height);
        ui.getStage().getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        rayHandler.dispose();
        world.dispose();
        stage.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public World getWorld() {
        return world;
    }

    public RayHandler getRayHandler() {
        return rayHandler;
    }

    public GameCamera getGameCamera() {
        return camera;
    }

    public GameStage getStage() {
        return stage;
    }

    public GameUI getUI() {
        return ui;
    }

    public boolean isPaused() {
        return paused;
    }
}
