package tetris.gameLogic;

public class TetrisGrid {
    private int[][] board;
    private int rows;
    private int columns;
    public TetrisGrid(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        board = new int[rows][columns];
        initBoard();
    }
    private void initBoard(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
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
    public boolean checkIfRowIsFull(int row){ // checks if a row is full
        for (int i = 0; i < columns; i++) {
            if(board[row][i] == 0) return false;
        }
        return true;
    }
    public boolean checkIfRowIsEmpty(int row){ // checks if a row of the board is empty
        for (int i = 0; i < columns; i++) {
            if(board[row][columns] > 0) return false;
        }
        return true;
    }
    public void clearRow(int row){
        for (int i = 0; i < columns; i++) {
            board[row][i] = 0;
        }
    }
    public void moveRowDown(int row, int numberOfRows){
        for (int i = 0; i < columns; i++) {
            board[row + numberOfRows][i] = board[row][columns];
        }
        clearRow(row);
    }

    public void clearAndMoveAllRows(){ // checks each row individually until a row is empty
        int clearedRows = 0;
        for (int i = rows -1; i >= 0; i--) {
            if(checkIfRowIsFull(i)){
                clearRow(i);
                clearedRows++;
            } else if (clearedRows > 0){
                moveRowDown(i, clearedRows);
            }
        }
    }
    public void printDebug(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(board[i][j] + "   ");
            }
            System.out.println();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

}
