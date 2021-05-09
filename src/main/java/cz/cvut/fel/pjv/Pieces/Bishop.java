package cz.cvut.fel.pjv.Pieces;

import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

public class Bishop extends Piece {

    public Bishop(boolean white) {
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

        if (x == y) {
            // check no piece between start and end position
            if (isNotPiecesOnTheWay(board, start, end)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check bishop way.
     * @param board
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    private boolean isNotPiecesOnTheWay(Board board, Spot start, Spot end) throws Exception {
        // terminate arrow
        int x = start.getX() - end.getX();
        int y = start.getY() - end.getY();

        int i, j;

        // upper left
        if (x > 0 && y > 0) {
            i = start.getX() - 1;
            j = start.getY() - 1;
            while (isOnTheBoard(i, j) && board.getBox(i, j).getPiece() == null) {
                i--;
                j--;

                if (i == end.getX() && j == end.getY()) {
                    return true;
                }
            }
        }
        // down left
        if (x < 0 && y > 0) {
            i = start.getX() + 1;
            j = start.getY() - 1;
            while (isOnTheBoard(i, j) && board.getBox(i, j).getPiece() == null) {
                i++;
                j--;

                if (i == end.getX() && j == end.getY()) {
                    return true;
                }
            }
        }
        // up right
        if (x > 0 && y < 0) {
            i = start.getX() - 1;
            j = start.getY() + 1;
            while (isOnTheBoard(i, j) && board.getBox(i, j).getPiece() == null) {
                i--;
                j++;
                if (i == end.getX() && j == end.getY()) {
                    return true;
                }
            }
        }
        // down right
        if (x < 0 && y < 0) {
            i = start.getX() + 1;
            j = start.getY() + 1;
            while (isOnTheBoard(i, j) && board.getBox(i, j).getPiece() == null) {
                i++;
                j++;
                if (i == end.getX() && j == end.getY()) {
                    return true;
                }
            }
        }
        System.err.println("The way is not clear!");
        return false;
    }

    @Override
    public String toString() {
        if (this.isWhite()) {
            return BLACK + "♗";
        } else {
            return WHITE + "♗";
        }
    }

}
