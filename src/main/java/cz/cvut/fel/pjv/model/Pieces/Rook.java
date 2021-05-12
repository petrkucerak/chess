package cz.cvut.fel.pjv.model.Pieces;

import cz.cvut.fel.pjv.model.Board;
import cz.cvut.fel.pjv.model.Spot;

public class Rook extends Piece {
    private boolean moved;

    public Rook(boolean white) {
        super(white);
        this.moved = false; // for check is possible do castling
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }


    public boolean isMoved() {
        return moved;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) throws Exception {

        // protect spot with same color piece
        if (isMyPieceInTheWay(end)) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        if ((x == 0 && y > 0) || (x > 0 && y == 0)) {
            // check no piece between start and end position
            if (isNotPiecesOnTheWayRook(board, start, end)) {
                moved = true;
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        if (this.isWhite()) {
            return BLACK + "♖";
        } else {
            return WHITE + "♖";
        }
    }
}
