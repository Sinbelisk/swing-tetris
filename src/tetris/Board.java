package tetris;

import tetris.tetranomino.Tetranomino;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int ROWS = 20;
    private static final int COLUMNS = 10;
    private static final int SIZE = 25;
    public final TetrisGrid game = new TetrisGrid(ROWS, COLUMNS);
    public Board() {
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Board board = new Board();
        frame.setSize(WIDTH, HEIGHT);
        frame.add(board);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        board.pintar();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[][] board = game.getBoard();

        for (int row = 0; row < board.length; row++) {

            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    g.setColor(Color.WHITE);
                } else if (board[row][col] == 1) {
                    g.setColor(Color.GREEN);
                }
                else if (board[row][col] == 2) g.setColor(Color.red);
                else if(board[row][col] == 3) g.setColor(Color.YELLOW);
                else {
                    g.setColor(Color.BLUE);
                }
                g.fillRect(col * SIZE, row * SIZE, SIZE, SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(col * SIZE, row * SIZE, SIZE, SIZE);
            }
        }
    }
    public void pintar(){

    }
}
