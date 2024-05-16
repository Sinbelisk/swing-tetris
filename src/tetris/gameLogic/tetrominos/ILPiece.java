package tetris.gameLogic.tetrominos;

import tetris.MatrixOperations;

public class ILPiece extends Tetromino{
    public ILPiece(){
        super(5, -2, 6);
        this.currentShape = new int[][] {
                {1, 0, 0},
                {1, 1, 1},
                {0, 0, 0},
        };

        rotations = MatrixOperations.generateRotations(currentShape);
    }
}
