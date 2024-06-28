package Question2;

import java.util.List;
import java.util.Random;

public class Ant extends Insect {
    private static final int BREEDING_AGE = 3;
    private int stepsSinceLastBreed = 0;
    public static int antCount = 0;

    public Ant(int x, int y) {
        super(x, y);
        antCount++;
    }

    @Override
    public void move(Insect[][] grid) {
        List<int[]> freeCells = getFreeAdjacentCells(grid, x, y);
        if (!freeCells.isEmpty()) {
            int[] move = freeCells.get(new Random().nextInt(freeCells.size()));
            grid[move[1]][move[0]] = grid[y][x];  // Move ant to new position
            grid[y][x] = null;                   // Set old position to null
            x = move[0];
            y = move[1];
        }
    }


    @Override
    public void breed(Insect[][] grid) {
        stepsSinceLastBreed++;
        if (stepsSinceLastBreed >= BREEDING_AGE) {
            List<int[]> freeCells = getFreeAdjacentCells(grid, x, y);
            if (!freeCells.isEmpty()) {
                int[] newLocation = freeCells.get(new Random().nextInt(freeCells.size()));
                grid[newLocation[1]][newLocation[0]] = new Ant(newLocation[0], newLocation[1]);
                System.out.println("New ant born at (" + newLocation[0] + ", " + newLocation[1] + ")");
                stepsSinceLastBreed = 0;  // Reset breeding counter after successful breeding
            }
        }
    }

    @Override
    public void starve(Insect[][] grid) {
        // Ants do not starve, so this method can be empty or not defined
    }



    public String toString() {
        return "Ant";
    }
}

