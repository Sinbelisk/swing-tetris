package tetris.gui;

import tetris.Drawable;
import tetris.Updatable;
import tetris.gameLogic.TetrisGrid;
import tetris.gameLogic.Timer;
import tetris.gameLogic.tetrominos.Bag;
import tetris.gameLogic.tetrominos.Tetromino;
import tetris.gui.events.KeyEvents.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PieceDrawer implements Drawable, Updatable {
    private static final int CELL_SIZE = 25;
    private static final int BASE_DROP_DELAY = 1000;
    private final TetrisGrid GRID;
    private final Bag BAG;
    private final int currentDropDelay;
    private Tetromino currentPiece;
    private Tetromino tempPiece; // invisible piece for handling collision simulation
    private final Timer moveTimer;
    private final Timer dropTimer;

    public PieceDrawer(TetrisGrid grid, Bag bag, Timer timer) {
        this.GRID = grid;
        this.BAG = bag;
        this.currentPiece = BAG.getNewPiece();
        this.moveTimer = timer;
        this.currentDropDelay = BASE_DROP_DELAY;
        this.dropTimer = new Timer(currentDropDelay);
    }

    @Override
    public void draw(Graphics2D g2d) {
        drawPiece(g2d, currentPiece);
    }

    @Override
    public void update() {
        if (moveTimer.hasElapsed()) {
            checkForInput();
        }
    }

    private void checkForInput() {
        int currentX = currentPiece.getPosX(); // Current row
        int currentY = currentPiece.getPosY(); // Current column

        // Detect user input
        if (KeyHandler.isKeyPressed(KeyEvent.VK_D)) {
            if (canMove(currentPiece, 1, 0)) { // Propose movement to the right
                currentPiece.move(1, 0);
            }
        } else if (KeyHandler.isKeyPressed(KeyEvent.VK_A)) {
            if (canMove(currentPiece, -1, 0)) { // Propose movement to the left
                currentPiece.move(-1, 0);
            }
        } else if (KeyHandler.isKeyPressed(KeyEvent.VK_S)) {
            if (canMove(currentPiece, 0, 1)) { // Propose movement downwards
                currentPiece.move(0, 1);
            }
        } else if (KeyHandler.isKeyPressed(KeyEvent.VK_W)) {
            if (canMove(currentPiece, 0, -1)) { // Propose movement upwards
                currentPiece.move(0, -1);
            }
        } else if (KeyHandler.isKeyPressed(KeyEvent.VK_R)) {
            currentPiece.rotateClockWise(); // Rotate the piece
            // Check if the rotation is valid
            if (!canMove(currentPiece, 0, 0)) {
                currentPiece.rotateCounterClockWise(); // Undo the rotation if not valid
            }// Do not continue with the movement after rotation
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
                    GRID.setCell(y + i, x + j, piece.getPieceID());
                }
            }
        }
        currentPiece = BAG.getNewPiece();
        piece.reset();
    }

    public boolean canMove(Tetromino currentShape, int deltaX, int deltaY) { //Dolor y sufrimiento.
        int[][] shape = currentShape.getCurrentShape();
        int[][] board = GRID.getBoard();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j]!= 0) { // If it's part of the piece
                    int newRow = currentShape.getPosY() + i + deltaY; // New row
                    int newCol = currentShape.getPosX() + j + deltaX; // New column

                    // Check if it's out of bounds
                    if (newRow < 0 || newRow >= GameManager.ROWS || newCol < 0 || newCol >= GameManager.COLUMNS) {
                        return false;
                    }

                    // Check if it collides with another piece on the board
                    if (board[newRow][newCol]!= 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }



}
