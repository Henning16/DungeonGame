package jnh.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {

    public final Sound COLLECT_ITEM;
    public final Sound ENEMY_HIT;
    public final Sound WEAPON_SWING;

    public Sounds() {
        COLLECT_ITEM = Gdx.audio.newSound(Gdx.files.internal("audio/plop.wav"));
        ENEMY_HIT = Gdx.audio.newSound(Gdx.files.internal("audio/punch.wav"));
        WEAPON_SWING = Gdx.audio.newSound(Gdx.files.internal("audio/swing.mp3"));
    }
}
