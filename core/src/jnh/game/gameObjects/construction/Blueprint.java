package jnh.game.gameObjects.construction;

import jnh.game.gameObjects.components.Component;

import java.util.ArrayList;

public final class Blueprint {

    public String type;
    public String layer = "MAIN";
    public ArrayList<Component> components;

    public Blueprint() {
        components = new ArrayList<>();
    }
}
