package tetris.gui;

import tetris.Main;
import tetris.db.DBManager;
import tetris.gameLogic.tetrominos.Bag;
import tetris.gameLogic.tetrominos.Tetromino;
import tetris.util.interfaces.BagQueueObserver;
import tetris.util.interfaces.PieceSubject;
import tetris.util.interfaces.IDrawable;
import tetris.util.interfaces.IUpdatable;

import javax.swing.*;
import java.awt.*;

public class GameManager implements IUpdatable, IDrawable, PieceSubject {
    private final BoardDrawer boardDrawer;
    private final PieceDrawer pieceDrawer;
    private final PieceController pieceController;
    private final ScoreManager scoreManager;
    public final Bag bag;
    public GameManager(BoardDrawer boardDrawer, PieceDrawer pieceDrawer, PieceController pieceController, ScoreManager scoreManager) {
        this.boardDrawer = boardDrawer;
        this.pieceDrawer = pieceDrawer;
        this.pieceController = pieceController;
        this.scoreManager = scoreManager;
        this.bag = new Bag();

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

        if(pieceController.isGameOver()){
            gameOver();
        }
    }

    private void gameOver() {
        String gameOverMessage = "GAME OVER!, Do you want to save your score?";
        int answer = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), gameOverMessage);

        if (answer == 0) {
            String inputUserMessage = "Insert your User Name";
            String userName = JOptionPane.showInputDialog(inputUserMessage);

            DBManager.insertInto(userName, 0);
        } else if (answer == 1) {

        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        boardDrawer.drawOccupiedSlots(g2d);
        pieceDrawer.draw(g2d);
        boardDrawer.drawGrid(g2d);
    }

    public BoardDrawer getBoardDrawer() {
        return this.boardDrawer;
    }
}
