package Question2;

import java.util.ArrayList;
import java.util.List;

public abstract class Insect {
    protected int x, y; // Position on the grid
    protected boolean hasMoved;

    public Insect(int x, int y) {
        this.x = x;
        this.y = y;
        this.hasMoved = false;
    }

    public abstract void move(Insect[][] grid);
    public abstract void breed(Insect[][] grid);
    public abstract void starve(Insect[][] grid);

    public void setMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean hasMoved() {
        return hasMoved;
    }
    protected List<int[]> getFreeAdjacentCells(Insect[][] grid, int x, int y) {
        List<int[]> freeCells = new ArrayList<>();
        for (int[] dir : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {  // Up, Down, Left, Right
            int nx = x + dir[0], ny = y + dir[1];
            if (nx >= 0 && nx < grid[0].length && ny >= 0 && ny < grid.length && grid[ny][nx] == null) {
                freeCells.add(new int[]{nx, ny});
            }
        }
        return freeCells;
    }

    protected List<int[]> getAdjacentAntCells(Insect[][] grid, int x, int y) {
        List<int[]> antCells = new ArrayList<>();
        for (int[] dir : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx >= 0 && nx < grid[0].length && ny >= 0 && ny < grid.length && grid[ny][nx] instanceof Ant) {
                antCells.add(new int[]{nx, ny});
            }
        }
        return antCells;
    }

}

