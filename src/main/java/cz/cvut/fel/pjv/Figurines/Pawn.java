package cz.cvut.fel.pjv.Figurines;

import cz.cvut.fel.pjv.Chessboard;

/**
 * Class for the Pawn figurine
 */
public class Pawn extends Figurine {
    public Pawn(char color) {
        super(color, 4);
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
    private boolean elPassant(int x, int y, Chessboard chessboard){
        return false;
    }

    /**
     * Change the pawn for another figurine
     */
    public void promotion(){}
}
