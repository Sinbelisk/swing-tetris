package tetris.gui;

import tetris.gameLogic.TetrisGrid;

import java.awt.*;

public class BoardDrawer {
    private static final int CELL_SIZE = 25;
    public final TetrisGrid grid;
    public BoardDrawer(TetrisGrid gameGrid) {
        this.grid = gameGrid;}
    public void drawGrid(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        int[][] grid = this.grid.getBoard();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                g2d.setStroke(new BasicStroke(3));
                g2d.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
    public void drawOccupiedSlots(Graphics g){
        int[][] grid = this.grid.getBoard();
        Color slotColor;
        int colorIndex;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                colorIndex = this.grid.getCell(i,j);
                slotColor = GameManager.getPieceColor(colorIndex);
                g.setColor(slotColor);
                g.fillRect((j) * CELL_SIZE, (i) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
}
