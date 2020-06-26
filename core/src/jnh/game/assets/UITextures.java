package jnh.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class UITextures {

    private final Texture BAR_SHEET;
    private final Texture BUTTON_SHEET;

    public final TextureRegion HEALTH_BAR_BACKGROUND, HEALTH_BAR_KNOB;
    public final TextureRegion BUTTON_UP, BUTTON_OVER, BUTTON_DOWN;

    public UITextures() {

        BAR_SHEET = new Texture(Gdx.files.internal("textures/bars.png"));
        BUTTON_SHEET = new Texture(Gdx.files.internal("textures/buttons.png"));


        HEALTH_BAR_BACKGROUND = new TextureRegion(BAR_SHEET, 0, 0, 48, 10);
        HEALTH_BAR_KNOB = new TextureRegion(BAR_SHEET, 0, 10, 48, 10);

        BUTTON_UP = new TextureRegion(BUTTON_SHEET, 0, 0, 64, 16);
        BUTTON_OVER = new TextureRegion(BUTTON_SHEET, 0, 16, 64, 16);
        BUTTON_DOWN = new TextureRegion(BUTTON_SHEET, 0, 32, 64, 16);
    }
}
