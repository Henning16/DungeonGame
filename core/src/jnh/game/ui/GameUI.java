package jnh.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import jnh.game.ui.notifications.Notification;
import jnh.game.ui.notifications.NotificationHandler;
import jnh.game.ui.notifications.NotificationTable;

public class GameUI implements Disposable {

    private final GameScreen screen;

    private final Stage stage;


    private final Table playUI;
    private final Table dialogUI;
    private final Table overlayUI;

    private final Label fpsLabel;
    private final Table valueBars;
    private final Table hotBar;

    private final ProgressBar healthBar;

    public GameUI(GameScreen _screen){
        this.screen = _screen;
        SpriteBatch batch = new SpriteBatch();

        new Styles();

        stage = new Stage(new ScreenViewport(), batch);



        playUI = new Table();
        playUI.setFillParent(true);
        stage.addActor(playUI);

        dialogUI = new Table();
        dialogUI.setFillParent(true);
        stage.addActor(dialogUI);

        NotificationTable notificationUI = new NotificationTable();
        notificationUI.setFillParent(true);
        stage.addActor(notificationUI);
        NotificationHandler.setTable(notificationUI);
        NotificationHandler.addNotification(new Notification("Notification 1", "A message"));
        NotificationHandler.addNotification(new Notification("Notification 2", "A message"));

        overlayUI = new Table();
        overlayUI.top().right();
        overlayUI.setFillParent(true);
        stage.addActor(overlayUI);

        fpsLabel = new Label("0", Styles.label);
        overlayUI.add(fpsLabel);

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

    public void updateFPS(int fps) {
        fpsLabel.setText(fps);
    }

}
