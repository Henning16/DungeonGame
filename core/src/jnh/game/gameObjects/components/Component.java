package jnh.game.gameObjects.components;

import jnh.game.gameObjects.GameObject;

public abstract class Component {

    protected GameObject gameObject;

    public Component(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public abstract void init(String[] parameters) throws IllegalArgumentException;

    public abstract void act(double delta);

}