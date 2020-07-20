package jnh.game.screens;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import jnh.game.DungeonGame;
import jnh.game.Global;
import jnh.game.gfx.ColorGrading;
import jnh.game.gfx.GameCamera;
import jnh.game.gfx.animations.ColorGrader;
import jnh.game.settings.Settings;
import jnh.game.settings.SettingsHotkeyHandler;
import jnh.game.stages.GameStage;
import jnh.game.ui.GameUI;


public class GameScreen implements Screen {

    private final DungeonGame game;

    private SpriteBatch batch;
    private SpriteBatch fboBatch;

    private final GameCamera camera;
    private final RayHandler rayHandler;

    private FrameBuffer fbo;
    private final ShaderProgram shaderProgram = new ShaderProgram(Gdx.files.internal("shaders/vertex.glsl"), Gdx.files.internal("shaders/fragment.glsl"));
    private final ColorGrader colorGrader;

    private World physicsWorld;
    private GameStage stage;

    private GameUI ui;

    private boolean paused = false;

    public float VIEWPORT_WIDTH = 24, VIEWPORT_HEIGHT = 24;

    public GameScreen(DungeonGame game) {
        this.game = game;

        //UI
        ui = new GameUI(this);
        //Camera
        camera = new GameCamera(this);
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        //World for physics
        physicsWorld = new World(new Vector2(0f, 0f), true);

        //Color grading
        colorGrader = new ColorGrader();

        //RayHandler
        rayHandler = new RayHandler(physicsWorld);
        rayHandler.setAmbientLight(new Color(1, 1, 1, 0));
        rayHandler.setBlur(true);
        rayHandler.setBlurNum(1);

        //Stage
        stage = new GameStage(this);
        stage.getViewport().setCamera(camera);

        batch = (SpriteBatch) stage.getBatch();
        fboBatch = new SpriteBatch();
    }

    @Override
    public void show() {
        //Input
        InputMultiplexer multiplexer = new InputMultiplexer(stage, ui.getStage());
        Gdx.input.setInputProcessor(multiplexer);
        colorGrader.setColorGrading(ColorGrading.NORMAL);
        fbo = new FrameBuffer(
                Pixmap.Format.RGBA8888,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                false
        );
    }

    public void update(float delta) {
        if(!paused) {
            Global.elapsedTime += delta;
            stage.act(Gdx.graphics.getDeltaTime());
            physicsWorld.step(1 / 60f, 6, 2);
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

        fbo.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined.scl(Global.UNIT));
        batch.begin();
        stage.getRoot().draw(batch, 1);
        batch.end();
        fbo.end();
        if(Settings.isRenderingLights()) {
            rayHandler.setCombinedMatrix(camera.combined.translate(0.5f, 0.5f, 0f));
            rayHandler.updateAndRender();
        }
        fbo.getColorBufferTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        TextureRegion fboRegion = new TextureRegion(fbo.getColorBufferTexture(), 0, 0, fbo.getWidth(), fbo.getHeight());
        fboRegion.flip(false, true);
        TextureRegion lightFboRegion = new TextureRegion(rayHandler.getLightMapBuffer().getColorBufferTexture(), 0, 0, rayHandler.getLightMapBuffer().getWidth(), rayHandler.getLightMapBuffer().getHeight());
        lightFboRegion.flip(false, true);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, camera.viewportWidth, camera.viewportHeight));
        colorGrader.update(shaderProgram);
        batch.setShader(shaderProgram);
        batch.draw(fboRegion, 0, 0, camera.viewportWidth, camera.viewportHeight);
        batch.setBlendFunction(GL20.GL_ZERO, GL20.GL_SRC_ALPHA);
        batch.draw(lightFboRegion, 0, 0, camera.viewportWidth, camera.viewportHeight);
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        batch.draw(lightFboRegion, 0, 0, camera.viewportWidth, camera.viewportHeight);
        batch.setBlendFunction(-1, -1);
        batch.end();
        if(Settings.isShowingUI()) {
            ui.getStage().draw();
            ui.updateFPS(Gdx.graphics.getFramesPerSecond());
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.resize(width, height);
        ui.getStage().getViewport().update(width, height, true);
        fbo = new FrameBuffer(
                Pixmap.Format.RGBA8888, width, height, false
        );
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
        stage.close();
    }

    @Override
    public void dispose() {
        stage.dispose();
        fbo.dispose();
        rayHandler.dispose();
        physicsWorld.dispose();
    }

    public DungeonGame getGame() {
        return game;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public World getPhysicsWorld() {
        return physicsWorld;
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
