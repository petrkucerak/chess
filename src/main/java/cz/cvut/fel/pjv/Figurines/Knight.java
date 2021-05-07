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
        // set possible moves
        if (isValidPosition(x - 2, y - 1)) possibleMoves[y - 1][x - 2] = true;
        if (isValidPosition(x - 2, y + 1)) possibleMoves[y + 1][x - 2] = true;
        if (isValidPosition(x + 2, y - 1)) possibleMoves[y - 1][x + 2] = true;
        if (isValidPosition(x + 2, y + 1)) possibleMoves[y + 1][x + 2] = true;
        if (isValidPosition(x - 1, y - 2)) possibleMoves[y - 2][x - 1] = true;
        if (isValidPosition(x + 1, y - 2)) possibleMoves[y - 2][x + 1] = true;
        if (isValidPosition(x - 1, y + 2)) possibleMoves[y + 2][x - 1] = true;
        if (isValidPosition(x + 1, y + 2)) possibleMoves[y + 2][x + 1] = true;
        return possibleMoves;
    }
}
