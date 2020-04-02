package jnh.game.lights;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class GamePointLight extends PointLight {

    private boolean castShadows;

    public GamePointLight(RayHandler rayHandler, Color color, boolean castShadows, int distance, Vector2 position) {
        super(rayHandler, 1000, color, distance, position.x, position.y);
        setCastShadows(castShadows);
    }

    public void setCastShadows(boolean castShadows) {
        this.castShadows = castShadows;
        if(castShadows) {
            setContactFilter((short) 0x0001, (short) -1, (short) 0);
        } else {
            setContactFilter((short) 0x0001, (short) 1, (short) 0);
        }
    }

    public boolean doCastShadows() {
        return castShadows;
    }
}
