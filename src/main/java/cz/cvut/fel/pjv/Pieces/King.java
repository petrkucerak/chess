package cz.cvut.fel.pjv.Pieces;

import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

public class King extends Piece {
    private boolean castlingDone;
    private boolean wasMoved;

    public King(boolean white) {
        super(white);
        this.castlingDone = false;
        this.wasMoved = false; // for check is possible do castling
    }
    // ToDo: Castling
    public boolean isCastlingDone() {
        return castlingDone;
    }

    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    public void setWasMoved(boolean wasMoved) {
        this.wasMoved = wasMoved;
    }

    public boolean isWasMoved() {
        return wasMoved;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // protect spot with same color piece
        if(isMyPieceInTheWay(end)){
            return false;
        }

        return false;
    }
    @Override
    public String toString() {
        if(this.isWhite()){
            return BLACK + "♔";
        } else {
            return WHITE + "♔";
        }
    }
}
