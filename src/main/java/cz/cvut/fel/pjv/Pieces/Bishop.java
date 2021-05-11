package cz.cvut.fel.pjv.Pieces;

import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

public class Bishop extends Piece {

    public Bishop(boolean white) {
        super(white);
    }

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

    @Override
    public String toString() {
        if (this.isWhite()) {
            return BLACK + "♗";
        } else {
            return WHITE + "♗";
        }
    }

}
