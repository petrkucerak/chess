package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.model.Pieces.King;
import cz.cvut.fel.pjv.model.Pieces.Pawn;
import cz.cvut.fel.pjv.model.Pieces.Piece;
import cz.cvut.fel.pjv.model.Pieces.Queen;
import cz.cvut.fel.pjv.model.Player.Player;

import java.util.ArrayList;

/**
 * The main mechanism of game
 */
public class Game {
    private Player[] players;
    private Board board;
    private Player currentTurn;
    private GameStatus status;
    private int gameRound;
    private ArrayList<Move> movesPlayed;
    private ArrayList<Board> gameBoards;

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
        this.gameBoards = new ArrayList<Board>();
        gameBoards.clear();
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

        // promotion
        if (sourcePiece instanceof Pawn && ((Pawn) sourcePiece).isPromotion()) {
            // select type of new piece
            // now generate automatically Queen
            move.getStart().setPiece(new Queen(sourcePiece.isWhite()));
            move.setPromotion(true);
            // ToDo: need implement a graphic piece choose
        }

        // castling
        if (sourcePiece instanceof King) {
            // long castling
            if (((King) sourcePiece).isLongCastlingMove()) {
                // save record
                move.setLongCastlingMove(true);
                // move with Rook
                // get Rook
                Piece rook = board.getBox(move.getStart().getX(), move.getStart().getY() - 4).getPiece();
                board.getBox(move.getStart().getX(), move.getStart().getY() - 4).setPiece(null);
                // set new Rook's position
                board.getBox(move.getStart().getX(), move.getStart().getY() - 1).setPiece(rook);
            }
            // short castling
            if (((King) sourcePiece).isShortCastlingMove()) {
                // save record
                move.setShortCastlingMove(true);
                // move with Rook
                // get Rook
                Piece rook = board.getBox(move.getStart().getX(), move.getStart().getY() + 3).getPiece();
                board.getBox(move.getStart().getX(), move.getStart().getY() + 3).setPiece(null);
                // set new Rook's position
                board.getBox(move.getStart().getX(), move.getStart().getY() + 1).setPiece(rook);
            }
        }

        // store the move
        movesPlayed.add(move);

        // move piece from the start box to end box
        move.getEnd().setPiece(move.getStart().getPiece());
        move.getStart().setPiece(null);

        // pinned piece
        if (isKingInDanger(move.getPlayer().isWhiteSide())) {
            System.err.println("King is in the danger!");
            // ToDo: implement move annulation
            return false;
        }

        // check 3-fold repetition situation
        checkThreeFoldRepetition(gameBoards);
        // store the board
        gameBoards.add(new Board(board.getBoxes()));


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
     * Function for check tree fold repetition
     *
     * @param gameBoards
     * @throws Exception
     */
    private void checkThreeFoldRepetition(ArrayList<Board> gameBoards) throws Exception {
        int countSameBoards = 0;
        for (int i = 0; i < gameBoards.size(); i++) {
            if (this.board.isBoardEqual(gameBoards.get(i))) {
                countSameBoards++;
            }
            if (countSameBoards == 2) {
                this.setStatus(GameStatus.DRAW);
                break;
            }
        }
    }

    /**
     * Interacts all opponent pieces and call function to check if the piece does not threaten the king
     * @param isWhiteSide
     * @return
     */
    private boolean isKingInDanger(boolean isWhiteSide) throws Exception {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // check if on this position isn't null
                if(!board.getBox(i,j).isSpotNull()){
                    // check valid opponent piece
                    Piece destPiece = board.getBox(i,j).getPiece();
                    if(destPiece.isWhite() != isWhiteSide){
                        if(destPiece.isKingInDanger(board, board.getBox(i,j), isWhiteSide)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
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
        CHECK,
        DRAW,
        FORFEIT,
        STALEMATE,
        RESIGNATION
    }

}
