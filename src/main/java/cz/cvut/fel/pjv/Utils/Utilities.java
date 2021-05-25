package cz.cvut.fel.pjv.Utils;

import cz.cvut.fel.pjv.model.Game;

import java.io.*;
import java.util.logging.Logger;

public class Utilities {
    private static final Logger LOG = Logger.getLogger(Game.class.getName());

    public static void saveChessboard(Game game, String filepath) {
        try (OutputStream fos = new FileOutputStream(filepath);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(game);
            LOG.info("Game data has been saved into: " + filepath);
        } catch (IOException e) {
            LOG.warning("Serialization error " + e);
        }
    }

    public static Game loadChessboard(String filepath) {
        Game game = null;
        try (InputStream fis = new FileInputStream(filepath);
             ObjectInputStream in = new ObjectInputStream(fis)) {
            game = (Game) in.readObject();
        } catch (ClassNotFoundException e) {
            LOG.warning("I can't find the class definition: " + e);
        } catch (IOException e) {
            LOG.warning("Problem with read file : " + e);
        }
        return game;
    }

}
