package cz.cvut.fel.pjv;

import java.io.*;

/**
 * Support functions
 */
public class Utilities {

    /**
     * Save Chessboard for serialization
     * @param chessboard
     * @param filepath
     */
    public static void saveChessboard(Chessboard chessboard, String filepath) {
        try (OutputStream fos = new FileOutputStream(filepath);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(chessboard);
            System.out.println("Serialized data is saved in" + filepath);
        } catch (IOException e) {
            System.out.println("Serialization error " + e);
        }
    }

    /**
     * Load Chessboard from serialized file
     * @param filepath
     * @return loaded chessboard
     */
    public static Chessboard loadChessboard(String filepath) {
        Chessboard chessboard = null;
        try (InputStream fis = new FileInputStream(filepath);
             ObjectInputStream in = new ObjectInputStream(fis)) {
            chessboard = (Chessboard) in.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Nemohu najít definici třídy: " + e);
        } catch (IOException e) {
            System.out.println("Chyba při čtení souboru : " + e);
        }
        return chessboard;
    }
}
