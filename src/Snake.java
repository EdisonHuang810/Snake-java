import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

public class Snake {
    private static int score;

    static Random randomNumber = new Random(); //generate random numbers
    private static int random = randomNumber.nextInt(7); //generate random numbers
    private static int[] applePosition = {random, random}; // random apple position
    private static ArrayList<int[]> playerBody = new ArrayList<>();
    private static int rows = 7;
    private static int columns = 7;
    private static boolean playerAlive = true;
    private static char playerDirection = 'd';
    private static char currentDirection = playerDirection;
    
    public static void main(String[] args){
        playerBody.add(new int[]{5, 2});  //snake position (starting position)

        JFrame frame = new JFrame("Snake Game"); //Jframe used for wasd movement
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        setupPlayerInput(frame);

        while(playerAlive){ // Constantly detecting updates like void Update()
            touchApple();
            movePlayer();
            if (playerBody.get(0)[0] < 0 || playerBody.get(0)[0] >= rows ||
                playerBody.get(0)[1] < 0 || playerBody.get(0)[1] >= columns) {
                playerAlive = false; // Detect if player dies
                break; // Exit the loop immediately
            }
            clearScreen();
            printGrid();
           
            try {
                Thread.sleep(500); // Stop the infinite loop
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Game Over!");
        System.out.println("Score: " + score);
        System.out.println("Rank: " + getRank(score)); 
    }

    public static void setupPlayerInput(JFrame frame) { //this is all the playerInput from Jframe (chatgpt)
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                char input = Character.toUpperCase(e.getKeyChar());
                if (input == 'W' || input == 'A' || input == 'S' || input == 'D') {
                    currentDirection = input;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyTyped(KeyEvent e) {}
        });
    } // up to here

    public static void movePlayer(){
        for (int i = playerBody.size() - 1; i > 0; i--) { //very similar to .length
            playerBody.set(i, playerBody.get(i - 1));  // Move each segment to the position of the one before it
        }
        int[] head = playerBody.get(0);  // Get the head position
        switch (currentDirection) { //this is all chatgpt but basically its the logic that makes the snake able to move
            case 'W': playerBody.set(0, new int[]{head[0] - 1, head[1]}); break;
            case 'A': playerBody.set(0, new int[]{head[0], head[1] - 1}); break;
            case 'S': playerBody.set(0, new int[]{head[0] + 1, head[1]}); break;
            case 'D': playerBody.set(0, new int[]{head[0], head[1] + 1}); break;
        }
    }

    

    public static void printGrid() {
        String[][] grid = new String[rows][columns];
    
        // Initialize the grid with empty spaces
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = "[ ]"; // Set grid to empty space
            }
        }
    
        // Place the player (snake) on the grid
        for (int i = 0; i < playerBody.size(); i++) {
            int[] segment = playerBody.get(i);
            if (segment[0] >= 0 && segment[0] < rows && segment[1] >= 0 && segment[1] < columns) {
                grid[segment[0]][segment[1]] = "[O]"; // Mark the snake's body
            }
        }
    
        // Place the apple, but only if it's within bounds
        if (applePosition[0] >= 0 && applePosition[0] < rows &&
            applePosition[1] >= 0 && applePosition[1] < columns) {
            if (grid[applePosition[0]][applePosition[1]].equals("[ ]")) {
                grid[applePosition[0]][applePosition[1]] = "[*]"; // Mark the apple
            }
        }
    
        // Print the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println(); // Newline after each row
        }
    }
    

    public static void touchApple(){
        if (playerBody.get(0)[0] == applePosition[0] && playerBody.get(0)[1] == applePosition[1]) {
            score++;
            // Generate a new random position for the apple
            applePosition[0] = randomNumber.nextInt(rows); // new row position
            applePosition[1] = randomNumber.nextInt(columns); // new column position

            // Add new tail segment
            int[] tail = playerBody.get(playerBody.size() - 1);  // Get the tail position
            playerBody.add(new int[]{tail[0], tail[1]});  // Add new segment at the tail
        }
    }

    public static void detectIfDead(int[] player){
        int[] playerPosition = player;
        //if(player)
    }

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

    public static void clearScreen() {
        // Print 50 new lines to "clear" the console
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
