package tetris.gameLogic.tetrominos;

public class LPiece extends Tetromino{
    public LPiece() {
        super(5, -2, 2);

        this.rotations = new int[][][] {
                {
                        {0, 0, 1},
                        {1, 1, 1},
                        {0, 0, 0},
                },
                {
                        {0, 1, 0},
                        {0, 1, 0},
                        {0, 1, 1},
                },
                {
                        {0, 0, 0},
                        {1, 1, 1},
                        {1, 0, 0},
                },
                {
                        {1, 1, 0},
                        {0, 1, 0},
                        {0, 1, 0},
                },
        };
        this.currentShape = rotations[rotationIndex];
    }
}
