package jnh.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jnh.game.screens.GameScreen;

public class GameUI implements Disposable {

    private final GameScreen screen;

    private final Stage stage;

    private final Table root;

    private final Table valueBars;
    private final Table hotBar;

    private final ProgressBar healthBar;

    public GameUI(GameScreen screen){
        this.screen = screen;
        SpriteBatch batch = new SpriteBatch();

        new Styles();

        stage = new Stage(new ScreenViewport(), batch);

        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        valueBars = new Table();
        root.add(valueBars).left().top().pad(10, 10, 0, 0).expand();
        root.row();

        hotBar = new Table();
        root.add(hotBar).bottom();

        healthBar = new ProgressBar(0, 100, 1, false, Styles.healthBar);
        healthBar.setAnimateDuration(0.1f);

        valueBars.add(healthBar).minWidth(300);

        TextButton c = new TextButton("Hotbar", Styles.defaultButton);
        hotBar.add(c);

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

    public void updateHealthBar(int min, int max, int value) {
        if(healthBar.getValue() != value) {

        }
        healthBar.setRange(min, max);
        healthBar.setValue(value);
    }

}
