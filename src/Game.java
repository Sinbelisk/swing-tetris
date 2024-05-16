import tetris.gui.Board;

import javax.swing.*;

public class Game {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Board board = new Board();
        frame.addKeyListener(board);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(board);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
