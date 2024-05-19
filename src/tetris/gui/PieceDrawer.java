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

public class PieceDrawer implements IDrawable, IUpdatable {
    private static final int CELL_SIZE = 25;
    private static final int BASE_DROP_DELAY = 900;
    private static final int PLACEMENT_DELAY = 120;
    private final TetrisGrid GRID;
    private final Bag BAG;
    private final int currentDropDelay;
    private final Timer moveTimer;
    private final Timer dropTimer;
    private final Timer placementTimer;
    private Tetromino currentPiece;
    private Tetromino tempPiece; // invisible piece for handling collision simulation

    public PieceDrawer(TetrisGrid grid, Bag bag, Timer timer) {
        this.GRID = grid;
        this.BAG = bag;
        this.currentPiece = BAG.getNewPiece();
        this.moveTimer = timer;
        this.currentDropDelay = BASE_DROP_DELAY;
        this.dropTimer = new Timer(currentDropDelay);
        this.placementTimer = new Timer(PLACEMENT_DELAY);
    }

    @Override
    public void draw(Graphics2D g2d) {
        drawPiece(g2d, currentPiece);
    }

    @Override
    public void update() {
        debugInput();
        if (dropTimer.hasElapsed() && !KeyHandler.isKeyPressed(KeyEvent.VK_S)) {
            drop();
        }

        if (currentPiece.isColliding() && placementTimer.hasElapsed()) {
            placePiece(currentPiece);
        }

        if (moveTimer.hasElapsed()) {
            checkForInput();
        }
    }

    private void drop() {
        if (canMove(currentPiece, 0, 1)) currentPiece.move(0, 1);
    }

    private void debugInput() {
        if (KeyHandler.isKeyPressed(KeyEvent.VK_ENTER)) GRID.clearAndMoveAllRows();
    }

    private void checkForInput() {
        // Value that represents how much the piece moves in a direccion on the board.
        int deltaX = 0;
        int deltaY = 0;

        if (KeyHandler.isKeyPressed(KeyEvent.VK_D)) {
            deltaX = 1;
        } else if (KeyHandler.isKeyPressed(KeyEvent.VK_A)) {
            deltaX = -1;
        } else if (KeyHandler.isKeyPressed(KeyEvent.VK_S)) {
            deltaY = 1;
        } else if (KeyHandler.isKeyPressed(KeyEvent.VK_W)) {
            deltaY = -1;
        } else if (KeyHandler.isKeyPressed(KeyEvent.VK_R)) {
            currentPiece.rotateClockWise();
            // Check if the rotation is valid
            if (!canMove(currentPiece, deltaX, deltaY)) {
                currentPiece.rotateCounterClockWise(); // Undo the rotation if not valid
                return;
            }
        } else if (KeyHandler.isKeyPressed(KeyEvent.VK_ENTER)) {
            placePiece(currentPiece);
        }

        if (canMove(currentPiece, deltaX, deltaY)) {
            currentPiece.move(deltaX, deltaY);
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
        piece.setColliding(false);
        int[][] currentShape = piece.getCurrentShape();
        int y = piece.getPosX();
        int x = piece.getPosY();
        System.out.println(x + "|" + y);
        System.out.println();

        for (int i = 0; i < currentShape.length; i++) {
            for (int j = 0; j < currentShape[i].length; j++) {
                if (currentShape[i][j] != 0) {
                    System.out.println((x + i) + "|" + (y + j));
                    GRID.setCell(x + i, y + j, piece.getPieceID());
                }
            }
        }
        currentPiece = BAG.getNewPiece();
        piece.reset();
    }

    public boolean canMove(Tetromino currentShape, int deltaX, int deltaY) {
        int[][] shape = currentShape.getCurrentShape();
        int[][] board = GRID.getBoard();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) { // If it's part of the piece
                    int newRow = currentShape.getPosY() + i + deltaY; // New row
                    int newCol = currentShape.getPosX() + j + deltaX; // New column

                    // Check if it's out of bounds
                    if (!insideBounds(newRow, newCol)) return false;

                    if (touchingBottom(newRow)) {
                        currentPiece.setColliding(true);
                    }

                    // Check if it collides with another piece currentShape.setColliding(true);
                    if (board[newRow][newCol] != 0) {
                        if (deltaY > 0) {
                            currentPiece.setColliding(true);
                        }
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean insideBounds(int newRow, int newColumn) {
        return newRow >= 0 && newRow < GameManager.ROWS && newColumn >= 0 && newColumn < GameManager.COLUMNS;
    }

    private boolean touchingBottom(int newRow) { // checks if the row is touching the bottom of the board or another piece
        return newRow == GameManager.ROWS - 1;
    }

}
