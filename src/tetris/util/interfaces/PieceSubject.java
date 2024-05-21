package tetris.util.interfaces;

import tetris.gameLogic.tetrominos.Tetromino;

import java.util.ArrayList;
import java.util.List;

public interface PieceSubject {
    List<BagObserver> observers = new ArrayList<>();
    List<BagQueueObserver> queueObservers = new ArrayList<>();

    default void addObserver(BagObserver observer) {
        observers.add(observer);
    }
    default void addQueueObserver(BagQueueObserver observer){
        queueObservers.add(observer);
    }
    default void notifyObservers(Tetromino newPiece) {
        for (BagObserver observer : observers) {
            observer.refreshPiece(newPiece);
        }
    }
    default  void notifyQueueObservers(Tetromino nextPiece){
        for (BagQueueObserver queueObserver : queueObservers) {
            queueObserver.notifyNextPieceChange(nextPiece);
        }
    }

}
