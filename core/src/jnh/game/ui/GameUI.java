package jnh.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.components.BodyComponent;
import jnh.game.gameObjects.components.HealthComponent;
import jnh.game.screens.GameScreen;
import jnh.game.settings.Settings;
import jnh.game.ui.notifications.Notification;
import jnh.game.ui.notifications.NotificationHandler;
import jnh.game.ui.notifications.NotificationTable;

import java.io.FileNotFoundException;

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

        Label deathTitle = new Label("You died", Styles.title);
        deathOverlay.add(deathTitle).padBottom(12 * Settings.getUIScale());
        deathOverlay.row();

        TextButton respawnButton = new TextButton("Respawn", Styles.defaultButton);
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
}
