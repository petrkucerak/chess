package cz.cvut.fel.pjv.Figurines;

/**
 * Class for the Knight figurine
 */
public class Knight extends Figurine {

    public Knight(char color) {
        super(color, "Knight");
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
