package tetris.gameLogic;

public class TilePosition {
    // This class creates positions for the blocks that composes a Tetranomino, making it easier to manage.
    int row;
    int column;

    public TilePosition(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
