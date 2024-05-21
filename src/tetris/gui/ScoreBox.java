package tetris.gui;

import tetris.gameLogic.tetrominos.Bag;
import tetris.gameLogic.tetrominos.Tetromino;
import tetris.util.interfaces.Observer;

import javax.swing.*;
import java.awt.*;

public class ScoreBox extends JPanel implements Observer {
    private static final int gridSize = 6;
    private Tetromino currentPiece;
    public ScoreBox() {
        setSize(Game.SIZE * gridSize, Game.SIZE * gridSize);
        setBackground(Color.black);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawPiece(g2d, currentPiece);
        drawString(g2d);
    }

    public void drawString(Graphics2D g2d){
        String text = "Next piece:";
        g2d.setColor(Color.WHITE);
        g2d.drawString("Next Piece:", (getWidth()-text.length())/3, 10);
    }
    public void drawPiece(Graphics2D g2d, Tetromino piece) {
        g2d.setStroke(new BasicStroke(3));
        int[][] shape = piece.getCurrentShape();
        int pieceWidth = shape[0].length * Game.SIZE;
        int pieceHeight = shape.length * Game.SIZE;

        int offsetX = (getWidth() - pieceWidth) / 2;
        int offsetY = (getHeight() - pieceHeight) / 2;

        g2d.setColor(GameManager.getPieceColor(piece.getPieceID()));
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    g2d.fillRect(offsetX + j * Game.SIZE, offsetY + i * Game.SIZE, Game.SIZE, Game.SIZE);
                }
            }
        }

        // Horizontal lines
        g2d.setColor(Color.BLACK);
        for (int i = 0; i <= shape.length; i++) {
            g2d.drawLine(offsetX, offsetY + i * Game.SIZE, offsetX + pieceWidth, offsetY + i * Game.SIZE);
        }

        // Vertical Lines
        for (int j = 0; j <= shape[0].length; j++) {
            g2d.drawLine(offsetX + j * Game.SIZE, offsetY, offsetX + j * Game.SIZE, offsetY + pieceHeight);
        }
    }

    @Override
    public void refreshPiece(Tetromino newPiece) {
        this.currentPiece = newPiece;
    }
}
