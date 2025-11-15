package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e){
    }
    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        
        switch (code) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> upPressed=true;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPressed=true;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPressed=true;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPressed=true;
            default -> {
            }

        // switch (code) {
        //     case KeyEvent.VK_W-> upPressed=true;
        //     case KeyEvent.VK_A -> leftPressed=true;
        //     case KeyEvent.VK_D -> rightPressed=true;
        //     case KeyEvent.VK_S -> downPressed=true;
        //     default -> {
        //     }
        // }
        
    }
}

    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> upPressed=false;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPressed=false;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPressed=false;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPressed=false;
            default -> {
            }

        // switch (code) {
        //     case KeyEvent.VK_W -> upPressed=false;
        //     case KeyEvent.VK_A -> leftPressed=false;
        //     case KeyEvent.VK_D -> rightPressed=false;
        //     case KeyEvent.VK_S -> downPressed=false;
        //     default -> {
        //     }
        // }

        }
    }
}
