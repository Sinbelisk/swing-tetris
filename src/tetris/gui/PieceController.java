package tetris.gui;

import tetris.gameLogic.TetrisGrid;
import tetris.gameLogic.Timer;
import tetris.gameLogic.tetrominos.Tetromino;
import tetris.util.interfaces.BagObserver;
import tetris.util.interfaces.IUpdatable;

public class PieceController implements IUpdatable, BagObserver {
    public static final int BASE_MOVE_DELAY = 75;
    private static final int BASE_DROP_DELAY = 900;
    private static final int PLACEMENT_DELAY = 120;
    private final TetrisGrid grid;
    private final Timer moveTimer = new Timer(BASE_MOVE_DELAY);
    private final Timer dropTimer;
    private final Timer placementTimer = new Timer(PLACEMENT_DELAY);
    private final int currentDropDelay;
    private Tetromino currentPiece;
    private boolean piecePlaced;
    private boolean gameOver;

    public PieceController(TetrisGrid grid) {
        this.grid = grid;

        dropTimer = new Timer(BASE_DROP_DELAY);
        currentDropDelay = BASE_DROP_DELAY;
    }

    @Override
    public void update() {
        if (grid.checkIfTopRowsAreOccupied()) {
            gameOver = true;
        }

        if (dropTimer.hasElapsed()) {
            drop();
        }

        if (currentPiece.isColliding() && placementTimer.hasElapsed()) {
            placePiece(currentPiece);
        }
    }

    public void placePiece(Tetromino piece) {
        piece.setColliding(false);
        int[][] currentShape = piece.getCurrentShape();
        int y = piece.getPosX();
        int x = piece.getPosY();

        for (int i = 0; i < currentShape.length; i++) {
            for (int j = 0; j < currentShape[i].length; j++) {
                if (currentShape[i][j] != 0) {
                    grid.setCell(x + i, y + j, piece.getPieceID());
                }
            }
        }
        piecePlaced = true;
        piece.reset();
    }

    public void move(int x, int y) {
        if (moveTimer.hasElapsed()) {
            if (canMove(currentPiece, x, y)) {
                currentPiece.move(x, y);
            }
        }
    }

    public void rotate() {
        currentPiece.rotateClockWise();
        if (!canMove(currentPiece, 0, 0)) {
            currentPiece.rotateCounterClockWise();
        }
    }

    private void drop() {
        move(0, 1);
    }

    public boolean canMove(Tetromino currentShape, int deltaX, int deltaY) {
        int[][] shape = currentShape.getCurrentShape();
        int[][] board = grid.getBoard();

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

                    // Check if it collides with another piece
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
        return newRow >= 0 && newRow < Game.ROWS && newColumn >= 0 && newColumn < Game.COLUMNS;
    }

    private boolean touchingBottom(int newRow) { // checks if the row is touching the bottom of the board or another piece
        return newRow == Game.ROWS - 1;
    }

    public boolean isPiecePlaced() {
        return piecePlaced;
    }

    public void setPiecePlaced(boolean piecePlaced) {
        this.piecePlaced = piecePlaced;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void refreshPiece(Tetromino newPiece) {
        this.currentPiece = newPiece;
    }
}
