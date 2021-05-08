package cz.cvut.fel.pjv.Piece;


import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

public abstract class Piece {
    private boolean killed = false;
    private boolean white = false;

    public static final String BLACK   = "\u001B[34m";
    public static final String WHITE = "\u001B[33m";
    public static final String GREEN  = "\u001B[32m";
    public static final String RESET  = "\u001B[0m";
    public static final String BACK  = "\u001B[30m";


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

    public abstract boolean canMove(Board board, Spot start, Spot end);

    /**
     * Protect spot with same color piece
     * @param end
     * @return
     */
    boolean myPieceInTheWay(Spot end){

        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            System.err.println("This spot is occupied by your piece!");
            return true;
        }
        return false;
    }
}
