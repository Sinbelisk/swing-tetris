package tetris.gui;

import tetris.util.interfaces.IDrawable;
import tetris.util.interfaces.IUpdatable;
import tetris.gameLogic.TetrisGrid;
import tetris.gameLogic.Timer;
import tetris.gameLogic.tetrominos.Bag;
import tetris.gameLogic.tetrominos.Tetromino;
import tetris.gui.events.KeyEvents.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

import static tetris.gui.MainMenu.game;

public class PieceDrawer implements IDrawable {
    private static final int CELL_SIZE = 25;
    private final Tetromino currentPiece;
    public PieceDrawer(Tetromino currentPiece) {
        this.currentPiece = currentPiece;
    }
    @Override
    public void draw(Graphics2D g2d) {
        drawPiece(g2d, currentPiece);
    }
    public void drawPiece(Graphics2D g2d, Tetromino piece) {
        int[][] shape = piece.getCurrentShape();
        g2d.setColor(GameManager.getPieceColor(piece.getPieceID()));
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    g2d.fillRect((piece.getPosX() + j) * CELL_SIZE, (piece.getPosY() + i) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }
}
