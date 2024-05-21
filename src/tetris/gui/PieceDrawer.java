package tetris.gui;

import tetris.gameLogic.tetrominos.Tetromino;
import tetris.util.interfaces.BagObserver;
import tetris.util.interfaces.IDrawable;

import java.awt.*;

public class PieceDrawer implements IDrawable, BagObserver {
    private static final int CELL_SIZE = 25;
    private Tetromino currentPiece;

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

    @Override
    public void refreshPiece(Tetromino newPiece) {
        this.currentPiece = newPiece;
    }
}
