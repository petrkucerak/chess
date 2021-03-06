package cz.cvut.fel.pjv.PGN;

import cz.cvut.fel.pjv.MainApp;
import cz.cvut.fel.pjv.model.Game;
import cz.cvut.fel.pjv.model.Move;
import cz.cvut.fel.pjv.model.Pieces.*;
import cz.cvut.fel.pjv.model.Spot;

import java.io.*;
import java.text.SimpleDateFormat;

/**
 * Export PGN format.
 */
public class PGNFormatter {
    public static final String DATEFORMAT_PGN = "yyyy.MM.dd";
    public static final String PGN_CASTLE_K = "O-O";
    public static final String PGN_CASTLE_Q = "O-O-O";
    public static final String PATTERN_COMMENTS = "\\{.*?\\}";
    public static final String PATTERN_HEADER = "\\[(.*) \"(.*)\"\\]";
    public static final String PATTERN_PGN = "([RNBQKP]?)([a-h]?)([1-8]?)([x-]?)([a-h][1-8])\\+?=?([RNBQ])?([\\+#])?";

    /**
     * Method to update PGN header string.
     */
    public static void updatePgnHeader() {
        Game game;
        if(PGNFileRead.getGame() != null){
            game = PGNFileRead.getGame();
        } else {
            game = MainApp.getGame();
        }

        // create String builder
        StringBuilder pgnHeader = new StringBuilder();
        SimpleDateFormat dataFormat = new SimpleDateFormat(DATEFORMAT_PGN);

        // create meatheads info
        appendPgnHeader(pgnHeader, "Event", "The epic chess game");
        appendPgnHeader(pgnHeader, "Site", "Prague, Czech Republic");
        appendPgnHeader(pgnHeader, "Date", dataFormat.format(game.getStartDate()));
        appendPgnHeader(pgnHeader, "Round", String.valueOf(game.getGameRound()));
        appendPgnHeader(pgnHeader, "White", game.getPlayers()[0].toString());
        appendPgnHeader(pgnHeader, "Black", game.getPlayers()[1].toString());

        /**
         * The Result field value is the result of the game. It is always exactly the same as the game termination
         * marker that concludes the associated movetext. It is always one of four possible values: "1-0" (White wins),
         * "0-1" (Black wins), "1/2-1/2" (drawn game), and "*" (game still in progress, game abandoned,
         * or result otherwise unknown). Note that the digit zero is used in both of the first two cases;
         * not the letter "O"
         */
        if (game.getStatus() == Game.GameStatus.ACTIVE) {
            appendPgnHeader(pgnHeader, "Result", "*");
        }
        if (game.getStatus() == Game.GameStatus.BLACK_WIN) {
            appendPgnHeader(pgnHeader, "Result", "0-1");
        }
        if (game.getStatus() == Game.GameStatus.WHITE_WIN) {
            appendPgnHeader(pgnHeader, "Result", "1-0");
        }
        if (game.getStatus() == Game.GameStatus.DRAW) {
            appendPgnHeader(pgnHeader, "Result", "1/2-1/2");
        }

        game.setPgnHeader(pgnHeader.toString());
        // System.out.println(game.getPgnHeader());
    }

    /**
     * Method to update PGN moves string.
     */
    public static void updatePgnMoves() {
        Game game;
        if(PGNFileRead.getGame() != null){
            game = PGNFileRead.getGame();
        } else {
            game = MainApp.getGame();
        }

        String out = "";

        if (game.getGameRound() > 0 && game.getGameRound() % 2 == 1) {
            out += (game.getGameRound() / 2) + 1;
            out += ". ";
        }

        // get last stored move
        Move move = game.getMovesPlayed().get(game.getGameRound() - 1);

        // check castling moves
        if (move.isShortCastlingMove()) {
            out += PGN_CASTLE_K;
        } else if (move.isLongCastlingMove()) {
            out += PGN_CASTLE_Q;
        }

        if (!move.isLongCastlingMove() && !move.isShortCastlingMove()) {

            // move of Pawn
            if (move.getStart().getPiece() instanceof Pawn) {
                out += addPgnPawnMove(move);
            }

            // move of King
            if (move.getStart().getPiece() instanceof King) {
                out += addPgnNoPawnMove(move, 'K');
            }

            // move of Queen
            if (move.getStart().getPiece() instanceof Queen) {
                out += addPgnNoPawnMove(move, 'Q');
            }

            // move of Rook
            if (move.getStart().getPiece() instanceof Rook) {
                out += addPgnNoPawnMove(move, 'R');
            }

            // move of Bishop
            if (move.getStart().getPiece() instanceof Bishop) {
                out += addPgnNoPawnMove(move, 'B');
            }
            // move of Knight
            if (move.getStart().getPiece() instanceof Knight) {
                out += addPgnNoPawnMove(move, 'N');
            }
        }

        // check checkmating move & check checking move
        if (game.getStatus() == Game.GameStatus.BLACK_WIN
                || game.getStatus() == Game.GameStatus.BLACK_WIN) {
            game.remove2LastMovesPgnMoves();
            out = "#";
            game.setPgnMoves(MainApp.getGame().removeLastCharFromString(game.getPgnMoves()));

        } else if (game.getStatus() == Game.GameStatus.CHECK) {
            out += "+";
        }


        // add space
        out += " ";
        // final printing
        game.appendPgnMoves(out);
        // System.out.println(game.getPgnMoves());
    }

    /**
     * Return String with pawn move in PGN format.
     *
     * @param move
     * @return
     */
    private static String addPgnPawnMove(Move move) {
        String out = "";

        // normal pawn killing
        if (move.getEnd().getPiece() != null) {
            out += returnPgnPosition(move.getStart().getY());
            out += "x";
        }

        // normal pawn move
        out += returnPgnPosition(move.getEnd());

        return out;
    }

    /**
     * Return String with none pawn move in PGN format.
     *
     * @param move
     * @param piece
     * @return
     */
    private static String addPgnNoPawnMove(Move move, char piece) {
        String out = "";

        // add name of piece
        out += piece;

        // killing move
        if (move.getEnd().getPiece() != null) {
            out += "x";
        }

        // coords of move
        out += returnPgnPosition(move.getEnd());

        return out;
    }

    /**
     * Method to append PGN header.
     *
     * @param text
     * @param name
     * @param value
     */
    private static void appendPgnHeader(StringBuilder text, String name, String value) {
        text.append("[");
        text.append(name);
        text.append(" \"");
        text.append(value);
        text.append("\"");
        text.append("]");
        text.append("\n");
    }

    /**
     * Method to return PGN style of cords.
     *
     * @param spot
     * @return
     */
    private static String returnPgnPosition(Spot spot) {
        int y = spot.getY();
        int x = spot.getX();
        char[] cordY = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        char[] cordX = {'8', '7', '6', '5', '4', '3', '2', '1'};
        String ret = "";
        ret += cordY[y];
        ret += cordX[x];
        return ret;
    }

    /**
     * Method to return PGN style of Y cords.
     *
     * @param y
     * @return
     */
    private static String returnPgnPosition(int y) {
        char[] cordY = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        String ret = "";
        ret += cordY[y];
        return ret;
    }


}
