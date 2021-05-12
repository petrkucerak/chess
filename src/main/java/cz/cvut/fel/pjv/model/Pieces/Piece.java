package cz.cvut.fel.pjv.model.Pieces;


import cz.cvut.fel.pjv.model.Board;
import cz.cvut.fel.pjv.model.Spot;

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
     *
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

    /**
     * Check bishop way.
     *
     * @param board
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    boolean isNotPiecesOnTheWayBishop(Board board, Spot start, Spot end) throws Exception {
        // terminate arrow
        int x = start.getX() - end.getX();
        int y = start.getY() - end.getY();

        int i, j;

        // upper left
        if (x > 0 && y > 0) {
            i = start.getX();
            j = start.getY();
            do {
                i--;
                j--;
                if (i == end.getX() && j == end.getY()) {
                    return true;
                }
            } while (isOnTheBoard(i, j) && board.getBox(i, j).getPiece() == null);
        }
        // down left
        if (x < 0 && y > 0) {
            i = start.getX();
            j = start.getY();
            do {
                i++;
                j--;
                if (i == end.getX() && j == end.getY()) {
                    return true;
                }
            } while (isOnTheBoard(i, j) && board.getBox(i, j).getPiece() == null);
        }
        // up right
        if (x > 0 && y < 0) {
            i = start.getX();
            j = start.getY();
            do {
                i--;
                j++;
                if (i == end.getX() && j == end.getY()) {
                    return true;
                }
            } while (isOnTheBoard(i, j) && board.getBox(i, j).getPiece() == null);
        }
        // down right
        if (x < 0 && y < 0) {
            i = start.getX();
            j = start.getY();
            do {
                i++;
                j++;
                if (i == end.getX() && j == end.getY()) {
                    return true;
                }
            } while (isOnTheBoard(i, j) && board.getBox(i, j).getPiece() == null);
        }
        System.err.println("The way is not clear!");
        return false;
    }

    /**
     * Check the rook way.
     *
     * @param board
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    boolean isNotPiecesOnTheWayRook(Board board, Spot start, Spot end) throws Exception {
        // terminate arrow
        int x = start.getX() - end.getX();
        int y = start.getY() - end.getY();

        int i;

        // down
        if (x < 0) {
            i = start.getX();
            do {
                i++;
                if (i == end.getX()) {
                    return true;
                }
            }
            while (isOnTheBoard(i, start.getY()) && board.getBox(i, start.getY()).getPiece() == null);
        }
        // up
        if (x > 0) {
            i = start.getX();
            do {
                i--;
                if (i == end.getX()) {
                    return true;
                }
            }
            while (isOnTheBoard(i, start.getY()) && board.getBox(i, start.getY()).getPiece() == null);
        }
        // right
        if (y < 0) {
            i = start.getY();
            do {
                i++;
                if (i == end.getY()) {
                    return true;
                }
            }
            while (isOnTheBoard(start.getX(), i) && board.getBox(start.getX(), i).getPiece() == null);
        }
        // left
        if (y > 0) {
            i = start.getY();
            do {
                i--;
                if (i == end.getY()) {
                    return true;
                }
            }
            while (isOnTheBoard(start.getX(), i) && board.getBox(start.getX(), i).getPiece() == null);
        }
        System.err.println("The way is not clear!");
        return false;
    }

    public boolean isKingInDanger(Board board, Spot piecePosition, Boolean kingColor) throws Exception {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // check if position isn't null
                if (!board.getBox(i, j).isSpotNull()) {
                    // valid if on this position is King
                    // & color
                    Piece destPiece = board.getBox(i, j).getPiece();
                    if (destPiece instanceof King && kingColor == destPiece.isWhite()) {
                        if (canMove(board, piecePosition, board.getBox(i, j))) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public abstract String getPieceSymbol();
}
