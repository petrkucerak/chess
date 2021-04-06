package cz.cvut.fel.pjv.Figurines;

import cz.cvut.fel.pjv.Chessboard;

/**
 * Class for the Rook figurine
 */

public class Rook extends Figurine {
    public Rook(char color) {
        super(color, 7);
    }

    @Override
    public int[][] getPossibleMoves() {
        return super.getPossibleMoves();
    }

    /**
     * @param x coordinates of the position
     * @param y coordinates of the position
     * @param chessboard info of board
     * @return true - if the move is possible or false - if it isn't possible
     */
    private boolean queenSideCastle(int x, int y, Chessboard chessboard){
        return false;
    }

    /**
     * @param x coordinates of the position
     * @param y coordinates of the position
     * @param chessboard info of board
     * @return true - if the move is possible or false - if it isn't possible
     */
    private boolean kingSideCastle(int x, int y, Chessboard chessboard){
        return false;
    }
}
