package tetris.gameLogic;

public class Score {
    // this class implements the Original N******o scoring system.
    private static int SCORE_ONE_LINE = 40;
    private static int SCORE_TWO_LINES = 100;
    private static int SCORE_THREE_LINES = 300;
    private static int SCORE_FOUR_LINES = 1200;
    private int score;
    private int level;
    public Score(int level){
        this.level = level;
        score = 0;
    }
    public void scoreUp(int linesCleared){
        int scoreBase;
        switch (linesCleared){
            case 1 -> scoreBase = SCORE_ONE_LINE;
            case 2-> scoreBase = SCORE_TWO_LINES;
            case 3 -> scoreBase = SCORE_THREE_LINES;
            case 4 -> scoreBase = SCORE_FOUR_LINES;
            default -> scoreBase = -1;
        }

        this.score += (scoreBase * (level));
    }
    public void resetScore(){
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
