package cz.cvut.fel.pjv.Pieces;

import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Move;
import cz.cvut.fel.pjv.Spot;

public class King extends Piece {
    private boolean castlingDone;

    public King(boolean white) {
        super(white);
        this.castlingDone = false;
    }
    // ToDo: Castling
    public boolean isCastlingDone() {
        return castlingDone;
    }

    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end, Move move) {

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
