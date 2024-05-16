package tetris.gameLogic.tetrominos;

import tetris.MatrixOperations;

public class ZPiece extends Tetromino{
    public ZPiece(){
        super(5, -2, 4);
        this.currentShape = new int[][] {
                {1, 1, 0},
                {0, 1, 1},
                {0, 1, 1},
        };

        rotations = MatrixOperations.generateRotations(currentShape);
    }
}
