package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.Pieces.King;
import cz.cvut.fel.pjv.Pieces.Pawn;
import cz.cvut.fel.pjv.Pieces.Piece;
import cz.cvut.fel.pjv.Player.Player;

import java.util.ArrayList;

import java.util.List;

/**
 * The main mechanism of game
 */
public class Game {
    private Player[] players;
    private Board board;
    private Player currentTurn;
    private GameStatus status;
    private int gameRound;
    private List<Move> movesPlayed;

    /**
     * Initialization game
     *
     * @param p1 player
     * @param p2 player
     */
    public void initGame(Player p1, Player p2) {
        this.players = new Player[2];
        players[0] = p1;
        players[1] = p2;

        this.gameRound = 1;

        this.board = new Board();

        if (p1.isWhiteSide()) {
            this.currentTurn = p1;
        } else {
            this.currentTurn = p2;
        }
        this.movesPlayed = new ArrayList<Move>();
        movesPlayed.clear();
    }

    public boolean isEnd() {
        return this.getStatus() != GameStatus.ACTIVE;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    /**
     * Do player move
     *
     * @param player
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return true - move is valid
     * @throws Exception
     */
    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) throws Exception {
        Spot startBox = board.getBox(startX, startY);
        Spot endBox = board.getBox(endX, endY);
        Move move = new Move(player, startBox, endBox);
        return this.makeMove(move, player);
    }

    /**
     * Process move on the chessboard
     *
     * @param move
     * @param player
     * @return
     * @throws Exception
     */
    private boolean makeMove(Move move, Player player) throws Exception {
        Piece sourcePiece = move.getStart().getPiece();

        // check if piece exist
        if (sourcePiece == null) {
            System.err.println("The piece doesn't exist!");
            return false;
        }

        // valid player
        if (player != currentTurn) {
            System.err.println("The player is not on the move!");
            return false;
        }
        // validate if color of piece is same as player color
        if (sourcePiece.isWhite() != player.isWhiteSide()) {
            System.err.println("The piece color isn't same as a paler color!");
            return false;
        }

        // valid move
        // TODO: create rules for all pieces
        if (!sourcePiece.canMove(board, move.getStart(), move.getEnd())) {
            System.err.println("The move isn't possible!");
            return false;
        }

        // MOVES
        // kill opponent piece
        Piece destPiece = move.getEnd().getPiece();
        if (destPiece != null) {
            destPiece.setKilled(true);
            move.setPieceKilled(destPiece);
        }
        // ToDo: implementation of killing by el passant isn't function
        // kill opponent because 'El passant'
        if (sourcePiece instanceof Pawn && ((Pawn) sourcePiece).isDidElPassant()) {
            // get piece
            int colorRegulator = Pawn.colorRegulator(move.getStart().getPiece());
            Piece victim = board.getBox(move.getEnd().getX() + colorRegulator, move.getEnd().getY()).getPiece();
            // set killed
            victim.setKilled(true);
            move.setElPassant(true);
            move.setPieceKilled(victim);
            // remove killed piece
            board.getBox(move.getEnd().getX() + colorRegulator, move.getEnd().getY()).setPiece(null);
        }

        // store the move
        movesPlayed.add(move);

        // move piece from the start box to end box
        move.getEnd().setPiece(move.getStart().getPiece());
        move.getStart().setPiece(null);

        // check win situation
        if (destPiece != null && destPiece instanceof King) {
            if (player.isWhiteSide()) {
                this.setStatus(GameStatus.WHITE_WIN);
            } else {
                this.setStatus(GameStatus.BLACK_WIN);
            }
        }

        // set current turn to the other player
        if (this.currentTurn == players[0]) {
            this.currentTurn = players[1];
        } else {
            this.currentTurn = players[0];
        }

        gameRound++;

        return true;
    }

    /**
     * Print game info
     */
    public void printGameInfo() {
        System.out.println("Round: " + gameRound);
        System.out.println("Status: " + status);
        board.printBoard();
    }

    /**
     * Game statuses
     */
    public enum GameStatus {
        ACTIVE,
        BLACK_WIN,
        WHITE_WIN,
        FORFEIT,
        STALEMATE,
        RESIGNATION
    }

}
