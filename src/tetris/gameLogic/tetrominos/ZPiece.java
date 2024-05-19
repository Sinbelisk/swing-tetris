package tetris.gameLogic.tetrominos;

import tetris.util.MatrixOperations;

public class ZPiece extends Tetromino{
    public ZPiece(){
        super(0, 0, 4);
        this.currentShape = new int[][] {
                {1, 1, 0},
                {0, 1, 1},
                {0, 0, 0},
        };

        rotations = MatrixOperations.generateRotations(currentShape);
    }
}
