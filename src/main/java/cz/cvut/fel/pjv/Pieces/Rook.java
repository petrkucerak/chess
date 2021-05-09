package cz.cvut.fel.pjv.Pieces;

import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

public class Rook extends Piece {
    private boolean castlingDone;
    private boolean wasMoved;

    public Rook(boolean white) {
        super(white);
        this.castlingDone = false;
        this.wasMoved = false; // for check is possible do castling
    }

    // ToDo: Castling


    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    public void setWasMoved(boolean wasMoved) {
        this.wasMoved = wasMoved;
    }

    public boolean isCastlingDone() {
        return castlingDone;
    }

    public boolean isWasMoved() {
        return wasMoved;
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
