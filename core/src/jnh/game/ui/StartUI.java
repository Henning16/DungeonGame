package jnh.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import jnh.game.assets.Assets;
import jnh.game.screens.GameScreen;
import jnh.game.screens.StartScreen;
import jnh.game.settings.Settings;
import jnh.game.world.World;

import java.io.FileNotFoundException;
import java.util.Set;

public class StartUI {

    private final Table startMenu;
    private final Table worldsMenu;
    private final Table newWorldMenu;

    public StartUI(final StartScreen screen) {
        //Start menu
        startMenu = new Table();
        startMenu.setFillParent(true);

        startMenu.add(new Label("Dungeon Game", Assets.uiStyles.title)).padBottom(32 * Settings.getUIScale());
        startMenu.row();

        TextButton playbutton = new TextButton("Play", Assets.uiStyles.defaultButton);
        playbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO Loading screen in between
                GameScreen gameScreen = new GameScreen(screen.getGame());
                //TODO Save and use last world
                try {
                    World.loadWorld(gameScreen.getStage(), "test");
                } catch (FileNotFoundException e) {
                    gameScreen.dispose();
                    return;
                }
                screen.getGame().setScreen(gameScreen);
                screen.dispose();
            }
        });
        startMenu.add(playbutton).padBottom(8 * Settings.getUIScale());
        startMenu.row();
        TextButton worldsButton = new TextButton("Worlds", Assets.uiStyles.defaultButton);
        worldsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startMenu.remove();
                screen.getStage().addActor(worldsMenu);
            }
        });
        startMenu.add(worldsButton).padBottom(8 * Settings.getUIScale());
        startMenu.row();
        TextButton quitButton = new TextButton("Quit", Assets.uiStyles.defaultButton);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.dispose();
                System.exit(0);
            }
        });
        startMenu.add(quitButton);
        screen.getStage().addActor(startMenu);

        //Worlds menu
        worldsMenu = new Table();
        worldsMenu.setFillParent(true);
        worldsMenu.add(new Label("Worlds", Assets.uiStyles.header)).padBottom(20 * Settings.getUIScale()).left();
        worldsMenu.row();

        TextButton backButton = new TextButton("Back", Assets.uiStyles.defaultButton);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                worldsMenu.remove();
                screen.getStage().addActor(startMenu);
            }
        });
        worldsMenu.add(backButton).padRight(4 * Settings.getUIScale()).padBottom(20 * Settings.getUIScale()).right();
        TextButton newWorldButton = new TextButton("New World", Assets.uiStyles.defaultButton);
        newWorldButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                worldsMenu.remove();
                screen.getStage().addActor(newWorldMenu);
            }
        });
        worldsMenu.add(newWorldButton).padBottom(20 * Settings.getUIScale()).right();
        worldsMenu.row();
        try {
            for(String worldName : World.getWorlds()) {
                worldsMenu.add(new Label(worldName, Assets.uiStyles.label)).padBottom(10 * Settings.getUIScale());
                worldsMenu.row();
            }
        } catch (FileNotFoundException e) {
            worldsMenu.add(new Label("Save folder not found", Assets.uiStyles.label));

        }

        //New World Menu
        newWorldMenu = new Table();
        newWorldMenu.setFillParent(true);
        newWorldMenu.add(new Label("New World", Assets.uiStyles.header)).padBottom(20 * Settings.getUIScale()).left();
        newWorldMenu.row();
        TextButton backButton2 = new TextButton("Back", Assets.uiStyles.defaultButton);
        backButton2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                newWorldMenu.remove();
                screen.getStage().addActor(worldsMenu);
            }
        });
        newWorldMenu.add(backButton2).padBottom(20 * Settings.getUIScale()).left();
        newWorldMenu.row();
        newWorldMenu.add(new Label("Name", Assets.uiStyles.label)).padBottom(5 * Settings.getUIScale()).left();
        newWorldMenu.row();
        final TextField nameField = new TextField("", Assets.uiStyles.textField);
        newWorldMenu.add(nameField).padBottom(20 * Settings.getUIScale()).minWidth(256 * Settings.getUIScale()).left();
        newWorldMenu.row();
        TextButton createButton = new TextButton("Create World", Assets.uiStyles.defaultButton);
        createButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameScreen gameScreen = new GameScreen(screen.getGame());
                World.newWorld(gameScreen.getStage(), nameField.getText());
                screen.getGame().setScreen(gameScreen);
                screen.dispose();
            }
        });
        newWorldMenu.add(createButton).left();
    }
}
