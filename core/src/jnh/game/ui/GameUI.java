package jnh.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;
import jnh.game.components.BodyComponent;
import jnh.game.components.HealthComponent;
import jnh.game.screens.GameScreen;
import jnh.game.screens.StartScreen;
import jnh.game.settings.Settings;
import jnh.game.ui.notifications.Notification;
import jnh.game.ui.notifications.NotificationHandler;
import jnh.game.ui.notifications.NotificationTable;

import java.io.FileNotFoundException;

public class GameUI implements Disposable {

    private final GameScreen screen;

    private final Stage stage;

    /**
     * This table contains ui for the game itself like health bars etc. but no inventory and such.
     */
    private final Table playUI;
    /**
     * This table can be used for displaying dialogs.
     */
    private final Table dialogUI;
    /**
     * This table is used to display the pause menu.
     */
    private final Table pauseUI;
    /**
     * This table is currently only used to display the fps.
     */
    private final Table overlayUI;

    private final Label fpsLabel;
    private final Table valueBars;
    private final Table hotBar;

    private final ProgressBar healthBar;

    public GameUI(GameScreen _screen){
        this.screen = _screen;
        SpriteBatch batch = new SpriteBatch();
        new UIStyles();
        stage = new Stage(new ScreenViewport(), batch);


        //Play
        playUI = new Table();
        playUI.setFillParent(true);
        stage.addActor(playUI);

        valueBars = new Table();
        playUI.add(valueBars).left().top().pad(10 * Settings.getUIScale(), 10 * Settings.getUIScale(), 0, 0).expand();
        playUI.row();

        healthBar = new ProgressBar(0, 100, 1, false, Assets.uiStyles.healthBar);
        healthBar.setAnimateDuration(0.1f);
        valueBars.add(healthBar).minWidth(200 * Settings.getUIScale());

        hotBar = new Table();


        //Notifications
        NotificationTable notificationUI = new NotificationTable();
        notificationUI.setFillParent(true);
        stage.addActor(notificationUI);
        NotificationHandler.setTable(notificationUI);


        //Pause
        pauseUI = new Table();
        pauseUI.setFillParent(true);
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixmap.setColor(Color.BLACK);
        pixmap.fillRectangle(0, 0, 1, 1);
        pauseUI.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pixmap))));
        pixmap.dispose();

        final TextButton titleScreenButton = new TextButton("Title Screen", Assets.uiStyles.defaultButton);
        titleScreenButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.getGame().setScreen(new StartScreen(screen.getGame()));
            }
        });
        pauseUI.add(titleScreenButton).padBottom(20 * Settings.getUIScale());
        pauseUI.row();
        final TextButton resumeButton = new TextButton("Resume", Assets.uiStyles.defaultButton);
        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                resumeGame();
            }
        });
        pauseUI.add(resumeButton);

        //Dialog
        dialogUI = new Table();
        dialogUI.setFillParent(true);


        //Overlay
        overlayUI = new Table();
        overlayUI.top().right();
        overlayUI.setFillParent(true);
        stage.addActor(overlayUI);

        fpsLabel = new Label("0", Assets.uiStyles.label);
        overlayUI.add(fpsLabel);
    }

    public void act(float delta) {
        stage.act(delta);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if(pauseUI.hasParent()) {
                resumeGame();
            } else {
                showPauseScreen();
            }
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }

    public void updateHealthBar(int min, int max, int value) {
        healthBar.setRange(min, max);
        healthBar.setValue(value);
    }

    public void updateFPS(int fps) {
        fpsLabel.setText(fps);
    }

    public void showDeathScreen() {
        final Table deathOverlay = new Table();
        deathOverlay.setFillParent(true);
        stage.addActor(deathOverlay);
        deathOverlay.setColor(new Color(1, 1, 1, 0));
        deathOverlay.addAction(Actions.fadeIn(1));

        Label deathTitle = new Label("You died", Assets.uiStyles.title);
        deathOverlay.add(deathTitle).padBottom(12 * Settings.getUIScale());
        deathOverlay.row();

        TextButton respawnButton = new TextButton("Respawn", Assets.uiStyles.defaultButton);
        respawnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                deathOverlay.remove();
                screen.resume();

                try {
                    screen.getStage().getWorld().switchScene(screen.getStage().getWorld().getRespawnSceneID());
                } catch (FileNotFoundException e) {
                    NotificationHandler.addNotification(new Notification("Error", "World is corrupted. The scene id could not be found."));
                }

                GameObject player = screen.getStage().getGameObjectManager().getGameObject(screen.getStage().getGameObjectManager().playerID);
                player.setScale(0);
                player.addAction(new SequenceAction(Actions.scaleTo(1.3f, 1.3f, 0.14f, Interpolation.pow2Out), Actions.scaleTo(0.9f, 0.9f, 0.2f, Interpolation.pow2In), Actions.scaleTo(1, 1, 0.4f, Interpolation.pow2Out)));
                player.getComponent(HealthComponent.class).setHealth(player.getComponent(HealthComponent.class).getMaxHealth());
                player.getComponent(BodyComponent.class).getBody().setTransform(screen.getStage().getWorld().getRespawnPosition(), 0);
            }
        });
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixmap.setColor(Color.BLACK);
        pixmap.fillRectangle(0, 0, 1, 1);
        deathOverlay.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pixmap))));
        pixmap.dispose();
        deathOverlay.add(respawnButton);
    }

    private void showPauseScreen() {
        stage.addActor(pauseUI);
        screen.pause();
        pauseUI.setColor(1, 1, 1, 0);
        pauseUI.addAction(Actions.fadeIn(0.4f));
    }

    private void resumeGame() {
        pauseUI.remove();
        screen.resume();
    }
}
