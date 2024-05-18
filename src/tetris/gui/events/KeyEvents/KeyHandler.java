package tetris.gui.events.KeyEvents;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
public class KeyHandler implements KeyListener {
    // Esta clase es para gestionar eventos de teclado.
    private static final Map<Integer, Boolean> keyStateMap = new HashMap<>();

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


