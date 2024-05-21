package tetris.gui;

import tetris.gameLogic.tetrominos.Bag;
import tetris.gameLogic.tetrominos.Tetromino;
import tetris.util.interfaces.IDrawable;
import tetris.util.interfaces.IUpdatable;
import tetris.util.interfaces.Subject;

import java.awt.*;

public class GameManager implements IUpdatable, IDrawable, Subject {
    private final BoardDrawer boardDrawer;
    private final PieceDrawer pieceDrawer;
    private final PieceController pieceController;
    private final Bag bag = new Bag();

    public GameManager(BoardDrawer boardDrawer, PieceDrawer pieceDrawer, PieceController pieceController) {
        this.boardDrawer = boardDrawer;
        this.pieceDrawer = pieceDrawer;
        this.pieceController = pieceController;

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
        notifyObservers(currentPiece);
    }

    @Override
    public void update() {
        boardDrawer.update();


        if (pieceController.isPiecePlaced()) {
            pieceController.setPiecePlaced(false);
            newPiece();
        }

        pieceController.update();
    }

    @Override
    public void draw(Graphics2D g2d) {
        boardDrawer.drawOccupiedSlots(g2d);
        pieceDrawer.draw(g2d);
        boardDrawer.drawGrid(g2d);
    }
}
