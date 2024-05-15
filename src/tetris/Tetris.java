package tetris;

import java.util.Random;

public class Tetris {
    private int[][] board;
    private static final int ROWS = 20;
    private static final int COLUMNS = 10;
    public Tetris(){
        board = new int[ROWS][COLUMNS];
        initBoard();
    }
    private void initBoard(){
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = 0;
            }
        }
    }
    public int[][] getBoard(){
        return board;
    }
    public int getCell(int row, int column){
        return board[row][column];
    }
    public void setCell(int row, int column, int value){
        board[row][column] = value;
    }
}
