package Question2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Doodlebug extends Insect {
    private static final int BREEDING_AGE = 8;
    private static final int STARVATION_TIME = 3;
    private int stepsSinceLastBreed = 0;
    private int stepsSinceLastEat = 0;
    public static int doodlebugCount = 0;

    public Doodlebug(int x, int y) {
        super(x, y);
        doodlebugCount++;
    }

    @Override
    public void move(Insect[][] grid) {

        List<int[]> antCells = getAdjacentAntCells(grid, x, y);
        if (!antCells.isEmpty()) {
            int[] move = antCells.get(new Random().nextInt(antCells.size()));
            // Eat the ant
            System.out.println("Doodlebug at (" + x + ", " + y + ") eats an ant at (" + move[0] + ", " + move[1] + ")");
            grid[y][x] = null;  // Remove the doodlebug from the old position
            grid[move[1]][move[0]] = this;  // Place the doodlebug in the new position
            x = move[0];  // Update the doodlebug's x-coordinate
            y = move[1];  // Update the doodlebug's y-coordinate
            Ant.antCount--;  // Decrement the ant count
            stepsSinceLastEat = 0;  // Reset the starvation counter
        } else {
            // Move randomly like an ant if no adjacent ant is found
            List<int[]> freeCells = getFreeAdjacentCells(grid, x, y);
            if (!freeCells.isEmpty()) {
                int[] move = freeCells.get(new Random().nextInt(freeCells.size()));
                grid[y][x] = null;  // Remove the doodlebug from the old position
                grid[move[1]][move[0]] = this;  // Move doodlebug to new position
                x = move[0];
                y = move[1];
                stepsSinceLastEat++;  // Increment the starvation counter
            }
        }
        hasMoved = true;
    }


    @Override
    public void breed(Insect[][] grid) {
        stepsSinceLastBreed++;
        if (stepsSinceLastBreed >= BREEDING_AGE) {
            List<int[]> freeCells = getFreeAdjacentCells(grid, x, y);
            if (!freeCells.isEmpty()) {
                int[] newLocation = freeCells.get(new Random().nextInt(freeCells.size()));
                grid[newLocation[1]][newLocation[0]] = new Doodlebug(newLocation[0], newLocation[1]);
                System.out.println("New Doodlebug born at (" + newLocation[0] + ", " + newLocation[1] + ")");
                stepsSinceLastBreed = 0;  // Reset breeding counter after successful breeding
            }
        }
    }

    @Override
    public void starve(Insect[][] grid) {
        if (stepsSinceLastEat >= STARVATION_TIME) {
            System.out.println("Doodlebug starved at (" + x + ", " + y + ")");
            grid[y][x] = null;
            doodlebugCount--;// Remove the doodlebug from the grid
        }
    }

    public String toString() {
        return "Doodlebug";
    }


}


