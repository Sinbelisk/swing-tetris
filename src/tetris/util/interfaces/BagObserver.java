package tetris.util.interfaces;

import tetris.gameLogic.tetrominos.Tetromino;

public interface BagObserver {
    void refreshPiece(Tetromino newPiece);
}
