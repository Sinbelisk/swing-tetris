package tetris.gui.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyHandler implements KeyListener {
    private static final Map<Integer, Boolean> keyStateMap = new HashMap<>();

    private KeyHandler(){

    }

    public static KeyHandler getInstance() {
        return instance;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        synchronized (keyStateMap) {
            keyStateMap.put(e.getKeyCode(), true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        synchronized (keyStateMap) {
            keyStateMap.put(e.getKeyCode(), false);
        }
    }

    public static boolean isKeyPressed(int keyCode) {
        synchronized (keyStateMap) {
            return keyStateMap.getOrDefault(keyCode, false);
        }
    }
}


