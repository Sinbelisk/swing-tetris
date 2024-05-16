package tetris.gameLogic.tetrominos;
public class IPiece extends Tetromino {
    public IPiece() {
        super(0, 5, 1);
        // shape 0
       this.rotations = new int[][][] {
               {
                       {0, 0, 0, 0},
                       {1, 1, 1, 1},
                       {0, 0, 0, 0},
                       {0, 0, 0, 0},
               },
               {
                       {0, 0, 0, 1},
                       {0, 0, 0, 1},
                       {0, 0, 0, 1},
                       {0, 0, 0, 1},
               },
               {
                       {0, 0, 0, 0},
                       {0, 0, 0, 0},
                       {1, 1, 1, 1},
                       {0, 0, 0, 0},
               },
               {
                       {1, 0, 0, 0},
                       {1, 0, 0, 0},
                       {1, 0, 0, 0},
                       {1, 0, 0, 0},
               }
       };
       this.currentShape = rotations[rotationIndex];


    }
}
