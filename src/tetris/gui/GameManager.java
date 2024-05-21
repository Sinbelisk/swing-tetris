package tetris.gui;

import tetris.gameLogic.tetrominos.Tetromino;
import tetris.util.interfaces.IDrawable;
import tetris.util.interfaces.IUpdatable;
import tetris.gameLogic.TetrisGrid;
import tetris.gameLogic.tetrominos.Bag;
import tetris.util.interfaces.Subject;

import java.awt.*;

public class GameManager implements IUpdatable, IDrawable, Subject {
    public static final int ROWS = 20;
    public static final int COLUMNS = 10;
    private final BoardDrawer boardDrawer;
    private Tetromino currentPiece;
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
    public void newPiece() {
        this.currentPiece = bag.getNewPiece();
        notifyObservers(currentPiece);
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
    @Override
    public void update() {
        boardDrawer.update();
        pieceController.update();

        if(pieceController.isPiecePlaced()){
            newPiece();
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        boardDrawer.drawOccupiedSlots(g2d);
        pieceDrawer.draw(g2d);
        boardDrawer.drawGrid(g2d);
    }
}
