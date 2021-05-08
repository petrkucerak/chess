package cz.cvut.fel.pjv.Pieces;

import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

public class Pawn extends Piece {

    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) throws Exception {

        // protect spot with same color piece
        if (isMyPieceInTheWay(end)) {
            return false;
        }

        int x;
        if (start.getPiece().isWhite()) {
            x = end.getX() - start.getX();
        } else {
            x = start.getX() - end.getX();
        }
        int y = Math.abs(start.getY() - end.getY());

        // protect spot with opposite piece
        if (isColorPiecesSame(start, end) || end.getPiece() == null) {
            // classic pawn move
            if (x == 1 && y == 0) {
                return true;
            }

            // first pawn move
            if (x == 2 && y == 0) {
                // check if spot on the way is empty & position is initialization
                if (start.getX() == 1 && board.getBox(2, start.getY()).getPiece() == null) {
                    return true;
                }
                if (start.getX() == 6 && board.getBox(5, start.getY()).getPiece() == null) {
                    return true;
                }
            }
        }
        // kill a opponent piece
        // if is opponent piece
        if (!isColorPiecesSame(start, end) && end.getPiece() != null) {
            if (x == 1 && y == 1) {
                return true;
            }
        }

        // ToDo add 'El passant'
        // ToDo add 'Promotion'

        return false;
    }

    @Override
    public String toString() {
        if (this.isWhite()) {
            return BLACK + "♙";
        } else {
            return WHITE + "♙";
        }
    }


}
