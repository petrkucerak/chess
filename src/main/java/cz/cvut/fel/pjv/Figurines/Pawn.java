package cz.cvut.fel.pjv.Figurines;

import cz.cvut.fel.pjv.Chessboard;

/**
 * Class for the Pawn figurine
 */
public class Pawn extends Figurine {

    public Pawn(char color) {
        super(color, "Pawn");
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
