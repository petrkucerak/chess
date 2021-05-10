package cz.cvut.fel.pjv.Pieces;

import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

public class King extends Piece {
    private boolean wasMoved;
    private boolean isLongCastlingMove;
    private boolean isShortCastlingMove;

    public King(boolean white) {
        super(white);
        this.wasMoved = false; // for check is possible do castling
        this.isLongCastlingMove = false;
        this.isShortCastlingMove = false;
    }

    public void setLongCastlingMove(boolean longCastlingMove) {
        isLongCastlingMove = longCastlingMove;
    }

    public void setShortCastlingMove(boolean shortCastlingMove) {
        isShortCastlingMove = shortCastlingMove;
    }

    public boolean isLongCastlingMove() {
        return isLongCastlingMove;
    }

    public boolean isShortCastlingMove() {
        return isShortCastlingMove;
    }

    public void setWasMoved(boolean wasMoved) {
        this.wasMoved = wasMoved;
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

        // standard King move
        if ((x == 1 && y == 1) || (x == 0 && y == 1) || (x == 1 && y == 0)) {
            wasMoved = true;
            return true;
        }

        // check conditions for Castling
        if (!wasMoved) {
            if (longCastling(board, start, end)) {
                return true;
            }
            if (shortCastling(board, start, end)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        if (this.isWhite()) {
            return BLACK + "♔";
        } else {
            return WHITE + "♔";
        }
    }

    private boolean longCastling(Board board, Spot start, Spot end) throws Exception {
        // validate short castling
        int y = start.getY() - end.getY();
        if (Math.abs(y) == 2 && y > 0) {
            // check clear way
            for (int i = 1; i < 3; i++) {
                if (board.getBox(start.getX(), start.getY() - i).getPiece() != null) {
                    return false;
                }
                if(board.getBox(start.getX(), start.getY() - 4).getPiece() instanceof Rook) {
                    wasMoved = true;
                    isLongCastlingMove = true;

                    System.out.println("Long castling!");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean shortCastling(Board board, Spot start, Spot end) throws Exception {
        // validate short castling
        int y = start.getY() - end.getY();
        if (Math.abs(y) == 2 && y < 0) {
            // check clear way
            for (int i = 1; i < 2; i++) {
                if (board.getBox(start.getX(), start.getY() + i).getPiece() != null) {
                    return false;
                }
                if (board.getBox(start.getX(), start.getY() + 3).getPiece() instanceof Rook){
                    wasMoved = true;
                    isShortCastlingMove = true;

                    System.out.println("Short castling!");
                    return true;
                }
            }
        }
        return false;
    }
}
