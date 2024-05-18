package tetris.gui.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    private static boolean rPressed, sPressed, aPressed, dPressed, wPressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_R -> rPressed = true; //Rotate piece
            case KeyEvent.VK_S -> sPressed = true;
            case KeyEvent.VK_A -> aPressed = true;
            case KeyEvent.VK_D -> dPressed = true;
            case KeyEvent.VK_W -> wPressed = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        rPressed = false;
        sPressed = false;
        aPressed = false;
        dPressed = false;
        wPressed = false;
    }

    private static void resetState(){

    }
    public static boolean isrPressed() {
        return rPressed;
    }

    public static boolean issPressed() {
        return sPressed;
    }

    public static boolean isaPressed() {
        return aPressed;
    }

    public static boolean isdPressed() {
        return dPressed;
    }

    public static boolean iswPressed() {
        return wPressed;
    }
}
