package jnh.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class UITextures {

    private final Texture BUTTON_SHEET;

    public final TextureRegion BUTTON_UP;

    public UITextures() {
        BUTTON_SHEET = new Texture(Gdx.files.internal("textures/buttons.png"));
        BUTTON_UP = new TextureRegion(BUTTON_SHEET, 0, 0, 16, 16);
    }
}
