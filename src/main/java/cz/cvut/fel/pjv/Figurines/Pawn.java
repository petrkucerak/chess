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
        int jump;
        // set move direction
        if (this.getColor() == 'W') {
            jump = -1;
            if(y == 6) possibleMoves[y - 2][x] = true;
        }
        else {
            jump = 1;
            if(y == 1) possibleMoves[y + 2][x] = true;
        }

        if (y != 7) possibleMoves[y + jump][x] = true;
        return possibleMoves;
    }
}
