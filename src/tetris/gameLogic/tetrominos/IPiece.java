package tetris.gameLogic.tetrominos;
public class IPiece extends Tetromino {
    public IPiece() {
        super(0, 5);
        // shape 0
        this.currentShape = new int[][] {
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

    }
}
