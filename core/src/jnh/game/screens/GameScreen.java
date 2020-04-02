package jnh.game.screens;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import jnh.game.DungeonGame;
import jnh.game.Global;
import jnh.game.gfx.GameCamera;
import jnh.game.stages.GameStage;


public class GameScreen implements Screen {

    private DungeonGame game;

    private FitViewport viewport;

    private GameCamera camera;
    private RayHandler rayHandler;

    private World world;
    private GameStage stage;

    private FPSLogger logger;
    public GameScreen(DungeonGame game) {
        this.game = game;
    }
    @Override
    public void show() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        camera = new GameCamera(this);

        world = new World(new Vector2(0f, 0f), true);

        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(new Color(1.0f, 0.3f, 0f, 0.15f));
        rayHandler.setBlur(true);
        rayHandler.setBlurNum(1);

        PointLight p = new PointLight(rayHandler, 1000, new Color(1.0f, 0.5f, 0f, 1f), 8, 4, 4);
        p.setSoftnessLength(3);
        p.setXray(false);
        //p.setContactFilter((short) 0x0001, (short) 1, (short) 0);//setContactFilter(0x0001, 1, 0);

        stage = new GameStage(this);
        stage.getViewport().setCamera(camera);
        Gdx.input.setInputProcessor(stage);

        logger = new FPSLogger();
    }

    public void update(float delta) {
        stage.act(Gdx.graphics.getDeltaTime());
        world.step(1 / 60f, 6, 2);
        rayHandler.update();
        camera.act(delta);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Batch batch = stage.getBatch();

        batch.setProjectionMatrix(camera.combined.scl(Global.UNIT));
        batch.begin();
        stage.getRoot().draw(batch, 1);
        batch.end();
        rayHandler.setCombinedMatrix(camera.combined.translate(0.5f, 0.5f, 0f));
        rayHandler.updateAndRender();
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

    public World getWorld() {
        return world;
    }

    public GameStage getStage() {
        return stage;
    }
}
