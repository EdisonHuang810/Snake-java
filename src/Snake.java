import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java. util. Random;
import javax.swing.JFrame;

public class Snake {
    
    public static void main(String[] args){
        int playerSize = 0;
        Random randomNumber = new Random(); //generate random numbers
        int random = randomNumber.nextInt(10); //generate random numbers
        int[] applePosition = {random,random}; // random apple position
        int rows = 10;
        int columns = 10;
        int[] playerPosition = {5,2}; //player position
        printGrid(rows, columns, playerPosition,applePosition); //print the grid
        //movePlayer()
        JFrame frame = new JFrame("Snake Game");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        

}
    
public static void printGrid(int r,int c,int[] player, int[] applePosition){
    int[] playerPosition = player;
    int rows = r;
    int columns = c;
    int[] applePos = applePosition;

    String[][] grid = new String[rows][columns];
    for(int i=0;i<rows;i++){
        for(int j=0;j<columns;j++){
            grid[i][j] = "[ ]"; // set grid to [ ]
        }
    }
    int playerRow = playerPosition[0]; // setting position of the player 
    int playerColumn = playerPosition[1]; 
    grid[playerRow][playerColumn] = "[O]";// setting the player icon to O;
    int appleRow = applePos[0]; // setting position of apple
    int appleColumn = applePos[1];
    grid[appleRow][appleColumn] = "[*]"; // setting the icon of apple to *
    for(int i=0;i<rows;i++){
        for(int j=0;j<columns;j++){
            System.out.print(grid[i][j]); // print the grid
        }
        System.out.println(""); // get an extra row every time.

    }


}
public static void movePlayer(int[] playerPosition, int newRow, int newColumn) {
    playerPosition[0] = newRow;
    playerPosition[1] = newColumn;
}
public static void touchApple(int[] playerPosition, int[] applePosition, int playerSize){
    int playerSi = playerSize;
    int[] playerPos = playerPosition;
    int[] applePos = applePosition;
    if(playerPos[0] == applePos[0] && playerPos[1] == applePos[1]){
        playerSi++;
    }
    

}
}

