package tetris.tetranomino;

import tetris.TilePosition;

import java.util.ArrayList;

public abstract class Tetranomino {
    protected TilePosition startingPosition;
    protected TilePosition[][] Blocks;
    protected int rotationState;
    protected TilePosition currentPosition;

    public Tetranomino(int startingRow, int startingColumn){
        startingPosition = new TilePosition(startingRow, startingColumn);
        currentPosition = new TilePosition(startingPosition.getRow(), startingPosition.getColumn());
    }

    public void move(int rows, int columns){
        currentPosition.setRow(rows + currentPosition.getRow());
        currentPosition.setColumn(columns + currentPosition.getColumn());
    }

}
