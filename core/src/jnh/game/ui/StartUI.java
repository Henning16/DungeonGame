package jnh.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import jnh.game.assets.Assets;
import jnh.game.screens.GameScreen;
import jnh.game.screens.StartScreen;
import jnh.game.settings.Settings;
import jnh.game.world.World;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StartUI {

    private StartScreen screen;

    private final Table overlay;
    private final Table startMenu;
    private Table worldsMenu;
    private final Table newWorldMenu;
    private Table settingsMenu;

    public StartUI(final StartScreen screen) {
        this.screen = screen;

        overlay = new Table();
        overlay.setFillParent(true);

        //Start menu
        startMenu = new Table();
        startMenu.setFillParent(true);

        startMenu.add(new Label("Dungeon Game", Assets.uiStyles.title)).padBottom(32 * Settings.getUIScale());
        startMenu.row();

        final TextButton playbutton = new TextButton("Play", Assets.uiStyles.defaultButton);
        playbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO Loading screen in between
                GameScreen gameScreen = new GameScreen(screen.getGame());
                try {
                    gameScreen.getStage().setWorld(World.loadWorld(gameScreen.getStage(), World.getLastWorld()));
                    screen.getGame().setScreen(gameScreen);
                } catch (FileNotFoundException e) {
                    playbutton.setText("Play (No recent worlds found)");
                }
            }
        });
        startMenu.add(playbutton).padBottom(8 * Settings.getUIScale());
        startMenu.row();
        TextButton worldsButton = new TextButton("Worlds", Assets.uiStyles.defaultButton);
        worldsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startMenu.remove();
                reloadWorlds();
            }
        });
        startMenu.add(worldsButton).padBottom(8 * Settings.getUIScale());
        startMenu.row();
        TextButton settingsButton = new TextButton("Settings", Assets.uiStyles.defaultButton);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                reloadSettings();
            }
        });
        startMenu.add(settingsButton).padBottom(8 * Settings.getUIScale());
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

        //New World Menu
        newWorldMenu = new Table();
        newWorldMenu.setFillParent(true);
        newWorldMenu.add(new Label("New World", Assets.uiStyles.header)).padBottom(20 * Settings.getUIScale()).left();
        newWorldMenu.row();
        TextButton backButton2 = new TextButton("< Back", Assets.uiStyles.defaultButton);
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
        final TextButton createButton = new TextButton("Create World", Assets.uiStyles.defaultButton);
        createButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameScreen gameScreen = new GameScreen(screen.getGame());
                try {
                    gameScreen.getStage().setWorld(World.newWorld(gameScreen.getStage(), nameField.getText()));
                    screen.getGame().setScreen(gameScreen);
                } catch (FileNotFoundException e) {
                    createButton.setText("Create World (Fatal Error)");
                }
            }
        });
        newWorldMenu.add(createButton).left();
    }

    private void reloadWorlds() {
        if(worldsMenu != null) {
            worldsMenu.remove();
        }
        //Worlds menu
        worldsMenu = new Table();
        worldsMenu.setFillParent(true);
        worldsMenu.add(new Label("Worlds", Assets.uiStyles.header)).padBottom(20 * Settings.getUIScale()).left();
        worldsMenu.row();

        TextButton backButton = new TextButton("< Back", Assets.uiStyles.defaultButton);
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
        final List<String> worldList = new List<>(Assets.uiStyles.list);
        try {
            final ArrayList<String> worldListItems = new ArrayList<>(World.getWorlds());
            if(worldListItems.size() != 0) {
                worldList.setItems(new Array<>(worldListItems.toArray()));
                worldsMenu.add(worldList).left().pad(0).padBottom(20 * Settings.getUIScale()).colspan(2).fillX();
            } else {
                worldsMenu.add(new Label("No worlds here :(", Assets.uiStyles.label)).left().pad(0).padBottom(20 * Settings.getUIScale()).colspan(2).fillX();
            }
        } catch (FileNotFoundException e) {
            worldsMenu.add(new Label("Save folder not found.", Assets.uiStyles.label));
        }
        worldsMenu.row();
        TextButton loadButton = new TextButton("Load", Assets.uiStyles.defaultButton);
        loadButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO Loading screen in between
                GameScreen gameScreen = new GameScreen(screen.getGame());
                try {
                    gameScreen.getStage().setWorld(World.loadWorld(gameScreen.getStage(), worldList.getSelected()));
                    screen.getGame().setScreen(gameScreen);
                } catch (NullPointerException | FileNotFoundException ignored) { }
            }
        });
        worldsMenu.add(loadButton).padRight(4 * Settings.getUIScale());
        final TextButton deleteButton = new TextButton("Delete", Assets.uiStyles.dangerousButton);
        deleteButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    World.deleteWorld(worldList.getSelected());
                    reloadWorlds();
                } catch (NullPointerException | FileNotFoundException ignored) { }
            }
        });
        worldsMenu.add(deleteButton);
        screen.getStage().addActor(worldsMenu);
    }

    private void reloadSettings() {
        startMenu.remove();
        if(settingsMenu != null) {
            settingsMenu.remove();
        }
        settingsMenu = new Table();
        settingsMenu.setFillParent(true);
        settingsMenu.add(new Label("Settings", Assets.uiStyles.header)).padBottom(20 * Settings.getUIScale()).left();
        settingsMenu.row();
        TextButton backButton = new TextButton("< Back", Assets.uiStyles.defaultButton);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                settingsMenu.remove();
                screen.getStage().addActor(startMenu);
            }
        });
        settingsMenu.add(backButton).padBottom(20 * Settings.getUIScale()).left();
        settingsMenu.row();
        settingsMenu.add(new Label("UI Scale", Assets.uiStyles.label)).left().padBottom(10 * Settings.getUIScale());
        settingsMenu.row();
        final Slider uiScaleSlider = new Slider(0.5f, 3, 0.5f, false, Assets.uiStyles.slider);
        uiScaleSlider.setValue(Settings.getUIScale());
        uiScaleSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.setUIScale(uiScaleSlider.getValue());
                Settings.save();
                Assets.reloadFonts();
                Assets.reloadUIStyles();
                StartScreen newStartScreen = new StartScreen(screen.getGame());
                screen.getGame().setScreen(newStartScreen);
                newStartScreen.getStartUI().reloadSettings();
            }
        });
        settingsMenu.add(uiScaleSlider).left().minWidth(192 * Settings.getUIScale());
        screen.getStage().addActor(settingsMenu);
    }
}
