package cz.cvut.fel.pjv.Figurines;

/**
 * Class for the Queen figurine
 */
public class Queen extends Figurine {

    public Queen(char color) {
        super(color, "Queen");
    }

    @Override
    public boolean[][] possibleMoves(int x, int y) {
        // create new board
        boolean[][] possibleMoves = new boolean[8][8];
        // set all position as empty
        setAllPositionFalse(possibleMoves);
        return possibleMoves;
    }
}
