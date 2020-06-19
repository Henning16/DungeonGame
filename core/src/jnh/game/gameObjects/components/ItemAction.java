package jnh.game.gameObjects.components;

import jnh.game.gameObjects.GameObject;

public interface ItemAction {

    public void use(GameObject user);

    public void secondaryUse(GameObject user);
}
