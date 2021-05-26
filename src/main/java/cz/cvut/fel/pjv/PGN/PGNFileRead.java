package cz.cvut.fel.pjv.PGN;

import cz.cvut.fel.pjv.model.Player.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static cz.cvut.fel.pjv.PGN.PGNFormatter.PATTERN_HEADER;

public class PGNFileRead {

    static private String date = "";
    static private int gameRound = 0;
    static private String player1name = "";
    static private String player2name = "";
    static private String result = "";

    static private String headerPGN;
    static private String movesPGN;

    public static void readPGNFile(String pathname) {
        // read file
        String input = readFile(pathname);

        // parse string
        parseString(input);
        // create PGN game

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
        

    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        PGNFileRead.date = date;
    }

    public static int getGameRound() {
        return gameRound;
    }

    public static void setGameRound(int gameRound) {
        PGNFileRead.gameRound = gameRound;
    }

    public static String getPlayer1name() {
        return player1name;
    }

    public static void setPlayer1name(String player1name) {
        PGNFileRead.player1name = player1name;
    }

    public static String getPlayer2name() {
        return player2name;
    }

    public static void setPlayer2name(String player2name) {
        PGNFileRead.player2name = player2name;
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

}
