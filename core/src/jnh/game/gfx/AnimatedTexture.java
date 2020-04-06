package jnh.game.gfx;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jnh.game.Global;

public class AnimatedTexture {

    private TextureRegion[] textures;
    private float animationSpeed;
    private Animation animation;

    public AnimatedTexture (TextureRegion[] textures, float animationSpeed, Animation.PlayMode playMode) {
        this.textures = textures;
        this.animationSpeed = animationSpeed;
        animation = new Animation (animationSpeed, textures, playMode);
    }

    public float getAnimationSpeed () {
        return animationSpeed;
    }

    public void setAnimationSpeed (float animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public TextureRegion get () {
        return (TextureRegion) animation.getKeyFrame (Global.elapsedTime);
    }

    public TextureRegion[] getTextures () {
        return textures;
    }

    public void setTextures (TextureRegion[] textures) {
        this.textures = textures;
    }
}
