package cz.cvut.fel.pjv.Utils;

import cz.cvut.fel.pjv.model.Game;

import java.io.*;

public class Utilities {
    public static void saveChessboard(Game game, String filepath) {
        try (OutputStream fos = new FileOutputStream(filepath);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(game);
            System.out.println("Serialized data is saved in" + filepath);
        } catch (IOException e) {
            System.err.println("Serialization error " + e);
        }
    }

    public static Game loadChessboard(String filepath) {
        Game game = null;
        try (InputStream fis = new FileInputStream(filepath);
             ObjectInputStream in = new ObjectInputStream(fis)) {
            game = (Game) in.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("I can't find the class definition: " + e);
        } catch (IOException e) {
            System.err.println("Problem with read file : " + e);
        }
        return game;
    }

}
