package cz.cvut.fel.pjv.Piece;

import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

public class King extends Piece {
    private boolean castlingDone;

    public King(boolean white) {
        super(white);
        this.castlingDone = false;
    }

    public boolean isCastlingDone() {
        return castlingDone;
    }

    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // protect spot with same color piece
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        return false;
    }
    @Override
    public String toString() {
        if(this.isWhite() == true){
            return BLACK + "K";
        } else {
            return WHITE + "K";
        }
    }
}
