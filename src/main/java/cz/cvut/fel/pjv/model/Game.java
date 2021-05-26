package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.MainApp;
import cz.cvut.fel.pjv.PGN.PGNFormatter;
import cz.cvut.fel.pjv.PGN.PGNGame;
import cz.cvut.fel.pjv.Utils.Utilities;
import cz.cvut.fel.pjv.model.Pieces.King;
import cz.cvut.fel.pjv.model.Pieces.Pawn;
import cz.cvut.fel.pjv.model.Pieces.Piece;
import cz.cvut.fel.pjv.model.Pieces.Queen;
import cz.cvut.fel.pjv.model.Player.Player;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Date;
import java.util.logging.*;

import static cz.cvut.fel.pjv.model.Pieces.Piece.*;

/**
 * The main mechanism of game
 */
public class Game implements Serializable {
    private Player[] players;
    private double[] timeLefts;
    private Board board;
    private Player currentTurn;
    private GameStatus status;
    private int gameRound;
    private ArrayList<Move> movesPlayed;
    private ArrayList<Board> gameBoards;
    private Date startDate;
    private String pgnHeader;
    private String pgnMoves;
    private boolean matInspectStatus;
    private boolean PGNGame = false;

    public void setPgnHeader(String pgnHeader) {
        this.pgnHeader = pgnHeader;
    }

    public boolean isPGNGame() {
        return PGNGame;
    }

    public void setPGNGame(boolean PGNGame) {
        this.PGNGame = PGNGame;
    }

    public void setPgnMoves(String pgnMoves) {
        this.pgnMoves = pgnMoves;
    }

    public void appendPgnMoves(String pgnMoves) {
        this.pgnMoves += pgnMoves;
    }

    public void remove2LastMovesPgnMoves() {
        String[] words = pgnMoves.split(" ");
        pgnMoves = "";
        if (words.length % 3 == 2) {
            for (int i = 0; i < words.length - 2; i++) {
                pgnMoves += words[i];
                if (i != words.length - 3) {
                    pgnMoves += " ";
                }
            }
        } else {
            for (int i = 0; i < words.length - 1; i++) {
                pgnMoves += words[i];
                if (i != words.length - 2) {
                    pgnMoves += " ";
                }
            }
        }
    }

    public String removeLastCharFromString(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == '+') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public String getPgnHeader() {
        return pgnHeader;
    }

    public String getPgnMoves() {
        return pgnMoves;
    }

    private static final Logger LOG = Logger.getLogger(Game.class.getName());

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

        this.timeLefts = new double[2];
        timeLefts[0] = -1;
        timeLefts[1] = -1;
        this.setStatus(GameStatus.ACTIVE);

        this.startDate = new Date();

        // set siro only for pgn format
        this.gameRound = 0;
        PGNFormatter.updatePgnHeader();
        this.gameRound = 1;
        pgnMoves = "";

        Logger.getLogger("").setLevel(Level.INFO);
        Logger.getLogger("").getHandlers()[0].setLevel(Level.INFO);

        matInspectStatus = false;
    }

    public void setTimeLefts(double[] timeLefts) {
        this.timeLefts = timeLefts;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setTimeLefts(int timeLefts) {
        this.timeLefts[0] = timeLefts;
        this.timeLefts[1] = timeLefts;
    }

    public void setTimeLefts(double timeLefts, int index) {
        this.timeLefts[index] = timeLefts;
    }

    public double[] getTimeLefts() {
        return timeLefts;
    }

    public double getAllTimeLefts() {
        return timeLefts[0];
    }

    public static Logger getLOG() {
        return LOG;
    }

    /**
     * Check if is end of the game
     *
     * @return
     */
    public boolean isEnd() {
        if (this.getStatus() != GameStatus.ACTIVE && this.getStatus() != GameStatus.CHECK
                && this.getStatus() != GameStatus.RESIGNATION) {
            return true;
        }
        return false;
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
        // set logger levels

        // for returning move
        turnLogOff();
        Utilities.saveChessboard(MainApp.getGame(), "move.bin");
        turnLogOn();

        if (isEnd()) {
            LOG.info("End of the game!");
            return false;
        }

        Piece sourcePiece = move.getStart().getPiece();

        // check if piece exist
        if (sourcePiece == null) {
            LOG.warning("The piece doesn't exist!");
            return false;
        }

        // valid player
        if (player != currentTurn) {
            LOG.warning("The player is not on the move!");
            return false;
        }
        // validate if color of piece is same as player color

        if (sourcePiece.isWhite() != player.isWhiteSide()) {
            LOG.warning("The piece color isn't same as a player color!");
            return false;
        }


        // valid move
        if (!sourcePiece.canMove(board, move.getStart(), move.getEnd())) {
            LOG.warning("The move isn't possible!");
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
        movesPlayed.add(new Move(move));


        // move piece from the start box to end box
        move.getEnd().setPiece(move.getStart().getPiece());
        move.getStart().setPiece(null);

        // inspect CHECKING

        if (sourcePiece.isChecking(board, move.getEnd(), sourcePiece.isWhite())) {
            setStatus(GameStatus.CHECK);
            LOG.info("The game status has been set ont CHECK!");
        } else {
            setStatus(GameStatus.ACTIVE);
        }

        if (MainApp.getCountOfCheck() < 1) {
            // pinned piece
            board.setActiveCheckingIsKingInDanger(true);
            if (isKingInDanger(move.getPlayer().isWhiteSide())) {
                LOG.warning("The king is in the danger!");
                // return move
                MainApp.setGame(Utilities.loadChessboard("move.bin"));
                MainApp.addCountOfCheck();
                board.setActiveCheckingIsKingInDanger(false);
                return false;
            }
            MainApp.setCountOfCheck(0);
            board.setActiveCheckingIsKingInDanger(false);

            // check 3-fold repetition situation
            checkThreeFoldRepetition(gameBoards);
            // store the board
            gameBoards.add(new Board(board.getBoxes()));
        }


        // check win situation
        if (destPiece != null && destPiece instanceof King) {
            if (player.isWhiteSide()) {
                this.setStatus(GameStatus.WHITE_WIN);
            } else {
                this.setStatus(GameStatus.BLACK_WIN);
            }
        }

        if (!matInspectStatus) {
            // ToDo
            if(!PGNGame) {
                PGNFormatter.updatePgnHeader();
                PGNFormatter.updatePgnMoves();
            }
        }

        // set current turn to the other player
        switchPlayer();
        gameRound++;

        MainApp.setCountOfCheck(0);

        return true;
    }

    /**
     * set current turn to the other player
     */
    private void switchPlayer() {
        if (this.currentTurn == players[0]) {
            this.currentTurn = players[1];
        } else {
            this.currentTurn = players[0];
        }
    }

    private void turnLogOff() {
        Logger.getLogger("").setLevel(Level.OFF);
        Logger.getLogger("").getHandlers()[0].setLevel(Level.OFF);
    }

    private void turnLogOn() {
        Logger.getLogger("").setLevel(Level.INFO);
        Logger.getLogger("").getHandlers()[0].setLevel(Level.INFO);
    }

    private boolean tryAllMovesIfItIsMat() throws Exception {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // check if is not null
                if (!board.getBox(i, j).isSpotNull()) {
                    // check my color
                    if (board.getBox(i, j).getPiece().isWhite() == currentTurn.isWhiteSide()) {
                        if (!tryAllPieceMovesIfItIsMat(board.getBox(i, j))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean tryAllPieceMovesIfItIsMat(Spot startBox) throws Exception {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                // simulate the next round
                switchPlayer();
                gameRound++;

                // set variables
                Player player = this.currentTurn;
                Spot endBox = board.getBox(i, j);
                Move move = new Move(player, startBox, endBox);

                // try if move is valid
                turnLogOff();
                if (this.makeMove(move, player)) {
                    turnLogOn();
                    MainApp.setGame(Utilities.loadChessboard("checkmating.bin"));
                    return false;
                    // if check status disappear, return false

                }
                MainApp.setGame(Utilities.loadChessboard("checkmating.bin"));
                turnLogOn();
            }
        }
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
     *
     * @param isWhiteSide
     * @return
     */
    private boolean isKingInDanger(boolean isWhiteSide) throws Exception {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // check if on this position isn't null
                if (!board.getBox(i, j).isSpotNull()) {
                    // check valid opponent piece
                    Piece destPiece = board.getBox(i, j).getPiece();
                    if (destPiece.isWhite() != isWhiteSide) {
                        if (destPiece.isKingInDanger(board, board.getBox(i, j), isWhiteSide)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void printPlayer() {
        if (currentTurn.isWhiteSide()) {
            System.out.println("On the order is: " + BLACK + "player" + RESET);
        } else {
            System.out.println("On the order is: " + WHITE + "player" + RESET);
        }
    }

    /**
     * Print game info
     */
    public void printGameInfo() {
        System.out.println("Round: " + gameRound);
        System.out.println("Status: " + status);
        printPlayer();
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

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setCurrentTurn(Player currentTurn) {
        this.currentTurn = currentTurn;
    }

    public void setGameRound(int gameRound) {
        this.gameRound = gameRound;
    }

    public void setMovesPlayed(ArrayList<Move> movesPlayed) {
        this.movesPlayed = movesPlayed;
    }

    public void setGameBoards(ArrayList<Board> gameBoards) {
        this.gameBoards = gameBoards;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentTurn() {
        return currentTurn;
    }

    public int getGameRound() {
        return gameRound;
    }

    public ArrayList<Move> getMovesPlayed() {
        return movesPlayed;
    }

    public ArrayList<Board> getGameBoards() {
        return gameBoards;
    }


    public boolean isMatInspectStatus() {
        return matInspectStatus;
    }

    public void setMatInspectStatus(boolean matInspectStatus) {
        this.matInspectStatus = matInspectStatus;
    }
}
