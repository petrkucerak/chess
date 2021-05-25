package cz.cvut.fel.pjv.model.Pieces;

import cz.cvut.fel.pjv.MainApp;
import cz.cvut.fel.pjv.model.Board;
import cz.cvut.fel.pjv.model.Game;
import cz.cvut.fel.pjv.model.Spot;

import java.util.logging.Logger;

/**
 * Class representation of the pawn piece.
 */
public class Pawn extends Piece {

    private boolean wasLastSuperJump;
    private boolean didElPassant;
    private boolean isPromotion;

    private static final Logger LOG = Logger.getLogger(Game.class.getName());

    /**
     * Create pawn piece
     *
     * @param white
     */
    public Pawn(boolean white) {
        super(white);
        this.wasLastSuperJump = false;
        this.didElPassant = false;
        this.isPromotion = false;
    }

    public void setDidElPassant(boolean didElPassant) {
        this.didElPassant = didElPassant;
    }

    public boolean isDidElPassant() {
        return didElPassant;
    }

    public void setPromotion(boolean promotion) {
        isPromotion = promotion;
    }

    public boolean isPromotion() {
        return isPromotion;
    }

    public boolean isWasLastSuperJump() {
        return wasLastSuperJump;
    }

    public void setWasLastSuperJump(boolean wasLastSuperJump) {
        this.wasLastSuperJump = wasLastSuperJump;
    }

    /**
     * Methode to validate if the move is possible.
     *
     * @param board
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    @Override
    public boolean canMove(Board board, Spot start, Spot end) throws Exception {

        // protect spot with same color piece
        if (isMyPieceInTheWay(end)) {
            return false;
        }

        // Implementation 'Promotion'
        // check situation for activate this mode
        if (!board.isActiveCheckingIsKingInDanger()) {
            if (end.getX() == 0 || end.getX() == 7) {
                // LOG.info("Piece promotion!");
                isPromotion = true;
            }
        }

        int x;
        // check move direction
        if (!start.getPiece().isWhite()) {
            x = end.getX() - start.getX();
        } else {
            x = start.getX() - end.getX();
        }
        int y = Math.abs(start.getY() - end.getY());

        // protect spot with opposite piece
        if (isColorPiecesSame(start, end) || end.getPiece() == null) {
            // classic pawn move
            if (x == 1 && y == 0) {
                this.wasLastSuperJump = false;
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
                this.wasLastSuperJump = false;
                return true;
            }
        }

        // Implementation 'El passant'
        // spot shut be empty
        if (end.getPiece() == null) {
            // check if move is diagonal
            x = Math.abs(start.getX() - end.getX());
            y = Math.abs(start.getY() - end.getY());
            if (x == 1 && y == 1) {
                // check color
                int colorRegulator = colorRegulator(start.getPiece());
                // check if last move was Pawn super jump
                Piece victim = board.getBox(end.getX() + colorRegulator, end.getY()).getPiece();
                if (victim instanceof Pawn && ((Pawn) victim).isWasLastSuperJump()) {
                    System.out.println("Player use 'El passant' move");
                    // kill opponent piece in game file
                    this.didElPassant = true;
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Method to get a symbol of the piece for print on board.
     *
     * @return
     */
    @Override
    public String getPieceSymbol() {
        return "♟";
    }

    /**
     * Internal method to determine board orientation.
     *
     * @param piece
     * @return
     */
    public static int colorRegulator(Piece piece) {
        if (piece.isWhite()) {
            return 1;
        }
        return -1;
    }

    /**
     * Method to get a symbol of the piece for print on console board.
     *
     * @return
     */
    @Override
    public String toString() {
        if (this.isWhite()) {
            return BLACK + "♙";
        } else {
            return WHITE + "♙";
        }
    }


}
