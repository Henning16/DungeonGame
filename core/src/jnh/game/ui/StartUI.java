package jnh.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import jnh.game.assets.Assets;
import jnh.game.screens.GameScreen;
import jnh.game.screens.StartScreen;

public class StartUI {

    private final Table startMenu;
    //private final Table newWorldMenu;

    public StartUI(final StartScreen screen) {
        startMenu = new Table();
        startMenu.setFillParent(true);

        TextButton playbutton = new TextButton("Play", Assets.uiStyles.defaultButton);
        playbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO Loading screen in between
                screen.getGame().setScreen(new GameScreen(screen.getGame()));
            }
        });
        startMenu.add(playbutton);
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
    }
}
