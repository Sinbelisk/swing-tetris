package tetris.gameLogic.tetrominos;

import tetris.MatrixOperations;

public class TPiece extends Tetromino{

    public TPiece() {
        super(5, -2, 3);
        this.currentShape = new int[][] {
                {0, 1, 0},
                {1, 1, 1},
                {0, 0, 0},
        };

        rotations = MatrixOperations.generateRotations(currentShape);
    }
}
