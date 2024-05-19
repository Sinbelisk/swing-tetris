package tetris.gameLogic.tetrominos;

import java.util.ArrayList;
import java.util.Collections;

public class Bag {
    Tetromino currentPiece;
    ArrayList<Tetromino> pieces = new ArrayList<>();
    int bagIndex;
    public Bag(){
        bagIndex = 0;

        pieces.add(new IPiece());
        pieces.add(new LPiece());
        pieces.add(new ILPiece());
        pieces.add(new QPiece());
        pieces.add(new TPiece());
        pieces.add(new ZPiece());
        pieces.add(new SPiece());

        currentPiece = getNewPiece();
    }
    public void randomize(){
        Collections.shuffle(pieces);
    }

    public Tetromino getNewPiece(){
        if(bagIndex == 0) randomize();
        currentPiece = pieces.get(bagIndex);
        currentPiece.reset();

        bagIndex = (bagIndex +1) % pieces.size();

        return currentPiece;
    }

    public Tetromino getCurrentPiece() {
        return currentPiece;
    }
    public void debugPlaceAtZero(){
        this.currentPiece.startingX = 0;
        this.currentPiece.startingY = 0;
    }
}
