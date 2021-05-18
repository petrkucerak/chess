package cz.cvut.fel.pjv.model.Pieces;

import cz.cvut.fel.pjv.model.Board;
import cz.cvut.fel.pjv.model.Spot;

/**
 * Class representation of the Knight piece.
 */
public class Knight extends Piece {

    /**
     * Create knight piece.
     *
     * @param white
     */
    public Knight(boolean white) {
        super(white);
    }

    /**
     * Methode to validate if the move is possible.
     *
     * @param board
     * @param start
     * @param end
     * @return
     */
    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // protect spot with same color piece
        if (isMyPieceInTheWay(end)) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        if (x * y == 2) {
            return true;
        }
        return false;
    }

    /**
     * Method to get a symbol of the piece for print on board.
     *
     * @return
     */
    @Override
    public String getPieceSymbol() {
        return "♞";
    }

    /**
     * Method to get a symbol of the piece for print on console board.
     *
     * @return
     */
    @Override
    public String toString() {
        if (this.isWhite()) {
            return BLACK + "♘";
        } else {
            return WHITE + "♘";
        }
    }


}
