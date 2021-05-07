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
        // set possible moves
        if (x != 0) possibleMoves[y][x - 1] = true;
        if (y != 0) possibleMoves[y - 1][x] = true;
        if (x != possibleMoves.length - 1) possibleMoves[y][x + 1] = true;
        if (y != possibleMoves.length - 1) possibleMoves[y + 1][x] = true;
        if (x != 0 && y != 0) possibleMoves[y - 1][x - 1] = true;
        if (x != 0 && y != possibleMoves.length - 1) possibleMoves[y + 1][x - 1] = true;
        if (x != possibleMoves.length - 1 && y != 0) possibleMoves[y - 1][x + 1] = true;
        if (x != possibleMoves.length - 1 && y != possibleMoves.length - 1) possibleMoves[y + 1][x + 1] = true;
        return possibleMoves;
    }
}
