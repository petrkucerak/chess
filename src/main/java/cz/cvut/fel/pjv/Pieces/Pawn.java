package cz.cvut.fel.pjv.Pieces;

import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Move;
import cz.cvut.fel.pjv.Spot;

public class Pawn extends Piece {

    private boolean wasLastSuperJump;

    public Pawn(boolean white) {
        super(white);
        this.wasLastSuperJump = false;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end, Move move) throws Exception {

        // protect spot with same color piece
        if (isMyPieceInTheWay(end)) {
            return false;
        }

        int x;
        // check move direction
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

            // first pawn move (super jump)
            if (x == 2 && y == 0) {
                // check if spot on the way is empty & position is initialization
                if (start.getX() == 1 && board.getBox(2, start.getY()).getPiece() == null) {
                    this.wasLastSuperJump = true;
                    return true;
                }
                if (start.getX() == 6 && board.getBox(5, start.getY()).getPiece() == null) {
                    this.wasLastSuperJump = true;
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
        // Implementation 'El passant'
        // spot shut be empty
        if (end.getPiece() == null) {
            // check if move is diagonal
            if (x == 1 && y == 1) {
                // check if last move was Pawn super jump
                if (board.getBox(end.getX() - 1, end.getY()).getPiece() instanceof Pawn) {
                    System.out.println("Player use 'El passant' move");

                    // kill opponent piece
                    Piece destPiece = board.getBox(end.getX() - 1, end.getY()).getPiece();

                    destPiece.setKilled(true);
                    // setPieceKilled(destPiece);

                    return true;
                }
            }
        }

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
