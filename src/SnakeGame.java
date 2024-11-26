import  javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame {
    private static boolean isRunning = true;
    private static List<int[]> snakeBody = new ArrayList<>();
    private static String currentDirection = "RIGHT";
    private static int rows = 7, cols = 10; // Grid size (7x10)
    private static int[] food = new int[2];
    private static int score = 0; // Variable to track the score

    public static void main(String[] args) {
        // Initialize snake and food
        snakeBody.add(new int[]{rows / 2, cols / 2});
        placeFood();

        // Create a frame for capturing key presses
        JFrame frame = new JFrame("Snake Game");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add key listener for real-time input
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        if (!currentDirection.equals("DOWN")) {
                            currentDirection = "UP";
                        }
                        break;
                    case KeyEvent.VK_S:
                        if (!currentDirection.equals("UP")) {
                            currentDirection = "DOWN";
                        }
                        break;
                    case KeyEvent.VK_A:
                        if (!currentDirection.equals("RIGHT")) {
                            currentDirection = "LEFT";
                        }
                        break;
                    case KeyEvent.VK_D:
                        if (!currentDirection.equals("LEFT")) {
                            currentDirection = "RIGHT";
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        frame.setVisible(true);

        // Main game loop
        while (isRunning) {
            moveSnake();
            renderGrid();
            checkCollision();

            try {
                Thread.sleep(200); // Control game speed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void renderGrid() {
        // Clear the console (ANSI escape code)
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Create and initialize grid
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = ' ';
            }
        }

        // Place the snake
        for (int[] part : snakeBody) {
            grid[part[0]][part[1]] = 'O';
        }

        // Place the food
        grid[food[0]][food[1]] = '*';

        // Render the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("[" + grid[i][j] + "]");
            }
            System.out.println();
        }

        // Display score on the screen
        System.out.println("Score: " + score);
    }

    private static void placeFood() {
        Random rand = new Random();
        while (true) {
            int foodRow = rand.nextInt(rows);
            int foodCol = rand.nextInt(cols);
            boolean onSnake = false;

            // Check if food is on the snake's body
            for (int[] part : snakeBody) {
                if (part[0] == foodRow && part[1] == foodCol) {
                    onSnake = true;
                    break;
                }
            }

            if (!onSnake) {
                food[0] = foodRow;
                food[1] = foodCol;
                break;
            }
        }
    }

    private static void moveSnake() {
        int[] head = snakeBody.get(0);
        int newRow = head[0];
        int newCol = head[1];

        // Determine new head position
        switch (currentDirection) {
            case "UP": newRow--; break;
            case "DOWN": newRow++; break;
            case "LEFT": newCol--; break;
            case "RIGHT": newCol++; break;
        }

        // Check if new position is within the bounds of the grid
        if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
            isRunning = false;
            endGame(); // Ends game if snake goes out of bounds
            return;
        }

        // Add the new head
        snakeBody.add(0, new int[]{newRow, newCol});

        // Check if food is eaten
        if (newRow == food[0] && newCol == food[1]) {
            score++; // Increase score when food is eaten
            placeFood(); // Reposition food
        } else {
            // Remove the tail if no food was eaten
            snakeBody.remove(snakeBody.size() - 1);
        }
    }

    private static void checkCollision() {
        int[] head = snakeBody.get(0);

        // Check self-collision
        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeBody.get(i)[0] == head[0] && snakeBody.get(i)[1] == head[1]) {
                isRunning = false;
                endGame();
            }
        }
    }

    private static void endGame() {
        System.out.println("Game Over!");
        System.out.println("Your final score: " + score);
        System.out.println("Your rank: " + getRank(score));
        System.exit(0); // Closes the program
    }

    // Get the rank based on the score
    private static String getRank(int score) {
        if (score <= 3) {
            return "Iron";
        } else if (score <= 6) {
            return "Bronze";
        } else if (score <= 9) {
            return "Silver";
        } else if (score <= 12) {
            return "Gold";
        } else if (score <= 15) {
            return "Platinum";
        } else {
            return "Diamond";
        }
    }
} 