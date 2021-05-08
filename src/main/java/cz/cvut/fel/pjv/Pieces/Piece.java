package cz.cvut.fel.pjv.Pieces;


import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

/**
 * Abstract class for define pieces and support methods for piece
 */
public abstract class Piece {
    private boolean killed = false;
    private boolean white = false;

    public static final String BLACK = "\u001B[34m";
    public static final String WHITE = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String BACK = "\u001B[30m";

    /**
     * Create piece
     *
     * @param white
     */
    public Piece(boolean white) {
        this.white = white;
    }

    public boolean isKilled() {
        return killed;
    }

    public boolean isWhite() {
        return white;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public abstract boolean canMove(Board board, Spot start, Spot end) throws Exception;

    /**
     * Protect spot with same color piece
     *
     * @param end
     * @return
     */
    boolean isMyPieceInTheWay(Spot end) {

        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            System.err.println("This spot is occupied by your piece!");
            return true;
        }
        return false;
    }

    /**
     * Check if pieces on current position have the same color
     *
     * @param start
     * @param end
     * @return
     */
    boolean isColorPiecesSame(Spot start, Spot end) {
        if (end.getPiece() != null) {
            if (start.getPiece().isWhite() == end.getPiece().isWhite()) {
                System.err.println("Spot is occupied by another piece!");
                return true;
            }
        }
        return false;
    }

    /**
     * Is position on the chessboard
     * @param x
     * @param y
     * @return
     */
    boolean isOnTheBoard(int x, int y) {
        if (x >= 0 && y >= 0 && x < 8 && y < 8) {
            return true;
        }
        return false;
    }
}
