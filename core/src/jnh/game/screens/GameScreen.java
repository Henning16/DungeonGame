package jnh.game.screens;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import jnh.game.DungeonGame;
import jnh.game.Global;
import jnh.game.gfx.GameCamera;
import jnh.game.stages.GameStage;
import jnh.game.ui.GameUI;


public class GameScreen implements Screen {

    private DungeonGame game;

    private SpriteBatch batch;

    private GameCamera camera;
    private RayHandler rayHandler;
    private World world;
    private GameStage stage;

    private GameUI ui;
    private FPSLogger logger;

    public float VIEWPORT_WIDTH = 24, VIEWPORT_HEIGHT = 24;

    public GameScreen(DungeonGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new GameCamera(this);
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        world = new World(new Vector2(0f, 0f), true);

        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(new Color(1f, 1f, 1f, 0.04f));
        rayHandler.setBlur(true);
        rayHandler.setBlurNum(1);

        stage = new GameStage(this);
        stage.getViewport().setCamera(camera);
        Gdx.input.setInputProcessor(stage);

        batch = (SpriteBatch) stage.getBatch();

        ui = new GameUI(this);
        logger = new FPSLogger();
    }

    public void update(float delta) {
        Global.elapsedTime += delta;
        stage.act(Gdx.graphics.getDeltaTime());
        world.step(1 / 60f, 6, 2);
        rayHandler.update();
        camera.act(delta);
        ui.act(delta);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

        batch.setProjectionMatrix(camera.combined.scl(Global.UNIT));
        batch.begin();
        stage.getRoot().draw(batch, 1);
        batch.end();

        rayHandler.setCombinedMatrix(camera.combined.translate(0.5f, 0.5f, 0f));
        rayHandler.updateAndRender();

        ui.getStage().getBatch().setProjectionMatrix(ui.getStage().getCamera().combined);
        ui.getStage().draw();

        logger.log();
    }

    @Override
    public void resize(int width, int height) {
        camera.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
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
}
