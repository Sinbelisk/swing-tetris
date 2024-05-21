package tetris.util.interfaces;

import tetris.gameLogic.tetrominos.Tetromino;

public interface Observer {
    void refreshPiece(Tetromino newPiece);
}
