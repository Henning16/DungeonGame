package jnh.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class UITextures {

    private final Texture BAR_SHEET;
    private final Texture BUTTON_SHEET;
    private final Texture CHECKBOX_SHEET;
    private final Texture LIST_SHEET;
    private final Texture SLIDER_SHEET;
    private final Texture TEXT_FIELD_SHEET;
    private final Texture WINDOW_SHEET;

    public final TextureRegion BUTTON_UP, BUTTON_OVER, BUTTON_DOWN, BUTTON_DISABLED;
    public final TextureRegion CHECKBOX_OFF, CHECKBOX_OVER, CHECKBOX_OFF_DISABLED, CHECKBOX_ON, CHECKBOX_ON_OVER, CHECKBOX_ON_DISABLED;
    public final TextureRegion HEALTH_BAR_BACKGROUND, HEALTH_BAR_KNOB;
    public final TextureRegion LIST_BACKGROUND, LIST_SELECTION;
    public final TextureRegion SCROLL_BACKGROUND, SCROLL_KNOB;
    public final TextureRegion SLIDER_BACKGROUND, SLIDER_KNOB, SLIDER_KNOB_OVER;
    public final TextureRegion TEXT_FIELD_BACKGROUND, TEXT_FIELD_BACKGROUND_DISABLED, TEXT_FIELD_BACKGROUND_FOCUSED, TEXT_FIELD_CURSOR, TEXT_FIELD_SELECTION;
    public final TextureRegion WINDOW_BACKGROUND, WINDOW_STAGE_BACKGROUND;

    public UITextures() {

        BAR_SHEET = new Texture(Gdx.files.internal("textures/bars.png"));
        BUTTON_SHEET = new Texture(Gdx.files.internal("textures/buttons.png"));
        CHECKBOX_SHEET = new Texture(Gdx.files.internal("textures/checkboxes.png"));
        LIST_SHEET = new Texture(Gdx.files.internal("textures/lists.png"));
        SLIDER_SHEET = new Texture(Gdx.files.internal("textures/sliders.png"));
        TEXT_FIELD_SHEET = new Texture(Gdx.files.internal("textures/textfields.png"));
        WINDOW_SHEET = new Texture(Gdx.files.internal("textures/windows.png"));

        BUTTON_UP = new TextureRegion(BUTTON_SHEET, 0, 0, 64, 16);
        BUTTON_OVER = new TextureRegion(BUTTON_SHEET, 0, 16, 64, 16);
        BUTTON_DOWN = new TextureRegion(BUTTON_SHEET, 0, 32, 64, 16);
        BUTTON_DISABLED = new TextureRegion(BUTTON_SHEET, 0, 48, 64, 16);

        CHECKBOX_OFF = new TextureRegion(CHECKBOX_SHEET, 0, 0, 13, 13);
        CHECKBOX_OVER = new TextureRegion(CHECKBOX_SHEET, 13, 0, 13, 13);
        CHECKBOX_OFF_DISABLED = new TextureRegion(CHECKBOX_SHEET, 26, 0, 13, 13);
        CHECKBOX_ON = new TextureRegion(CHECKBOX_SHEET, 0, 13, 13, 13);
        CHECKBOX_ON_OVER = new TextureRegion(CHECKBOX_SHEET, 13, 13, 13, 13);
        CHECKBOX_ON_DISABLED = new TextureRegion(CHECKBOX_SHEET, 26, 13, 13, 13);

        HEALTH_BAR_BACKGROUND = new TextureRegion(BAR_SHEET, 0, 0, 48, 10);
        HEALTH_BAR_KNOB = new TextureRegion(BAR_SHEET, 0, 10, 48, 10);

        LIST_BACKGROUND = new TextureRegion(LIST_SHEET, 0, 0, 64, 16);
        LIST_SELECTION = new TextureRegion(LIST_SHEET, 0, 16, 64, 16);

        SCROLL_BACKGROUND = BUTTON_DISABLED;
        SCROLL_KNOB = BUTTON_UP;
        SLIDER_BACKGROUND = new TextureRegion(SLIDER_SHEET, 0, 0, 48, 8);
        SLIDER_KNOB = new TextureRegion(SLIDER_SHEET, 0, 8, 10, 10);
        SLIDER_KNOB_OVER = new TextureRegion(SLIDER_SHEET, 10, 8, 10, 10);

        TEXT_FIELD_BACKGROUND = new TextureRegion(TEXT_FIELD_SHEET, 0, 0, 64, 16);
        TEXT_FIELD_BACKGROUND_DISABLED = new TextureRegion(TEXT_FIELD_SHEET, 0, 16, 64, 16);
        TEXT_FIELD_BACKGROUND_FOCUSED = new TextureRegion(TEXT_FIELD_SHEET, 0, 32, 64, 16);
        TEXT_FIELD_CURSOR = new TextureRegion(TEXT_FIELD_SHEET, 0, 48, 1, 12);
        TEXT_FIELD_SELECTION = new TextureRegion(TEXT_FIELD_SHEET, 1, 48, 1, 12);

        WINDOW_BACKGROUND = new TextureRegion(WINDOW_SHEET, 0, 0, 64, 32);
        WINDOW_STAGE_BACKGROUND = new TextureRegion(WINDOW_SHEET, 0, 32, 1, 1);

    }
}
