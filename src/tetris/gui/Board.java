package tetris.gui;

import tetris.gameLogic.TetrisGrid;
import tetris.gameLogic.tetrominos.Bag;
import tetris.gameLogic.tetrominos.Tetromino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements KeyListener , Runnable{
    private static final int ROWS = 20;
    private static final int COLUMNS = 10;
    private static final int CELL_SIZE = 25;
    public final TetrisGrid GAME = new TetrisGrid(ROWS, COLUMNS);
    public final Bag pieceBag = new Bag();
    public Tetromino I = pieceBag.getCurrentPiece();
    PieceControl drawTest = new PieceControl(GAME);
    public Board() {
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawOccupiedSlots(g);
        drawTest.drawPiece(g, I);

        drawGrid(g);
    }
    private void drawBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, getWidth(), getHeight());
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.GRAY);
        int[][] grid = GAME.getBoard();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
    private void drawOccupiedSlots(Graphics g){
        int[][] grid = GAME.getBoard();
        Color slotColor;
        int colorIndex;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                colorIndex = GAME.getCell(i,j);
                slotColor = drawTest.getPieceColor(colorIndex);
                g.setColor(slotColor);
                g.fillRect((j) * CELL_SIZE, (i) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    private static int n = 1;
    @Override
    public void keyPressed(KeyEvent e) {
        GAME.printDebug();
        drawTest.repaint(this, I);

        if(e.getKeyCode() == KeyEvent.VK_S){
            I.move(0, 1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_D){
            I.move(1, 0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_A){
            I.move(-1, 0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_R){
            I.rotateClockWise();
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE){
            I = pieceBag.getNewPiece();
        }
        else if (e.getKeyCode() == KeyEvent.VK_ENTER){
            drawTest.placePiece(I, this);
            I = pieceBag.getNewPiece();
        }

        drawTest.repaint(this, I);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
