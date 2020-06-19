package jnh.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import jnh.game.screens.GameScreen;

public class GameUI implements Disposable {

    private GameScreen screen;

    private Stage stage;
    private Viewport viewport;
    private SpriteBatch batch;

    private Table root;


    public GameUI(GameScreen screen){
        this.screen = screen;
        this.batch = new SpriteBatch();

        new Styles();

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, batch);

        root = new Table();
        root.setFillParent(true);
        root.setDebug(true);

        TextButton button = new TextButton("Button", Styles.defaultButton);
        button.setText("Hello this is some text");
        button.setPosition(4, 4);
        root.addActor(button);
        Gdx.input.setInputProcessor(stage);
    }

    public void act(float delta) {
        stage.act(delta);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }
}
