package cz.cvut.fel.pjv.Pieces;

import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

public class King extends Piece {
    private boolean moved;
    private boolean isLongCastlingMove;
    private boolean isShortCastlingMove;

    public King(boolean white) {
        super(white);
        this.moved = false; // for check is possible do castling
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

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean isMoved() {
        return moved;
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
            moved = true;
            return true;
        }

        // check conditions for Castling
        if (!moved) {
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
                // check the rook position and move status
                if (board.getBox(start.getX(), start.getY() - 4).getPiece() instanceof Rook) {
                    System.err.println("Rook was already moving!");
                    return false;
                }
                moved = true;
                isLongCastlingMove = true;

                System.out.println("Long castling!");
                return true;
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
                // check the rook position and move status
                if (board.getBox(start.getX(), start.getY() + 3).getPiece() instanceof Rook) {
                    if (((Rook) board.getBox(start.getX(), start.getY() + 3).getPiece()).isMoved()) {
                        System.err.println("Rook was already moving!");
                        return false;
                    }
                    moved = true;
                    isShortCastlingMove = true;

                    System.out.println("Short castling!");
                    return true;
                }
            }
        }
        return false;
    }
}
