package jnh.game.world;

import jnh.game.stages.GameStage;

public class Scene {

    public static final int ROOM_WIDTH = 24;
    public static final int ROOM_HEIGHT = 24;

    private transient GameStage stage;

    public Scene(GameStage stage) {
        this.stage = stage;
        load();
    }

    protected void load() {

    }

    protected GameStage getStage() {
        return stage;
    }
}
