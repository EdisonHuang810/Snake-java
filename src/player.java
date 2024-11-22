import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class player implements KeyListener {}
    public int rowPosition;
    public int colPosition;
    boolean keyPressed = false;

    public void KeyTyped(KeyEvent d){
        keyPressed = true;
        while(keyPressed){
            rowPosition ++;
        }

    }

    public void playerPosition(int x, int y){
        x = rowPosition;
        y = colPosition;
    }
    



    
    
}
