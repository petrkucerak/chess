package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.MainApp;

import java.text.SimpleDateFormat;

public class PGNFormatter {
    public static final String DATEFORMAT_PGN = "yyyy.MM.dd";

    static void updatePgnHeader() {
        Game game = MainApp.getGame();


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
}
