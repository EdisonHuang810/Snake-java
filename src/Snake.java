import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java. util. Random;
import javax.swing.JFrame;

public class Snake {
    private static int score = 0;
    private static int playerSize = 1;

    static Random randomNumber = new Random(); //generate random numbers
        private static int random = randomNumber.nextInt(7); //generate random numbers
    private static int[] applePosition = {random,random}; // random apple position
    
    private static int rows = 7;
    private static int columns = 7;
    
    private static int[] playerPosition = {5,2}; //player position
    private static boolean playerAlive = true;
    private static char playerDirection = 'd';
    private static char currentDirection = playerDirection;
    
    public static void main(String[] args){

        
        touchApple();
        printGrid(); //print the grid
        //movePlayer()
        
        JFrame frame = new JFrame("Snake Game");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        setupPlayerInput(frame);



        while(playerAlive){
            touchApple();
            movePlayer();
        if (playerPosition[0] < 0 || playerPosition[0] >= rows ||
        playerPosition[1] < 0 || playerPosition[1] >= columns) {
        playerAlive = false; //detect if player dies
}

            printGrid();

            try {
                Thread.sleep(500); // Pause for 500ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            } //prevent infinite loop cuz while(playerAlive is infinite loop)
        }


        

}
public static void setupPlayerInput(JFrame frame) {
    frame.addKeyListener(new KeyListener() {
        @Override
        public void keyPressed(KeyEvent e) {
            char input = Character.toUpperCase(e.getKeyChar());
            // Update direction if valid input (WASD)
            if (input == 'W' || input == 'A' || input == 'S' || input == 'D') {
                currentDirection = input;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // Optional: Handle key releases
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // Optional: Handle key typing
        }
    });
}

// Method to move the player based on the current direction
public static void movePlayer(){

    switch (currentDirection) {
        case 'W': // Move up
            playerPosition[0]--;
            break;
        case 'A': // Move left
            playerPosition[1]--;
            break;
        case 'S': // Move down
            playerPosition[0]++;
            break;
        case 'D': // Move right
            playerPosition[1]++;
            break;
    }
}
    
public static void printGrid(){

    int playerRow = playerPosition[0]; // setting position of the player 
    int playerColumn = playerPosition[1]; 
    int appleRow = applePosition[0]; // setting position of apple
    int appleColumn = applePosition[1];

    //while(playerAlive){
    String[][] grid = new String[rows][columns];
 
    for(int i=0;i<rows;i++){
        for(int j=0;j<columns;j++){
            grid[i][j] = "[ ]"; // set grid to [ ]
        }
    }
    grid[playerRow][playerColumn] = "[O]";// setting the player icon to O;
    grid[appleRow][appleColumn] = "[*]"; // setting the icon of apple to *

    for(int i=0;i<rows;i++){
        for(int j=0;j<columns;j++){
            System.out.print(grid[i][j]); // print the grid
        }
        System.out.println(""); // get an extra row every time.

    }
}//}



public static void touchApple(){

    if (playerPosition[0] == applePosition[0] && playerPosition[1] == applePosition[1]) {
        playerSize++;
        score++;
        // Generate a new random position for the apple
        applePosition[0] = randomNumber.nextInt(rows); // new row position
        applePosition[1] = randomNumber.nextInt(columns); // new column position
    
}}

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

}

