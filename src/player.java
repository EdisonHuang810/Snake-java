import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class player implements KeyListener {}
    public int rowPosition;
    public int colPosition;
    boolean keyPressed = false;

    public void keyPressed(KeyEvent d){
        keyPressed = true;
        while(keyPressed){
            rowPosition ++;
        }
    public void keyReleased(){
        keyPressed = false;
    }
    

    }

    public void playerPosition(int x, int y){
        x = rowPosition;
        y = colPosition;
    }
    



    
    
}
