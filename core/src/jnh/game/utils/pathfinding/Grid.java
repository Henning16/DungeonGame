package jnh.game.utils.pathfinding;

import com.badlogic.gdx.math.Vector2;

public class Grid {

    public Vector2 gridWorldSize;
    public float nodeRadius;
    private Node[][] grid;

    private float nodeDiameter;
    private int gridSizeX, gridSizeY;

    public void calculate() {
        nodeDiameter = nodeRadius * 2;
        gridSizeX = Math.round(gridWorldSize.x / nodeDiameter);
        gridSizeY = Math.round(gridWorldSize.y / nodeDiameter);
        createGrid();
    }

    private void createGrid() {
        grid = new Node[gridSizeX][gridSizeY];
        Vector2 worldBottomLeft = new Vector2(0,0);

        for(int x = 0; x < gridSizeX; x++) {
            for(int y = 0; x < gridSizeY; y++) {
                Vector2 worldPoint = worldBottomLeft.add(new Vector2(x * nodeDiameter + nodeRadius, 0)).add(new Vector2(0, y * nodeDiameter + nodeRadius));
                boolean walkable = false;
            }
        }

    }
}
