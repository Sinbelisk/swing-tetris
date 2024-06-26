package tetris.gameLogic.tetrominos;

import tetris.util.MatrixOperations;

public class IPiece extends Tetromino {
    public IPiece() {
        super(0, 0, 1);
        // shape 0
       this.currentShape = new int[][] {
               {0, 0, 0, 0},
               {1, 1, 1, 1},
               {0, 0, 0, 0},
               {0, 0, 0, 0},
       };
       rotations = MatrixOperations.generateRotations(currentShape);
    }
}
