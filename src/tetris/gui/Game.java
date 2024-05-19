package tetris.gui;

import tetris.util.interfaces.Updatable;
import tetris.gui.events.KeyEvents.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable, Updatable {
    private static final int WIDTH = 265;
    private static final int HEIGHT = 535;
    public static final int DRAW_INTERVAL = 1000/60;
    private Thread gameLoop;
    private GameManager gameManager = new GameManager();

    public Game(){
        this.addKeyListener(new KeyHandler());
        setFocusable(true);
        requestFocus();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setFocusable(false);
        Game game = new Game();
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setFocusable(false);
        frame.setVisible(true);
        game.startGameLoop();
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

    public void startGameLoop(){
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    @Override
    public void update() {
        gameManager.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        gameManager.draw(g2d);
    }
}
