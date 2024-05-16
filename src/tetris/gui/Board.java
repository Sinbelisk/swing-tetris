package tetris.gui;

import tetris.gameLogic.TetrisGrid;
import tetris.gameLogic.tetrominos.IPiece;
import tetris.gameLogic.tetrominos.Tetromino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements KeyListener {
    // this whole class is just for testing the paintin of the grid and pieces.
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
        frame.addKeyListener(board);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(board);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        board.pintar(0);
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
                    g.setColor(Color.BLUE);
                }
                else if (board[row][col] == 2) g.setColor(Color.red);
                else if(board[row][col] == 3) g.setColor(Color.YELLOW);
                else {
                    g.setColor(Color.GREEN);
                }
                g.fillRect(col * SIZE, row * SIZE, SIZE, SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(col * SIZE, row * SIZE, SIZE, SIZE);
            }
        }
    }
    public void pintar(int n){
        Tetromino iPiece = new IPiece();
        int[][] shape = iPiece.getCurrentShape();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0){
                    game.setCell(i+iPiece.getPosX()+n, j+iPiece.getPosY(), iPiece.getPieceID());
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private static int n = 1;
    @Override
    public void keyPressed(KeyEvent e) {
        n++;
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            pintar(n);
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
