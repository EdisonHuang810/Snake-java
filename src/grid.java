public class grid{
public static void main(String[] args){
    int rows = 7;
    int cols = 15;
    char[][] grid = new char[rows][cols];
    grid[5][5] = 's'; 


    for(int i=0;i< rows;i++){
        for(int k=0;k<cols;k++){
            grid[i][k] = '-';
            System.out.print(grid[i][k] + "");
        }
        System.out.println("");
    }
}
    }

