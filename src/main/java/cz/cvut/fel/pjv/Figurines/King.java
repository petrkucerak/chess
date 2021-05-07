package cz.cvut.fel.pjv.Figurines;

import cz.cvut.fel.pjv.Chessboard;

/**
 * Class for the king figurine
 */
public class King extends Figurine {

    public King(char color) {
        super(color, "King");
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
