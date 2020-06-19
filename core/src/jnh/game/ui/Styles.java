package jnh.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import jnh.game.assets.Assets;

public class Styles {

    public static TextButton.TextButtonStyle defaultButton;

    public Styles() {
        defaultButton = new TextButton.TextButtonStyle();
        defaultButton.up = new TextureRegionDrawable(Assets.uiTextures.BUTTON_UP);
        defaultButton.font = Assets.fonts.PIXEL_PLAY;
        defaultButton.fontColor = Color.BLUE;
    }
}