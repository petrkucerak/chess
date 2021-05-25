package cz.cvut.fel.pjv.PGN;

import cz.cvut.fel.pjv.model.Player.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PGNFileRead {

    private String date;
    private int gameRound;
    private String player1name;
    private String player2name;
    private String result;

    private String headerPGN;
    private String movesPGN;

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

    }
}
