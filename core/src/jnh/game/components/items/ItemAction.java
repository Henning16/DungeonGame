package jnh.game.components.items;

import jnh.game.gameObjects.GameObject;

public interface ItemAction {

    void use(GameObject user);

    void secondaryUse(GameObject user);
}
