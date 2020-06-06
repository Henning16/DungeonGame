package jnh.game.gfx.animations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jnh.game.assets.Assets;

public class Animator {

    private boolean active = false;
    private float elapsedTime;
    private Animation<TextureRegion> waitingAnimation;
    private Animation<TextureRegion> currentAnimation;

    public void play(TextureRegion textureRegion, boolean interrupt) {
        Animation<TextureRegion> animation = new Animation<>(1f, textureRegion);
        animation.setPlayMode(Animation.PlayMode.LOOP);
        play(animation, interrupt);
    }

    public void play(Animation<TextureRegion> animation, boolean interrupt) {
        if(currentAnimation == animation) {
            return;
        }
        if(!interrupt
                && currentAnimation != null
                && (currentAnimation.getPlayMode() == Animation.PlayMode.NORMAL || currentAnimation.getPlayMode() == Animation.PlayMode.REVERSED)
                && !currentAnimation.isAnimationFinished(elapsedTime)) {
            waitingAnimation = animation;
            System.out.println("interrupting");
            return;
        }
        currentAnimation = animation;
        elapsedTime = 0f;
    }

    public void reset() {
        elapsedTime = 0f;
    }

    public void tick(float delta) {
        if(currentAnimation != null) {
            elapsedTime += delta;
            if(currentAnimation.isAnimationFinished(elapsedTime) && waitingAnimation != null) {
                currentAnimation = waitingAnimation;
                elapsedTime = 0f;
            }
        }

    }

    public TextureRegion getTexture() {
        if(currentAnimation != null) {
            return currentAnimation.getKeyFrame(elapsedTime);
        } else {
            return (TextureRegion) Assets.textures.ERROR.getKeyFrame(1);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
