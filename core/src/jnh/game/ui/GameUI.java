package jnh.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jnh.game.screens.GameScreen;
import jnh.game.settings.Settings;

public class GameUI implements Disposable {

    private final GameScreen screen;

    private final Stage stage;

    private final Stack root;
    private final Table playUI;
    private final Table notificationUI;

    private final Table valueBars;
    private final Table hotBar;

    private final ProgressBar healthBar;

    public GameUI(GameScreen _screen){
        this.screen = _screen;
        SpriteBatch batch = new SpriteBatch();

        new Styles();

        stage = new Stage(new ScreenViewport(), batch);

        root = new Stack();
        root.setFillParent(true);
        stage.addActor(root);

        playUI = new Table().debug();
        playUI.setFillParent(true);
        root.add(playUI);

        notificationUI = new Table();
        root.add(notificationUI);

        valueBars = new Table();
        playUI.add(valueBars).left().top().pad(10 * Settings.getUIScale(), 10 * Settings.getUIScale(), 0, 0).expand();
        playUI.row();

        hotBar = new Table();
        playUI.add(hotBar).bottom();

        healthBar = new ProgressBar(0, 100, 1, false, Styles.healthBar);
        healthBar.setAnimateDuration(0.1f);

        valueBars.add(healthBar).minWidth(200 * Settings.getUIScale());

        TextButton c = new TextButton("Pause", Styles.defaultButton);
        c.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(screen.isPaused()) {
                    screen.resume();
                } else {
                    screen.pause();
                }
            }
        });
        hotBar.add(c).width(160 * Settings.getUIScale()).height(40 * Settings.getUIScale());

        SelectBox c2 = new SelectBox(Styles.dropdownMenu);
        c2.setItems(new String[] {"Dropdown Item 1", "Dropdown Item 2", "Dropdown Item 3", "Dropdown Item 4", "Dropdown Item 5", "Dropdown Item 6", "Dropdown Item 7", "Dropdown Item 8", "Dropdown Item 9", "Dropdown Item 10", "Dropdown Item 11", "Dropdown Item 12","Dropdown Item 13", "Dropdown Item 14", "Dropdown Item 15", "Dropdown Item 16", "Dropdown Item 17", "Dropdown Item 18", "Dropdown Item 19", "Dropdown Item 20",  "Dropdown Item 21", "Dropdown Item 22", "Dropdown Item 23", "Dropdown Item 24"});
        valueBars.add(c2);

        Label c3 = new Label("This is a label.\nIt has multiple lines\nwhich is very cool.", Styles.label);
        valueBars.add(c3);

        Dialog d = new Dialog("Test", Styles.window).text(new Label("This is a small dialog, which asks you a question.\nWhat do you want to do?", Styles.label)).button(new TextButton("Ok", Styles.defaultButton)).button(new TextButton("Cancel", Styles.defaultButton)).button(new TextButton("Do something else", Styles.defaultButton));
        root.add(d);

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
