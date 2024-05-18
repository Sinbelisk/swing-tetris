package tetris.gui;

import tetris.Drawable;
import tetris.Updatable;
import tetris.gameLogic.TetrisGrid;
import tetris.gameLogic.tetrominos.Bag;
import tetris.gameLogic.tetrominos.Tetromino;
import tetris.gui.events.KeyHandler;

import java.awt.*;
import java.security.Key;

public class PieceDrawer implements Drawable, Updatable {
    private final TetrisGrid GRID;
    private final Bag BAG;
    private Tetromino currentPiece;
    private static final int CELL_SIZE = 25;
    private int dropCount = 0;
    private final int INTERVAL = 60;
    public PieceDrawer(TetrisGrid grid, Bag bag){
        this.GRID = grid;
        this.BAG = bag;
        this.currentPiece = BAG.getNewPiece();
    }
    @Override
    public void draw(Graphics2D g2d) {
        drawPiece(g2d, currentPiece);
    }
    @Override
    public void update() {
        checkForInput();
    }
    private void checkForInput(){
        if (KeyHandler.issPressed()){
            currentPiece.move(0,1);
        }

        if(KeyHandler.isrPressed()){
            placePiece(currentPiece);
            System.out.println("a");
        }

        if(KeyHandler.isaPressed()){
            currentPiece.move(-1, 0);
        }

        if(KeyHandler.isdPressed()){
            currentPiece.move(1, 0);
        }

        if(KeyHandler.iswPressed()){
            currentPiece.move(0, -1);
        }
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
    public void placePiece(Tetromino piece) {
        // Repaint the panel to clear the previous state
        int[][] currentShape = piece.getCurrentShape();
        int x = piece.getPosX();
        int y = piece.getPosY();

        for (int i = 0; i < currentShape.length; i++) {
            for (int j = 0; j < currentShape[i].length; j++) {
                if (currentShape[i][j] != 0) {
                    // Correct the placement logic
                    GRID.setCell(y + i, x + j, piece.getPieceID());
                }
            }
        }
        currentPiece = BAG.getNewPiece();
        piece.reset();
    }

    public void drop(Tetromino currentPiece){
        dropCount++;
        if(dropCount == INTERVAL){
            currentPiece.move(0, 1);
            dropCount = 0;
        }
    }
}
