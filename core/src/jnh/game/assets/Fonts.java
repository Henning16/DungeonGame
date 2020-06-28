package jnh.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import jnh.game.settings.Settings;

public class Fonts {

    public final BitmapFont EXEPP, EXEPPS;

    public Fonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/exepp.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (32 * Settings.getUIScale());
        EXEPP = generator.generateFont(parameter);
        parameter.size = (int) (16 * Settings.getUIScale());
        EXEPPS = generator.generateFont(parameter); // font size 32
        generator.dispose();
    }
}
