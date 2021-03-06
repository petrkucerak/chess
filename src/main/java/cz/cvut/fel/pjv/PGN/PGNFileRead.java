package cz.cvut.fel.pjv.PGN;

import cz.cvut.fel.pjv.MainApp;
import cz.cvut.fel.pjv.Utils.Log;
import cz.cvut.fel.pjv.model.Game;
import cz.cvut.fel.pjv.model.Player.ComputerPlayer;
import cz.cvut.fel.pjv.model.Player.HumanPlayer;
import cz.cvut.fel.pjv.model.Player.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

import static cz.cvut.fel.pjv.PGN.PGNFormatter.DATEFORMAT_PGN;
import static cz.cvut.fel.pjv.PGN.PGNFormatter.PATTERN_COMMENTS;
import static cz.cvut.fel.pjv.PGN.ToFindPossibleMove.returnPossiblePNGmove;

public class PGNFileRead {

    static private String dateString = "";
    static private int gameRound = 0;
    static private String playerWhiteString = "";
    static private String playerBlackString = "";
    static private String result = "";
    static private ArrayList<String> movesPGNParsed = new ArrayList<String>();

    static private String headerPGN;
    static private String movesPGN;

    private static PGNGame game;
    private static final Logger LOG = Logger.getLogger(Game.class.getName());

    public static void readPGNFile(String pathname) throws Exception {
        // read file
        String input = readFile(pathname);

        // parse string
        parseString(input);

        // create PGN game
        Player playerBlack = new HumanPlayer(false);
        Player playerWhite = new ComputerPlayer(true);

        playerBlack.setName(playerBlackString);
        playerWhite.setName(playerWhiteString);

        SimpleDateFormat dataFormat = new SimpleDateFormat(DATEFORMAT_PGN);
        Date date = dataFormat.parse(dateString);

        game = new PGNGame();
        game.initGame(playerWhite, playerBlack, date);

        // load game from pgn
        playGameByPGNMoves(game);

        // set game to normal game
        setPGNGameToStandardGame(game);
        game.setPGNGame(false);
    }

    private static void setPGNGameToStandardGame(PGNGame game) {
        MainApp.setGame(null);
        MainApp.setGame(game);
    }

    static void playGameByPGNMoves(PGNGame game) throws Exception {

        // create loop
        parseMoveString(movesPGN);

        do {
            // get move from PGN format
            int[] moves = returnPossiblePNGmove(game, movesPGNParsed.get(game.getGameRound() - 1));

            int startX = moves[0];
            int startY = moves[1];
            int endX = moves[2];
            int endY = moves[3];

            Log.turnLogOff();
            LOG.info("Cords are: " + startX + startY + endX + endY);
            Log.turnLogOn();

            if (game.playerMove(game.getCurrentTurn(), startX, startY, endX, endY)) {
                Log.turnLogOff();
                LOG.info("Move has been success!");
                Log.turnLogOn();
            }

        } while (gameRound != game.getGameRound() - 1);
    }

    /**
     * Return PGN file as a String.
     *
     * @param pathname
     * @return
     */
    static String readFile(String pathname) {
        String str = "";
        try {
            File file = new File(pathname);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                str += sc.nextLine();
                str += "\n";
            }
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * Parse string into the structure variables.
     *
     * @param input
     */
    static void parseString(String input) {
        // parse into the specific parts
        String[] parts = input.split("\n\n");
        PGNFileRead.headerPGN = parts[0];
        PGNFileRead.movesPGN = parts[1];

        System.out.println(parts[0]);
        System.out.println(parts[1]);

        parseHeaderString(headerPGN);

    }

    /**
     * Parse header string int class variables.
     *
     * @param input
     */
    static void parseHeaderString(String input) {
        String[] lines = headerPGN.split("\n");

        parseDate(lines[2]);
        // parseGameRound(lines[3]); use size of moves ArrayList
        parseWhitePlayer(lines[4]);
        parseBlackPlayer(lines[5]);
        parseGameResult(lines[6]);

    }

    static void parseDate(String input) {
        String regex = "\\[(.*) \"";
        input = input.replaceAll(regex, "");
        regex = "\"\\]";
        dateString = input.replaceAll(regex, "");
    }

    static void parseGameRound(String input) {
        String regex = "\\[(.*) \"";
        input = input.replaceAll(regex, "");
        regex = "\"\\]";
        input = input.replaceAll(regex, "");
        gameRound = Integer.parseInt(input);
    }

    static void parseWhitePlayer(String input) {
        String regex = "\\[(.*) \"";
        input = input.replaceAll(regex, "");
        regex = "\"\\]";
        playerWhiteString = input.replaceAll(regex, "");
    }

    static void parseBlackPlayer(String input) {
        String regex = "\\[(.*) \"";
        input = input.replaceAll(regex, "");
        regex = "\"\\]";
        playerBlackString = input.replaceAll(regex, "");

    }

    static void parseGameResult(String input) {
        String regex = "\\[(.*) \"";
        input = input.replaceAll(regex, "");
        regex = "\"\\]";
        result = input.replaceAll(regex, "");
    }

    /**
     * Parse move from string into the ArrayList movesPGNParsed.
     *
     * @param input
     */
    static void parseMoveString(String input) {
        // remove new lines
        String regex = "\n";
        input = input.replaceAll(regex, " ");

        // remove comments
        regex = PATTERN_COMMENTS;
        input = input.replaceAll(regex, "");

        // remove index num
        regex = "\\d\\.";
        input = input.replaceAll(regex, "");

        // remove checking info
        regex = "\\+";
        input = input.replaceAll(regex, "");

        // split into string array
        String[] words = input.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() >= 2) {
                movesPGNParsed.add(words[i]);
            }
        }
        /*
        // test print
        for (int i = 0; i < movesPGNParsed.size(); i++) {
            System.out.println(movesPGNParsed.get(i));
        }

        */
        gameRound = movesPGNParsed.size();
    }

    public static String getDateString() {
        return dateString;
    }

    public static void setDateString(String dateString) {
        PGNFileRead.dateString = dateString;
    }

    public static int getGameRound() {
        return gameRound;
    }

    public static void setGameRound(int gameRound) {
        PGNFileRead.gameRound = gameRound;
    }

    public static String getResult() {
        return result;
    }

    public static void setResult(String result) {
        PGNFileRead.result = result;
    }

    public static String getHeaderPGN() {
        return headerPGN;
    }

    public static void setHeaderPGN(String headerPGN) {
        PGNFileRead.headerPGN = headerPGN;
    }

    public static String getMovesPGN() {
        return movesPGN;
    }

    public static void setMovesPGN(String movesPGN) {
        PGNFileRead.movesPGN = movesPGN;
    }

    public static String getPlayerWhiteString() {
        return playerWhiteString;
    }

    public static void setPlayerWhiteString(String playerWhiteString) {
        PGNFileRead.playerWhiteString = playerWhiteString;
    }

    public static String getPlayerBlackString() {
        return playerBlackString;
    }

    public static void setPlayerBlackString(String playerBlackString) {
        PGNFileRead.playerBlackString = playerBlackString;
    }

    public static ArrayList<String> getMovesPGNParsed() {
        return movesPGNParsed;
    }

    public static void setMovesPGNParsed(ArrayList<String> movesPGNParsed) {
        PGNFileRead.movesPGNParsed = movesPGNParsed;
    }

    public static PGNGame getGame() {
        return game;
    }

    public static void setGame(PGNGame game) {
        PGNFileRead.game = game;
    }
}
