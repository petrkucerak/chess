package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.Pieces.Piece;

/**
 * Represent a cell on the chess board
 */
public class Spot {
    private Piece piece;
    private int x;
    private int y;

    /**
     * Create object of spot
     * @param piece
     * @param x
     * @param y
     */
    public Spot(Piece piece, int x, int y) {
        this.piece = piece;
        this.x = x;
        this.y = y;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
