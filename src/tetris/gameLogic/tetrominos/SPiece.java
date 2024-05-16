package tetris.gameLogic.tetrominos;

import tetris.MatrixOperations;

public class SPiece extends Tetromino{
    public SPiece(){
        super(5, -2, 5);
        this.currentShape = new int[][] {
                {0, 1, 1},
                {1, 1, 0},
                {1, 0, 0},
        };

        rotations = MatrixOperations.generateRotations(currentShape);
    }
}
