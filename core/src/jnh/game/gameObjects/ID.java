package jnh.game.gameObjects;

public class ID {

    private int sceneID;
    private long globalID;

    public static final ID NULL = new ID(-1, -1);

    public ID() {

    }

    public ID(int sceneID, long globalID) {
        this.sceneID = sceneID;
        this.globalID = globalID;
    }

    public int getSceneID() {
        return sceneID;
    }

    public long getGlobalID() {
        return globalID;
    }

    public void setSceneID(int sceneID) {
        this.sceneID = sceneID;
    }

    @Override
    public boolean equals(Object object) {
        try {
            ID id = (ID) object;
            return id.getGlobalID() == getGlobalID();
        } catch(ClassCastException e) {
            return false;
        }

    }


}
