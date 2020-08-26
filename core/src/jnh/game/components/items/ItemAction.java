package jnh.game.components.items;

import jnh.game.gameObjects.GameObject;

public interface ItemAction {

    public void use(GameObject user);

    public void secondaryUse(GameObject user);
}
