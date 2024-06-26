package tetris.gameLogic.tetrominos;

public abstract class Tetromino {
    protected int pieceID;
    protected int startingX, startingY;
    protected int[][] currentShape;
    protected int[][][] rotations; // AKA kicktable.
    protected int rotationIndex;
    protected int posX, posY;
    protected boolean isColliding;

    public Tetromino(int startingX, int startingY, int pieceID) {
        this.pieceID = pieceID;
        this.startingX = startingX;
        this.startingY = startingY;

        //When the piece is created it is moved to the starting position.
        posX = this.startingX;
        posY = this.startingY;
    }

    public boolean isColliding() {
        return isColliding;
    }

    public void setColliding(boolean colliding) {
        isColliding = colliding;
    }

    public int[][] getNextRotation() {
        int localRotation = rotationIndex + 1;
        return rotations[localRotation];
    }

    public void rotateClockWise() {
        rotationIndex = (rotationIndex + 1) % 4;
        currentShape = rotations[rotationIndex];
    }

    public void rotateCounterClockWise() {
        if (rotationIndex == 0) {
            rotationIndex = 3;
        } else {
            rotationIndex--;
        }
        currentShape = rotations[rotationIndex];
    }

    public void move(int x, int y) {
        this.posX += x;
        this.posY += y;
    }

    public void undoLastMove(int x, int y) {
        this.posX -= x;
        this.posY -= y;
    }

    public int[][] getCurrentShape() {
        return this.currentShape;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void reset() {
        this.posX = startingX;
        this.posY = startingY;
        this.currentShape = rotations[0];
    }

    public int getPieceID() {
        return pieceID;
    }

    public void debugPlaceAtZero() {
        this.startingX = 0;
        this.startingY = 0;

        this.posX = 0;
        this.posY = 0;
    }
}
