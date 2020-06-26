package jnh.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Fonts {

    public final BitmapFont PIXEL_PLAY;

    public Fonts() {
        PIXEL_PLAY = new BitmapFont(Gdx.files.internal("fonts/returnofganon.fnt"));
    }
}
