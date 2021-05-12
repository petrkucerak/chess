package cz.cvut.fel.pjv.model.Pieces;

import cz.cvut.fel.pjv.model.Board;
import cz.cvut.fel.pjv.model.Spot;

public class Knight extends Piece {

    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // protect spot with same color piece
        if(isMyPieceInTheWay(end)){
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
    public String getPieceSymbol() {
        return "♞";
    }

    @Override
    public String toString() {
        if (this.isWhite()) {
            return BLACK + "♘";
        } else {
            return WHITE + "♘";
        }
    }


}
