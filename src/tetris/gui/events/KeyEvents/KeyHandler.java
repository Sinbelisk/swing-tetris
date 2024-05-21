package tetris.gui.events.KeyEvents;

import tetris.gui.Game;
import tetris.gui.PieceController;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    private final PieceController pieceController;
    private final Game game;
    public KeyHandler(PieceController pieceController,Game game) {
        this.pieceController = pieceController;
        this.game = game;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        handleKeyPress(e.getKeyCode());
    }

    private void handleKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A:
                pieceController.move(-1, 0);
                break;
            case KeyEvent.VK_D:
                pieceController.move(1,0);
                break;
            case KeyEvent.VK_S:
                pieceController.move(0, 1);
                break;
            case KeyEvent.VK_R:
                pieceController.rotate();
                break;
            case KeyEvent.VK_W:
                pieceController.move(0, -1);
                break;
            case KeyEvent.VK_ESCAPE:
                if(!game.isPaused()){
                    game.pauseGameLoop();
                } else game.resumeGameLoop();
        }
    }
}

