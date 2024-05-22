package tetris.gui;

import tetris.db.DBManager;
import tetris.gameLogic.tetrominos.Bag;
import tetris.gameLogic.tetrominos.Tetromino;
import tetris.util.interfaces.IDrawable;
import tetris.util.interfaces.IUpdatable;
import tetris.util.interfaces.PieceSubject;

import javax.swing.*;
import java.awt.*;

public class GameManager implements IUpdatable, IDrawable, PieceSubject {
    public final PieceDrawer pieceDrawer;
    public final ScoreManager scoreManager;
    public final Bag bag;
    private final Game game;
    private final BoardDrawer boardDrawer;
    private final PieceController pieceController;

    public GameManager(BoardDrawer boardDrawer, PieceDrawer pieceDrawer, PieceController pieceController, ScoreManager scoreManager, Game game) {
        this.boardDrawer = boardDrawer;
        this.pieceDrawer = pieceDrawer;
        this.pieceController = pieceController;
        this.scoreManager = scoreManager;
        this.bag = new Bag();
        this.game = game;

        addObserver(pieceDrawer);
        addObserver(pieceController);
        newPiece();
    }

    public static Color getPieceColor(int pieceID) {
        return switch (pieceID) {
            case 1 -> Color.CYAN;
            case 2 -> Color.orange;
            case 3 -> Color.magenta;
            case 4 -> Color.red;
            case 5 -> Color.green;
            case 6 -> Color.BLUE;
            case 7 -> Color.yellow;
            default -> Color.BLACK;
        };
    }

    public void newPiece() {
        Tetromino currentPiece = bag.getNewPiece();
        notifyQueueObservers(bag.getNextPiece());
        notifyObservers(currentPiece);
    }

    @Override
    public void update() {
        pieceController.update();
        scoreManager.update();

        if (pieceController.isPiecePlaced()) {
            pieceController.setPiecePlaced(false);
            newPiece();
        }

        if (pieceController.isGameOver()) {
            pieceController.setGameOver(false);
            gameOver();
        }
    }

    private void gameOver() {
        String message = "¡YOU LOST!, Do you want to save your score?";
        int option = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), message);
        if (option == JOptionPane.YES_OPTION) {
            String name = JOptionPane.showInputDialog("Insert name");
            if (name != null && !name.trim().isEmpty()) {
                int score = scoreManager.score.getScore();
                DBManager.insertInto(name, score);
            }
        }
        game.menu.showMainLayer();
        game.menu.reset();
        game.stopGameLoop();
    }

    @Override
    public void draw(Graphics2D g2d) {
        boardDrawer.drawOccupiedSlots(g2d);
        pieceDrawer.draw(g2d);
        boardDrawer.drawGrid(g2d);
    }
}
