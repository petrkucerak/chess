package cz.cvut.fel.pjv.Piece;

import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

public class Knight extends Piece {

    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // protect spot with same color piece
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        if (x * y == 2) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.isWhite() == true) {
            return BLACK + "N";
        } else {
            return WHITE + "N";
        }
    }


}
