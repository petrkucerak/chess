package cz.cvut.fel.pjv.Piece;


import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

public abstract class Piece {
    private boolean killed = false;
    private boolean white = false;

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
}
