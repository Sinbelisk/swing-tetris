package tetris.gameLogic.tetrominos;

import tetris.util.MatrixOperations;

public class LPiece extends Tetromino{
    public LPiece() {
        super(0, 0, 2);
        this.currentShape = new int[][] {
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0},
        };

        rotations = MatrixOperations.generateRotations(currentShape);
    }
}
