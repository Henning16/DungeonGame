package jnh.game.gameObjects;

import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.components.Component;

import java.util.ArrayList;
import java.util.List;

public class GameObjectJson {

    public String type;
    public ID id;
    public boolean removed;
    public boolean persistent;
    public String layer;
    public Vector2 position;
    public Vector2 dimension;
    public List<String> tags;
    public ArrayList<Component> components;

    public GameObjectJson() {

    }

    public GameObjectJson(GameObject gameObject) {
        this.type = gameObject.getType();
        this.id = gameObject.getID();
        this.removed = gameObject.isRemoved();
        this.persistent = gameObject.isPersistent();
        this.layer = gameObject.getLayerAsString();
        this.position = new Vector2(gameObject.getX(), gameObject.getY());
        this.dimension = new Vector2(gameObject.getWidth(), gameObject.getHeight());
        this.tags = gameObject.getTags();
        this.components = gameObject.getComponents();
    }
}
