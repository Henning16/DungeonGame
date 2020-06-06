package jnh.game.gameObjects.behaviors;

import jnh.game.gameObjects.GameObject;

public abstract class Behavior {

    protected GameObject gameObject;

    public Behavior(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public abstract void act(double delta);

}
