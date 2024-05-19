package tetris.gameLogic.tetrominos;

import tetris.MatrixOperations;

public class QPiece extends Tetromino{
    public QPiece(){
        super(0, 0, 7);
        this.currentShape = new int[][] {
                {1, 1},
                {1, 1},
        };

        rotations = MatrixOperations.generateRotations(currentShape);
    }
}
