package tetris.util.interfaces;

import tetris.gameLogic.tetrominos.Tetromino;

import java.util.ArrayList;
import java.util.List;

public interface CurrentPieceSubject {
    List<BagObserver> observers = new ArrayList<>();

    default void addObserver(BagObserver observer) {
        observers.add(observer);
    }

    default void removeObserver(BagObserver observer) {
        observers.remove(observer);
    }

    default void notifyObservers(Tetromino newPiece) {
        for (BagObserver observer : observers) {
            observer.refreshPiece(newPiece);
        }
    }
}
