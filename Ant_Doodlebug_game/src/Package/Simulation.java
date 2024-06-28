package Question2;

import java.util.Random;
import java.util.Scanner;

public class Simulation {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private final Insect[][] grid = new Insect[WIDTH][HEIGHT];
    private static final int INITIAL_ANTS = 100;
    private static final int INITIAL_DOODLEBUGS = 5;
    private Random rand = new Random();

    public Simulation() {
        initializeGrid();


    }
    private void initializeGrid() {
        Random rand = new Random();
        // Place initial doodlebugs
        for (int i = 0; i < INITIAL_DOODLEBUGS; i++) {
            int x, y;
            do {
                x = rand.nextInt(WIDTH);
                y = rand.nextInt(HEIGHT);
            } while (grid[y][x] != null);  // Ensure no two doodlebugs are placed in the same cell
            grid[y][x] = new Doodlebug(x, y);
        }

        // Place initial ants
        for (int i = 0; i < INITIAL_ANTS; i++) {
            int x, y;
            do {
                x = rand.nextInt(WIDTH);
                y = rand.nextInt(HEIGHT);
            } while (grid[y][x] != null);  // Ensure no two ants are placed in the same cell
            grid[y][x] = new Ant(x, y);
        }
    }

    private void placeCritterRandomly(Insect critter) {
        int x, y;
        do {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(HEIGHT);
        } while (grid[y][x] != null);
        grid[y][x] = critter;
        critter.x = x;
        critter.y = y;
    }

    public void run() {
        while (true) {
            while (true) {
                timeStep();  // Execute moves, breeding, and starving for all organisms
                displayGrid();  // Display the current state of the grid

                // Check win conditions
                if (Ant.antCount == 0) {
                    System.out.println("Doodlebugs win! All ants have been eaten.");
                    break;
                } else if (Doodlebug.doodlebugCount == 0) {
                    System.out.println("Ants win! All doodlebugs have starved.");
                    break;
                }

                // Add a delay between each simulation step
                try {
                    Thread.sleep(500);  // 500 milliseconds delay
                } catch (InterruptedException e) {
                    System.out.println("Simulation interrupted.");
                    break;
                }
            }

        }
    }
        private void timeStep() {
            // Clear moved flag for all organisms
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    if (grid[i][j] != null) {
                        grid[i][j].setMoved(false);
                    }
                }
            }

            // Move, breed, and starve each organism
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    if (grid[i][j] != null && !grid[i][j].hasMoved()) {
                        grid[i][j].setMoved(true);
                        grid[i][j].move(grid);
                    }
                }
            }

            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    if (grid[i][j] != null) {
                        grid[i][j].breed(grid);
                        grid[i][j].starve(grid);
                    }
                }
            }
        }

        private void displayGrid() {
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    if (grid[i][j] instanceof Ant) {
                        System.out.print("O ");
                    } else if (grid[i][j] instanceof Doodlebug) {
                        System.out.print("X ");
                    } else {
                        System.out.print("_ ");
                    }
                }
                System.out.println();
            }

            System.out.println("Ants: " + Ant.antCount + ", Doodlebugs: " + Doodlebug.doodlebugCount);
            System.out.println("***********************************************************************");
        }


    public static void main(String[] args) {
        Simulation sim = new Simulation();
        sim.run();

    }
}
