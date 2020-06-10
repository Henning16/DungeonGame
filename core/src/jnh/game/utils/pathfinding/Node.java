package jnh.game.utils.pathfinding;

import com.badlogic.gdx.math.Vector2;

public class Node {

    public boolean walkable;
    public Vector2 worldPosition;

    public Node(boolean walkable, Vector2 worldPosition) {
        this.walkable = walkable;
        this.worldPosition = worldPosition;
    }
}
