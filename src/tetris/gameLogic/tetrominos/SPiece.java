package tetris.gameLogic.tetrominos;

import tetris.util.MatrixOperations;

public class SPiece extends Tetromino{
    public SPiece(){
        super(0, 0, 5);
        this.currentShape = new int[][] {
                {0, 1, 1},
                {1, 1, 0},
                {0, 0, 0},
        };

        rotations = MatrixOperations.generateRotations(currentShape);
    }
}
