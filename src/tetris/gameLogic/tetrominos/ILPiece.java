package tetris.gameLogic.tetrominos;

import tetris.util.MatrixOperations;

public class ILPiece extends Tetromino{
    public ILPiece(){
        super(0, 0, 6);
        this.currentShape = new int[][] {
                {1, 0, 0},
                {1, 1, 1},
                {0, 0, 0},
        };

        rotations = MatrixOperations.generateRotations(currentShape);
    }
}
