package tetris.util.interfaces;

import tetris.gameLogic.tetrominos.Tetromino;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    List<Observer> observers = new ArrayList<>();

    default void addObserver(Observer observer) {
        observers.add(observer);
    }

    default void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    default void notifyObservers(Tetromino newPiece) {
        for (Observer observer : observers) {
            observer.refreshPiece(newPiece);
        }
    }
}
