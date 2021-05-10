package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.Pieces.Piece;
import cz.cvut.fel.pjv.Player.Player;

/**
 * Class definition move of player
 */
public class Move {
    private Player player;
    private Spot start;
    private Spot end;
    private Piece pieceMoved;
    private Piece pieceKilled;

    // specific move
    private boolean shortCastlingMove;
    private boolean longCastlingMove;
    private boolean elPassant;

    /**
     * Create move object
     * @param player
     * @param start spot
     * @param end spot
     */
    public Move(Player player, Spot start, Spot end) {
        this.player = player;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();

        this.shortCastlingMove = false;
        this.longCastlingMove = false;
        this.elPassant = false;
    }

    public void setShortCastlingMove(boolean shortCastlingMove) {
        this.shortCastlingMove = shortCastlingMove;
    }

    public void setLongCastlingMove(boolean longCastlingMove) {
        this.longCastlingMove = longCastlingMove;
    }

    public boolean isShortCastlingMove() {
        return shortCastlingMove;
    }

    public boolean isLongCastlingMove() {
        return longCastlingMove;
    }

    public void setElPassant(boolean elPassant) {
        this.elPassant = elPassant;
    }

    public boolean isElPassant() {
        return elPassant;
    }

    public Player getPlayer() {
        return player;
    }

    public Spot getStart() {
        return start;
    }

    public Spot getEnd() {
        return end;
    }

    public Piece getPieceMoved() {
        return pieceMoved;
    }

    public Piece getPieceKilled() {
        return pieceKilled;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setStart(Spot start) {
        this.start = start;
    }

    public void setEnd(Spot end) {
        this.end = end;
    }

    public void setPieceMoved(Piece pieceMoved) {
        this.pieceMoved = pieceMoved;
    }

    public void setPieceKilled(Piece pieceKilled) {
        this.pieceKilled = pieceKilled;
    }
}
