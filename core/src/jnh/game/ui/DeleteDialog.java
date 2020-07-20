package jnh.game.ui;


import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import jnh.game.assets.Assets;

public class DeleteDialog extends Dialog {

    public DeleteDialog(String title, String message) {
        super(title, Assets.uiStyles.window);
        add(new Label(message, Assets.uiStyles.label));

    }
}