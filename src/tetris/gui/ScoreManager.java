package tetris.gui;

import tetris.gameLogic.Score;
import tetris.gameLogic.TetrisGrid;
import tetris.util.interfaces.IUpdatable;

public class ScoreManager implements IUpdatable {
    protected final Score score;
    private final TetrisGrid grid;

    public ScoreManager(Score score, TetrisGrid grid){
        this.score = score;
        this.grid = grid;
    }

    @Override
    public void update() {
        checkScore();
    }

    private void checkScore() {
        int affectedRows = grid.clearAndMoveAllRows();
        if(affectedRows > 0) {
            score.scoreUp(affectedRows);
            System.out.println("Current score: "  + getCurrentScore());
        }
    }
    private int getCurrentScore(){
        return score.getScore();
    }
}
