package tetris.gameLogic.tetrominos;

import tetris.util.MatrixOperations;

public class TPiece extends Tetromino {

    public TPiece() {
        super(0, 0, 3);
        this.currentShape = new int[][] {
                {0, 1, 0},
                {1, 1, 1},
                {0, 0, 0},
        };

        rotations = MatrixOperations.generateRotations(currentShape);
    }
}
