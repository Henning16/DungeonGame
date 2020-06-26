package jnh.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import jnh.game.assets.Assets;

public class Styles {

    public static TextButton.TextButtonStyle defaultButton;
    public static ProgressBar.ProgressBarStyle healthBar;

    public Styles() {

        //DEFAULT BUTTON STYLE
        defaultButton = new TextButton.TextButtonStyle();

        NinePatch p = new NinePatch(Assets.uiTextures.BUTTON_OVER, 5, 5, 5, 5);
        p.scale(4, 4);
        defaultButton.up =  new NinePatchDrawable(p);

        NinePatch p2 = new NinePatch(Assets.uiTextures.BUTTON_DOWN, 5, 5, 5, 4);
        p2.scale(4, 4);
        defaultButton.down =  new NinePatchDrawable(p2);

        defaultButton.fontColor = new Color(1, 0.9f, 0.8f, 1);
        defaultButton.font = Assets.fonts.PIXEL_PLAY;


        //HEALTH BAR STYLE
        healthBar = new ProgressBar.ProgressBarStyle();

        NinePatch healthBarPatch = new NinePatch(Assets.uiTextures.HEALTH_BAR_BACKGROUND, 3, 3, 2, 2);
        healthBarPatch.scale(4, 4);
        healthBarPatch.setPadding(0, 0, 0, 0);
        healthBar.background = new NinePatchDrawable(healthBarPatch);
        healthBar.background.setMinHeight(40);

        NinePatch healthBarPatch2 = new NinePatch(Assets.uiTextures.HEALTH_BAR_KNOB, 3, 3, 2, 2);
        healthBarPatch2.scale(4, 4);
        healthBar.knobBefore = new NinePatchDrawable(healthBarPatch2);
        healthBar.knobBefore.setMinHeight(40);
        healthBar.knobBefore.setMinWidth(64);
    }
}