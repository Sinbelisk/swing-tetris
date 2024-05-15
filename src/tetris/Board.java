package tetris;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Board extends JPanel {
    private static final int SIZE = 25;
    public final Tetris game = new Tetris();
    public Board() {
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Board board = new Board();
        frame.setSize(25*10, 25*25);
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
                else if(board[row][col] == 3) g.setColor(Color.YELLOW);z
                else {
                    g.setColor(Color.BLUE);
                }
                g.fillRect(col * SIZE, row * SIZE, SIZE, SIZE);
                g.setColor(Color.BLACK);
            }
        }
    }
    public void pintar(){
        int n = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if(j <= 2 || j>=7) n = 2;
                else n= 3;
                game.setCell(i, j, n);
                repaint();
            }
        }
    }
}
