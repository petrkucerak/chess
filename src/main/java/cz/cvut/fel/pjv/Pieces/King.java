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

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        // standard King move
        if ((x == 1 && y == 1) || (x == 0 && y == 1) || (x == 1 && y == 0)){
            return true;
        }

        // check conditions for Castling
        if(wasMoved == false && castlingDone == false){
            if(longCastling(board, start, end)){
                return true;
            }
            if(shortCastling(board, start, end)){
                return true;
            }
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

    private boolean longCastling(Board board, Spot start, Spot end){
        // ToDo: implements algorithm
        return false;
    }

    private boolean shortCastling(Board board, Spot start, Spot end){
        // ToDo: implements algorithm
        return false;
    }
}
