package jnh.game.gameObjects.construction;

import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.components.Component;

import java.util.ArrayList;

public final class Blueprint {

    public String type;
    public boolean persistent;
    public String layer = "main";
    public Vector2 position = new Vector2(0, 0);
    public Vector2 dimension = new Vector2(1, 1);
    public ArrayList<String> tags = new ArrayList<>();
    public ArrayList<Component> components;

    public Blueprint() {
        components = new ArrayList<>();
    }
}