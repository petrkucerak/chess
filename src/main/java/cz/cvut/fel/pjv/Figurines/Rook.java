package cz.cvut.fel.pjv.Figurines;

import cz.cvut.fel.pjv.Chessboard;

/**
 * Class for the Rook figurine
 */

public class Rook extends Figurine {

    public Rook(char color) {
        super(color, "Rook");
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
