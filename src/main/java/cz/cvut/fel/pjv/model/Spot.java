package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.model.Pieces.Piece;

import java.io.Serializable;

/**
 * Represent a cell on the chess board
 */
public class Spot implements Serializable {
    private Piece piece;
    private int x;
    private int y;

    /**
     * Create object of spot
     *
     * @param piece
     * @param x
     * @param y
     */
    public Spot(Piece piece, int x, int y) {
        this.piece = piece;
        this.x = x;
        this.y = y;
    }
    public Spot(Spot spot){
        this.piece = spot.piece;
        this.x = spot.x;
        this.y = spot.y;
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

    /**
     * If the spot is empty, return true
     *
     * @return
     */
    public boolean isSpotNull() {
        if (this.piece == null) {
            return true;
        }
        return false;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
