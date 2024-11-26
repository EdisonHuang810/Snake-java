import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Snake {
    
    public static void main(String[] args){
        int rows = 10;
        int columns = 10;
        int[] playerPosition = {5,2}; //player position
        printGrid(rows, columns, playerPosition); //print the grid
        //movePlayer()
        JFrame frame = new JFrame("Snake Game");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}
    
public static void printGrid(int r,int c,int[] player){
    int[] playerPosition = player;
    int rows = r;
    int columns = c;

    String[][] grid = new String[rows][columns];
    for(int i=0;i<rows;i++){
        for(int j=0;j<columns;j++){
            grid[i][j] = "[ ]"; // set grid to [ ]
        }
    }
    int playerRow = playerPosition[0]; // setting position of the player 
    int playerColumn = playerPosition[1]; 
    grid[playerRow][playerColumn] = "[O]";// setting the player icon to O;
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
}

