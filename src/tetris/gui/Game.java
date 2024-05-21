package tetris.gui;

import tetris.gameLogic.Score;
import tetris.gameLogic.TetrisGrid;
import tetris.gameLogic.tetrominos.Bag;
import tetris.gui.events.KeyEvents.KeyHandler;
import tetris.gui.panel.GamePanel;
import tetris.util.interfaces.IUpdatable;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable, IUpdatable {
    private MainMenu menu;
    public static final int ROWS = 20;
    public static final int COLUMNS = 10;
    public static final int SIZE = 25;
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
    private volatile boolean running = false;
    private volatile boolean paused = false;
    public Game() {
        this.boardDrawer = new BoardDrawer(grid);
        this.pieceController = new PieceController(grid);
        this.pieceDrawer = new PieceDrawer();
        this.keyHandler = new KeyHandler(pieceController, this);

        this.gameManager = new GameManager(boardDrawer, pieceDrawer, pieceController, scoreManager);

        this.addKeyListener(keyHandler);
        setFocusable(true);
    }
    public void configureNextPieceBox(NextPieceBox nextPieceBox){
        gameManager.addQueueObserver(nextPieceBox);
        nextPieceBox.setCurrentPiece(gameManager.bag.getNextPiece());
    }
    @Override
    public void run() {
        while (gameLoop != null) {
            try {
                Thread.sleep(DRAW_INTERVAL);
                if (!paused){
                    update();
                    repaint();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void startGameLoop() {
        //start game
        if (gameLoop == null || !running) {
            gameLoop = new Thread(this);
            running = true;
            gameLoop.start();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPaused() {
        return paused;
    }
    public void stopGameLoop() {
        //Stop Game
        running = false;
        if (gameLoop != null) {
            try {
                gameLoop.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public void pauseGameLoop() {
        paused = true;
        menu.showPauseLayer();
    }

    public void resumeGameLoop() {
        paused = false;
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
