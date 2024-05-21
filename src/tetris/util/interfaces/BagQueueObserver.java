package tetris.util.interfaces;

import tetris.gameLogic.tetrominos.Tetromino;

public interface BagQueueObserver  {
    void notifyNextPieceChange(Tetromino tetromino);
}
