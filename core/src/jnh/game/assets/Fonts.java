package jnh.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import jnh.game.settings.Settings;

public class Fonts {

    public final BitmapFont EXEPP, EXEPPL, EXEPPS, EXEPPXL;

    public Fonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/exepp.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (32 * Settings.getUIScale());
        EXEPP = generator.generateFont(parameter);
        parameter.size = (int) (48 * Settings.getUIScale());
        EXEPPL = generator.generateFont(parameter);
        parameter.size = (int) (16 * Settings.getUIScale());
        EXEPPS = generator.generateFont(parameter);
        parameter.size = (int) (72 * Settings.getUIScale());
        EXEPPXL = generator.generateFont(parameter);
        generator.dispose();
    }
}
