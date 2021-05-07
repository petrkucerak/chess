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
        // set positive moves
        int tmp_y = y + 1;
        while (tmp_y < possibleMoves.length) {
            possibleMoves[tmp_y][x] = true;
            tmp_y++;
        }
        tmp_y = y - 1;
        while (tmp_y >= 0) {
            possibleMoves[tmp_y][x] = true;
            tmp_y--;
        }
        int tmp_x = x + 1;
        while (tmp_x < possibleMoves.length) {
            possibleMoves[y][tmp_x] = true;
            tmp_x++;
        }
        tmp_x = x - 1;
        while (tmp_x >= 0) {
            possibleMoves[y][tmp_x] = true;
            tmp_x--;
        }
        return possibleMoves;
    }
}
