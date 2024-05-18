package tetris.gui;

import tetris.Updatable;
import tetris.gameLogic.TetrisGrid;

import java.awt.*;

public class BoardDrawer implements Updatable {
    private static final int CELL_SIZE = 25;
    public final TetrisGrid GAME_GRID;
    public BoardDrawer(TetrisGrid gameGrid) {
        this.GAME_GRID = gameGrid;
    }
    @Override
    public void update() {
        GAME_GRID.clearAndMoveAllRows();
    }
    public void drawGrid(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        int[][] grid = GAME_GRID.getBoard();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                g2d.setStroke(new BasicStroke(3));
                g2d.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
    public void drawOccupiedSlots(Graphics g){
        int[][] grid = GAME_GRID.getBoard();
        Color slotColor;
        int colorIndex;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                colorIndex = GAME_GRID.getCell(i,j);
                slotColor = GameManager.getPieceColor(colorIndex);
                g.setColor(slotColor);
                g.fillRect((j) * CELL_SIZE, (i) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
}
