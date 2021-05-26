package cz.cvut.fel.pjv.PGN;

import cz.cvut.fel.pjv.model.Player.HumanPlayer;
import cz.cvut.fel.pjv.model.Player.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static cz.cvut.fel.pjv.PGN.PGNFormatter.DATEFORMAT_PGN;

public class PGNFileRead {

    static private String dateString = "";
    static private int gameRound = 0;
    static private String playerWhiteString = "";
    static private String playerBlackString = "";
    static private String result = "";

    static private String headerPGN;
    static private String movesPGN;

    public static void readPGNFile(String pathname) throws ParseException {
        // read file
        String input = readFile(pathname);

        // parse string
        parseString(input);

        // create PGN game
        Player playerBlack = new HumanPlayer(false);
        Player playerWhite = new HumanPlayer(true);

        playerBlack.setName(playerBlackString);
        playerWhite.setName(playerWhiteString);

        SimpleDateFormat dataFormat = new SimpleDateFormat(DATEFORMAT_PGN);
        Date date = dataFormat.parse(dateString);

        PGNGame game = new PGNGame();
        game.initGame(playerWhite, playerBlack, date);

        // load game from pgn
        // set game to normal game
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
     * @param input
     */
    static void parseHeaderString(String input){
        String[] lines = headerPGN.split("\n");

        parseDate(lines[2]);
        parseGameRound(lines[3]);
        parseWhitePlayer(lines[4]);
        parseBlackPlayer(lines[5]);
        parseGameResult(lines[6]);

    }

    static void parseDate(String input){
        String regex = "\\[(.*) \"";
        input = input.replaceAll(regex,"");
        regex = "\"\\]";
        dateString = input.replaceAll(regex,"");
    }

    static void parseGameRound(String input){
        String regex = "\\[(.*) \"";
        input = input.replaceAll(regex,"");
        regex = "\"\\]";
        input = input.replaceAll(regex,"");
        gameRound = Integer.parseInt(input);
    }

    static void parseWhitePlayer(String input){
        String regex = "\\[(.*) \"";
        input = input.replaceAll(regex,"");
        regex = "\"\\]";
        playerWhiteString = input.replaceAll(regex,"");
    }

    static void parseBlackPlayer(String input){
        String regex = "\\[(.*) \"";
        input = input.replaceAll(regex,"");
        regex = "\"\\]";
        playerBlackString = input.replaceAll(regex,"");

    }

    static void parseGameResult(String input){
        String regex = "\\[(.*) \"";
        input = input.replaceAll(regex,"");
        regex = "\"\\]";
        result = input.replaceAll(regex,"");
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
}
