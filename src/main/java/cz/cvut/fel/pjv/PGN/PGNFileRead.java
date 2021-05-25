package cz.cvut.fel.pjv.PGN;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PGNFileRead {
    public static void readPGNFile(String pathname) {
        // read file
        String input = readFile(pathname);
        // load game from pgn
        // set game to normal game
    }

    /**
     * Return PGN file as a String.
     * @param pathname
     * @return
     */
    static String readFile(String pathname) {
        String str = "";
        try{
            File file = new File(pathname);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                str += sc.nextLine();
            }
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return str;
    }
}
