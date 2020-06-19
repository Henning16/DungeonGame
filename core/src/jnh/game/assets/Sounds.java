package jnh.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {

    public final Sound COLLECT_ITEM;

    public Sounds() {
        COLLECT_ITEM = Gdx.audio.newSound(Gdx.files.internal("audio/plop.wav"));
    }
}
