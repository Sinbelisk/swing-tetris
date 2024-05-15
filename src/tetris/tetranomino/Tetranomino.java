package tetris.tetranomino;

public abstract class Tetranomino {
    private int offsetX;
    private int offsetY;
    int[][] tetranomino;
    int color;

    public Tetranomino(boolean big){
        if (big) tetranomino = new int[4][4];
        else tetranomino = new int[3][3];
    }

    public int[][] getTetranomino(){
        return tetranomino;
    }

    public int getColorAt(int row, int column){
        return tetranomino[row][column];
    }

    protected void setColor(){
        for (int i = 0; i < tetranomino.length; i++) {
            for (int j = 0; j < tetranomino[i].length; j++) {
                tetranomino[i][j] = 0;
            }
        }
    }

    public int getColor() {
        return color;
    }
}
