package cz.cvut.fel.pjv.PGN;

import cz.cvut.fel.pjv.MainApp;
import cz.cvut.fel.pjv.model.Game;
import cz.cvut.fel.pjv.model.Move;
import cz.cvut.fel.pjv.model.Pieces.Piece;
import cz.cvut.fel.pjv.model.Spot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class PgnRefactor {
    public PgnRefactor() {
    }

    public ArrayList<String> convertMoveToPgn(ArrayList<Move> movesPlayed) {
        ArrayList<String> pngMoves = new ArrayList<String>();
        for (Move move : movesPlayed) {
            // create tmp string
            String tmp = "";

            // check castling
            if (move.isLongCastlingMove()) {
                tmp += PgnFormats.PGN_CASTLE_Q;
                pngMoves.add(tmp);
                continue;
            }
            if (move.isShortCastlingMove()) {
                tmp += PgnFormats.PGN_CASTLE_K;
                pngMoves.add(tmp);
                continue;
            }

            // add type of piece
            if (getSymbolOfPiece(move) != 'f') {
                tmp += getSymbolOfPiece(move);
            } else {
                // special pawn killing
                if (move.getEnd().getPiece() != null) {
                    if (move.getEnd().getPiece().isKilled()) {
                        tmp += returnPgnPosition(move.getStart().getY());
                    }
                }
            }
            // ToDO: problem with killing none pawn piecessn
            // add killing status
            if (move.getEnd().getPiece() != null) {
                if (move.getEnd().getPiece().isKilled()) {
                    tmp += "x";
                }
            }
            // add coords
            int x = move.getEnd().getX();
            int y = move.getEnd().getY();
            tmp += returnPgnPosition(x, y);

            // ToDO: check checking move
            // check checkmating move
            if (MainApp.getGame().getStatus() == Game.GameStatus.BLACK_WIN
                    || MainApp.getGame().getStatus() == Game.GameStatus.BLACK_WIN) {
                tmp += "#";
            }
            // add tmp to arraylist
            pngMoves.add(tmp);
        }
        return pngMoves;
    }

    public String exportGame(Game game) {
        // create String builder
        StringBuilder pgn = new StringBuilder();
        SimpleDateFormat dataFormat = new SimpleDateFormat(PgnFormats.DATEFORMAT_PGN);

        // create meatheads info
        appendPgnHeader(pgn, "Event", "The epic chess game");
        appendPgnHeader(pgn, "Site", "Prague, Czech Republic");
        appendPgnHeader(pgn, "Date", dataFormat.format(game.getStartDate()));
        appendPgnHeader(pgn, "Round", String.valueOf(game.getGameRound()));
        appendPgnHeader(pgn, "White", game.getPlayers()[0].toString());
        appendPgnHeader(pgn, "Black", game.getPlayers()[1].toString());

        /**
         * The Result field value is the result of the game. It is always exactly the same as the game termination
         * marker that concludes the associated movetext. It is always one of four possible values: "1-0" (White wins),
         * "0-1" (Black wins), "1/2-1/2" (drawn game), and "*" (game still in progress, game abandoned,
         * or result otherwise unknown). Note that the digit zero is used in both of the first two cases;
         * not the letter "O"
         */
        if (game.getStatus() == Game.GameStatus.ACTIVE) {
            appendPgnHeader(pgn, "Result", "*");
        }
        if (game.getStatus() == Game.GameStatus.BLACK_WIN) {
            appendPgnHeader(pgn, "Result", "0-1");
        }
        if (game.getStatus() == Game.GameStatus.WHITE_WIN) {
            appendPgnHeader(pgn, "Result", "1-0");
        }
        if (game.getStatus() == Game.GameStatus.DRAW) {
            appendPgnHeader(pgn, "Result", "1/2-1/2");
        }
        pgn.append("\n");

        // create moves info
        ArrayList<String> pngMoves = new ArrayList<String>();
        pngMoves = convertMoveToPgn(game.getMovesPlayed());

        StringBuilder line = new StringBuilder();
        int index = 2;

        for (String move : pngMoves) {
            if (index % 2 == 0) {
                line.append(index / 2);
                line.append(". ");
            }

            line.append(move);
            line.append(' ');


            if (line.length() > 70) {
                pgn.append(line);
                pgn.append("\n");
                line.setLength(0);
            }
            index++;
        }

        pgn.append(line);
        pgn.append("\n");

        return pgn.toString();
    }

    /**
     * Method to append PGN header.
     *
     * @param text
     * @param name
     * @param value
     */
    private void appendPgnHeader(StringBuilder text, String name, String value) {
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
     * @param x
     * @param y
     * @return
     */
    private String returnPgnPosition(int x, int y) {
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
    private String returnPgnPosition(int y) {
        char[] cordY = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        String ret = "";
        ret += cordY[y];
        return ret;
    }

    /**
     * Return symbol of piece
     *
     * @param move
     * @return
     */
    private char getSymbolOfPiece(Move move) {
        String name;
        if (move.getEnd().getPiece() != null) {
            name = move.getEnd().getPiece().getClass().getSimpleName();
        } else {
            name = move.getPieceMoved().getClass().getSimpleName();
            // name = move.getStart().getPiece().getClass().getSimpleName();
        }
        switch (name) {
            case "King":
                return 'K';
            case "Queen":
                return 'Q';
            case "Rook":
                return 'R';
            case "Bishop":
                return 'B';
            case "Knight":
                return 'N';
        }
        return 'f'; // f like f*ck
    }
}
