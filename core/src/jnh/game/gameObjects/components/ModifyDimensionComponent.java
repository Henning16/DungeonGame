package jnh.game.gameObjects.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

public class ModifyDimensionComponent extends Component{

    private Vector2 maxScale;
    private float duration = 0;
    private float remainingTime = 0;

    @Override
    public void tick(float delta) {
        super.tick(delta);
        if(remainingTime != 0 && maxScale != null) {
            gameObject.setScaleX(lerp(1, maxScale.x, remainingTime / duration));
            gameObject.setScaleY(lerp(1, maxScale.y, remainingTime / duration));
            gameObject.setOrigin(Align.center);
        }
        remainingTime = Math.max(0, remainingTime - delta);
    }

    @Override
    public Component copy() {
        ModifyDimensionComponent c = new ModifyDimensionComponent();
        c.maxScale = maxScale;
        return c;
    }

    public void deform(Vector2 scaleTo, float duration) {
        maxScale = scaleTo;
        this.duration = duration;
        remainingTime = duration;
    }

    private float lerp(float a, float b, float f) {
        return a + f * (b - a);
    }
}
