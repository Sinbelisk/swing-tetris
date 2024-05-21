package tetris.gui;

import tetris.gameLogic.Score;
import tetris.gameLogic.TetrisGrid;
import tetris.gui.events.KeyEvents.KeyHandler;
import tetris.util.interfaces.IUpdatable;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable, IUpdatable {
    public static final int ROWS = 20;
    public static final int COLUMNS = 10;
    public static final int DRAW_INTERVAL = 1000 / 60;
    private final TetrisGrid grid = new TetrisGrid(ROWS, COLUMNS);
    private final Score score = new Score(1);
    private final BoardDrawer boardDrawer;
    private final PieceDrawer pieceDrawer;
    private final ScoreManager scoreManager = new ScoreManager(score, grid);
    private final PieceController pieceController;
    private final KeyHandler keyHandler;
    private final GameManager gameManager;
    private Thread gameLoop;

    public Game() {
        this.boardDrawer = new BoardDrawer(grid);
        this.pieceController = new PieceController(grid);
        this.pieceDrawer = new PieceDrawer();
        this.keyHandler = new KeyHandler(pieceController);

        this.gameManager = new GameManager(boardDrawer, pieceDrawer, pieceController, scoreManager);

        this.addKeyListener(keyHandler);
        setFocusable(true);
    }

    @Override
    public void run() {
        while (gameLoop != null) {
            try {
                Thread.sleep(DRAW_INTERVAL);
                update();
                repaint();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void startGameLoop() {
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    public void stopGameLoop() {
        //Stop Game
    }

    @Override
    public void update() {
        requestFocus();
        gameManager.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        gameManager.draw(g2d);
    }
}
