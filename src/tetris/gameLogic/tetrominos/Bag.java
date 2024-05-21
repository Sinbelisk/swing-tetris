package tetris.gameLogic.tetrominos;

import java.util.*;

public class Bag {
    private Tetromino currentPiece;
    private final ArrayList<Tetromino> pieces = new ArrayList<>();
    private final Queue<Tetromino> bagQueue = new LinkedList<>();
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
        if(bagIndex == 0) {
            randomize();
            resetQueue();
        }
        currentPiece = pieces.get(bagIndex);
        currentPiece.reset();

        bagIndex = (bagIndex +1) % pieces.size();

        return currentPiece;
    }

    private void resetQueue() {
        bagQueue.clear();
        bagQueue.addAll(pieces);
    }

    public void debugPlaceAtZero(){
        this.currentPiece.startingX = 0;
        this.currentPiece.startingY = 0;
    }

    public Queue<Tetromino> getBagQueue() {
        return bagQueue;
    }
}
