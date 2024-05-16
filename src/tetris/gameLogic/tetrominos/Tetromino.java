package tetris.gameLogic.tetrominos;
public abstract class Tetromino {
    protected int startingX;
    protected int startingY;
    protected int[][] currentShape;
    protected int[][][] rotations; // AKA kicktable.
    protected int posX;
    protected int posY;
    public Tetromino(int startingY, int startingX){
        this.startingX = startingX;
        this.startingY = startingY;

        //When the piece is created it is moved to the starting position.
        posX = this.startingX;
        posY = this.startingY;
    }

    public void move(int x, int y){
        this.posX += x;
        this.posY += y;
    }
}
