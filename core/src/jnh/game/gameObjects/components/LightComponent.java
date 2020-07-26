package jnh.game.gameObjects.components;

import box2dLight.Light;
import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;

/**
 * Attaches a light to the game object and manages it. More information can be found <a href="https://github.com/Henning16/DungeonGame/wiki/Component-Dokumentation#bodycomponent">here</a>.
 */
public class LightComponent extends Component {

    private transient Light light;

    private Color color;
    private float distance = 4f;
    private float flickeringIntensity = 0f;

    @Override
    public void tick(float delta) {
        super.tick(delta);
        light.setPosition(new Vector2(gameObject.getX() - gameObject.getWidth() / 2, gameObject.getY() - gameObject.getHeight() / 2));
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
