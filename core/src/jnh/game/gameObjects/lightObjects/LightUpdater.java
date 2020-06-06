package jnh.game.gameObjects.lightObjects;

import java.util.ArrayList;

public class LightUpdater {

    private ArrayList<LightObject> lightObjects = new ArrayList<>();

    public void update(float delta) {
        for(LightObject l : lightObjects) {
            l.update(delta);
        }
    }

    public void register(LightObject lightObject) {
        lightObjects.add(lightObject);
    }
}
