package jnh.game.gameObjects.components;

import box2dLight.Light;
import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Color;
import jnh.game.gameObjects.GameObject;

public class LightComponent extends Component {

    private transient Light light;

    private Color color;
    private float distance = 4f;
    private float flickeringIntensity = 0f;
    @Override
    public void tick(float delta) {
        super.tick(delta);
        light.setPosition(gameObject.getPosition());
        if(flickeringIntensity > 0) {
            Color modifiedColor = new Color(color).set(color.r, color.g, color.b, color.a + ((float) Math.random() * 2 - 1) * 0.01f * flickeringIntensity);
            light.setColor(modifiedColor);
        }
        light.setContactFilter((short) 0x0001, (short) -1, (short) 0);
    }

    @Override
    public void attachedTo(GameObject gameObject) {
        super.attachedTo(gameObject);
        light = new PointLight(gameObject.getStage().getRayHandler(), 100, color, distance, gameObject.getX(), gameObject.getY());
    }

    @Override
    public void remove() {
        super.remove();
        if(light != null) {
            light.remove();
        }
    }

    @Override
    public LightComponent copy() {
        LightComponent c = new LightComponent();
        c.color = color;
        c.distance = distance;
        c.flickeringIntensity = flickeringIntensity;
        return c;
    }
}
