package cz.cvut.fel.pjv.model.Pieces;

import cz.cvut.fel.pjv.model.Board;
import cz.cvut.fel.pjv.model.Spot;

/**
 * Class representation of the bishop piece.
 */
public class Bishop extends Piece {

    /**
     * Create bishop piece
     *
     * @param white If is white, set to true.
     */
    public Bishop(boolean white) {
        super(white);
    }

    /**
     * Methode to validate if the move is possible.
     *
     * @param board Game board.
     * @param start
     * @param end
     * @return If move is possible, true.
     * @throws Exception
     */
    @Override
    public boolean canMove(Board board, Spot start, Spot end) throws Exception {

        // protect spot with same color piece
        if (isMyPieceInTheWay(end)) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        if (x == y) {
            // check no piece between start and end position
            if (isNotPiecesOnTheWayBishop(board, start, end)) {
                return true;
            }
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
        return "♝";
    }

    /**
     * Method to get a symbol of the piece for print on console board.
     *
     * @return
     */
    @Override
    public String toString() {
        if (this.isWhite()) {
            return BLACK + "♗";
        } else {
            return WHITE + "♗";
        }
    }

}
