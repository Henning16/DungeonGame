package jnh.game.gfx.animations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator {

    private float elapsedTime;
    private Animation<TextureRegion> waitingAnimation;
    private Animation<TextureRegion> currentAnimation;

    public void play (Animation<TextureRegion> animation, boolean interrupt) {
        if (currentAnimation == animation) {
            return;
        }
        if (!interrupt
                && currentAnimation != null
                && (currentAnimation.getPlayMode () == Animation.PlayMode.NORMAL || currentAnimation.getPlayMode () == Animation.PlayMode.REVERSED)
                && !currentAnimation.isAnimationFinished (elapsedTime)) {
            waitingAnimation = animation;
            return;
        }
        currentAnimation = animation;
        elapsedTime = 0f;
    }

    public void reset () {
        elapsedTime = 0f;
    }

    public void tick (float delta) {
        if(currentAnimation != null) {
            elapsedTime += delta;
            if (currentAnimation.isAnimationFinished (elapsedTime) && waitingAnimation != null) {
                currentAnimation = waitingAnimation;
                elapsedTime = 0f;
            }
        }

    }

    public TextureRegion getTexture () {
        if(currentAnimation != null) {
            return currentAnimation.getKeyFrame (elapsedTime);
        } else  {
            return null;
        }
    }
}
