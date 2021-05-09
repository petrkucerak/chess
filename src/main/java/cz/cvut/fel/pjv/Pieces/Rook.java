package cz.cvut.fel.pjv.Pieces;

import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

public class Rook extends Piece {

    public Rook(boolean white) {
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

        if ((x == 0 && y > 0) || (x > 0 && y == 0)) {
            // check no piece between start and end position
            if (isNotPiecesOnTheWay(board, start, end)) {
                return true;
            }
        }

        return false;
    }

    private boolean isNotPiecesOnTheWay(Board board, Spot start, Spot end) throws Exception {
        // terminate arrow
        int x = start.getX() - end.getX();
        int y = start.getY() - end.getY();

        int i;

        // down
        if (x < 0) {
            i = start.getX();
            do {
                i++;
                if (i == end.getX()) {
                    return true;
                }
            }
            while (isOnTheBoard(i, start.getY()) && board.getBox(i, start.getY()).getPiece() == null);
        }
        // up
        if (x > 0) {
            i = start.getX();
            do {
                i--;
                if (i == end.getX()) {
                    return true;
                }
            }
            while (isOnTheBoard(i, start.getY()) && board.getBox(i, start.getY()).getPiece() == null);
        }
        // right
        if (y < 0) {
            i = start.getY();
            do {
                i++;
                if (i == end.getY()) {
                    return true;
                }
            }
            while (isOnTheBoard(start.getX(), i) && board.getBox(start.getX(), i).getPiece() == null);
        }
        // left
        if (y > 0) {
            i = start.getY();
            do {
                i--;
                if (i == end.getY()) {
                    return true;
                }
            }
            while (isOnTheBoard(start.getX(), i) && board.getBox(start.getX(), i).getPiece() == null);
        }
        System.err.println("The way is not clear!");
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
