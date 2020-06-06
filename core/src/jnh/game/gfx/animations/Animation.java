package jnh.game.gfx.animations;

import com.badlogic.gdx.utils.Array;

public class Animation<T> extends com.badlogic.gdx.graphics.g2d.Animation<T> implements Animatable {

    public enum PlayMode {
        NORMAL,
        REVERSED,
        LOOP,
        LOOP_REVERSED,
        LOOP_PINGPONG,
        LOOP_RANDOM,
    }

    public Animation(float frameDuration, Array<? extends T> keyFrames) {
        super(frameDuration, keyFrames);
    }

    public Animation(float frameDuration, Array<? extends T> keyFrames, PlayMode playMode) {
        super(frameDuration, keyFrames, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.valueOf(playMode.toString()));
    }

    public Animation(float frameDuration, T... keyFrames) {
        super(frameDuration, keyFrames);
    }

    public Animation(float frameDuration, PlayMode playMode, T... keyFrames) {
        super(frameDuration, keyFrames);
        setPlayMode(com.badlogic.gdx.graphics.g2d.Animation.PlayMode.valueOf(playMode.toString()));
    }
}
