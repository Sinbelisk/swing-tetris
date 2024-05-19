package tetris.gui;

import tetris.Drawable;
import tetris.Updatable;
import tetris.gameLogic.TetrisGrid;
import tetris.gameLogic.Timer;
import tetris.gameLogic.tetrominos.Bag;

import java.awt.*;

public class GameManager implements Updatable, Drawable {
    public static final int ROWS = 20;
    public static final int COLUMNS = 10;
    public static final int BASE_MOVE_DELAY = 150;
    private final Bag BAG = new Bag();
    private final TetrisGrid GRID = new TetrisGrid(ROWS, COLUMNS);
    private final BoardDrawer BOARD_DRAWER = new BoardDrawer(GRID);
    private final PieceDrawer PIECE_DRAWER = new PieceDrawer(GRID, BAG, new Timer(BASE_MOVE_DELAY));

    public GameManager(){

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
        BOARD_DRAWER.update();
        PIECE_DRAWER.update();
    }

    @Override
    public void draw(Graphics2D g2d) {
        BOARD_DRAWER.drawOccupiedSlots(g2d);
        PIECE_DRAWER.draw(g2d);

        BOARD_DRAWER.drawGrid(g2d);
    }
}
