package tetris.gameLogic;

import tetris.gameLogic.tetrominos.Tetromino;

import javax.swing.*;
import java.awt.*;

public class PieceControl {

    private static final int CELL_SIZE = 25;

    public void drawPiece(Graphics g, Tetromino piece ) {
        int[][] shape = piece.getCurrentShape();
        g.setColor(getPieceColor(piece.getPieceID()));
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    g.fillRect((piece.getPosX() + j) * CELL_SIZE, (piece.getPosY() + i) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }
    public void repaint(JPanel currentPanel, Tetromino piece){
        int[][] shape = piece.getCurrentShape();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    currentPanel.repaint((piece.getPosX() + j) * CELL_SIZE, (piece.getPosY() + i) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    private Color getPieceColor(int pieceID){
        return switch (pieceID){
            case 1 -> Color.CYAN;
            case 2 -> Color.orange;
            case 3 -> Color.magenta;

            default -> throw new IllegalStateException("Unexpected value: " + pieceID);
        };
    }

}
