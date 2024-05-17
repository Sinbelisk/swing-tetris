package tetris.gui;

import tetris.gameLogic.TetrisGrid;
import tetris.gameLogic.tetrominos.Tetromino;

import javax.swing.*;
import java.awt.*;

public class PieceControl {
    private TetrisGrid grid;
    public PieceControl(TetrisGrid grid){
        this.grid = grid;
    }
    private static final int CELL_SIZE = 25;
    private int dropCount = 0;
    private final int INTERVAL = 60;

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
    public void placePiece(Tetromino piece, JPanel currentPanel) {
        // Repaint the panel to clear the previous state
        currentPanel.repaint();

        int[][] currentShape = piece.getCurrentShape();
        int x = piece.getPosX();
        int y = piece.getPosY();

        for (int i = 0; i < currentShape.length; i++) {
            for (int j = 0; j < currentShape[i].length; j++) {
                if (currentShape[i][j] != 0) {
                    // Correct the placement logic
                    grid.setCell(y + i, x + j, piece.getPieceID());
                }
            }
        }
        piece.reset();
    }
    public Color getPieceColor(int pieceID){
        return switch (pieceID){
            case 1 -> Color.CYAN;
            case 2 -> Color.orange;
            case 3 -> Color.magenta;
            case 4 -> Color.red;
            case 5 -> Color.green;
            case 6 -> Color.BLUE;
            case 7 -> Color.yellow;

            default -> Color.BLACK;
        };
    }

    public void drop(Tetromino currentPiece){
        dropCount++;
        if(dropCount == INTERVAL){
            currentPiece.move(0, 1);
            dropCount = 0;
        }
    }

}
