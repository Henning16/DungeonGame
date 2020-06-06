package jnh.game.gameObjects.lightObjects;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import jnh.game.stages.GameStage;

public class LightObject extends Actor {

    private RayHandler rayHandler;
    private Color color = new Color(1f, 1f, 1f, 0.8f);
    private float distance = 4f;
    private float flickeringIntensity = 0f;
    private boolean castShadows = true;

    private PointLight light;

    public LightObject(GameStage stage) {
        this.rayHandler = stage.getRayHandler();
        light = new PointLight(rayHandler, 1000, color, distance, 0f, 0f);
        light.setSoftnessLength(2);
        stage.getLightUpdater().register(this);
    }

    public LightObject(GameStage stage, Color color, Vector2 position, float distance, float flickeringIntensity, boolean castShadows) {
        this.rayHandler = stage.getRayHandler();
        this.color = color;
        this.flickeringIntensity = flickeringIntensity;
        this.castShadows = castShadows;
        light = new PointLight(rayHandler, 2000, color, distance, position.x, position.y);
        light.setSoftnessLength(2);
        setPosition(position);
        setCastShadows(castShadows);
        stage.getLightUpdater().register(this);
    }

    public void update(float delta) {
        if(flickeringIntensity > 0) {
            Color modifiedColor = new Color(color).set(color.r, color.g, color.b, color.a + ((float) Math.random() * 2 - 1) * 0.01f * flickeringIntensity);
            light.setColor(modifiedColor);
        }
    }

    @Override
    public boolean remove() {
        boolean r = super.remove();
        light.remove();
        return r;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    public Vector2 getPosition() {
        return light.getPosition();
    }

    public void setPosition(Vector2 position) {
        light.setPosition(position.x, position.y);
    }

    public float getFlickeringIntensity() {
        return flickeringIntensity;
    }

    public void setFlickeringIntensity(float flickeringIntensity) {
        this.flickeringIntensity = flickeringIntensity;
    }

    public boolean isCastShadows() {
        return castShadows;
    }

    public void setCastShadows(boolean castShadows) {
        this.castShadows = castShadows;
        if(castShadows) {
            light.setContactFilter((short) 0x0001, (short) -1, (short) 1);
        } else {
            light.setContactFilter((short) 0x0001, (short) -1, (short) 0);
        }
    }

}
