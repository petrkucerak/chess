package cz.cvut.fel.pjv.Utils;

import cz.cvut.fel.pjv.PGN.PGNGame;
import cz.cvut.fel.pjv.model.Game;

import java.io.*;
import java.util.logging.Logger;

public class Utilities {
    private static final Logger LOG = Logger.getLogger(Game.class.getName());

    /**
     * Save game into the binary file.
     *
     * @param game
     * @param filepath
     */
    public static void saveChessboard(Game game, String filepath) {
        try (OutputStream fos = new FileOutputStream(filepath);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(game);
            LOG.info("Game data has been saved into: " + filepath);
        } catch (IOException e) {
            LOG.warning("Serialization error " + e);
        }
    }

    /**
     * Save PGN game into the binary file.
     *
     * @param game
     * @param filepath
     */
    public static void savePGNChessboard(PGNGame game, String filepath) {
        try (OutputStream fos = new FileOutputStream(filepath);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(game);
            LOG.info("Game data has been saved into: " + filepath);
        } catch (IOException e) {
            LOG.warning("Serialization error " + e);
        }
    }

    /**
     * Method for load game.
     *
     * @param filepath location of binary file.
     * @return
     */
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

    /**
     * Method for load PGN game.
     *
     * @param filepath location of binary file.
     * @return
     */
    public static PGNGame loadPGNChessboard(String filepath) {
        PGNGame game = null;
        try (InputStream fis = new FileInputStream(filepath);
             ObjectInputStream in = new ObjectInputStream(fis)) {
            game = (PGNGame) in.readObject();
        } catch (ClassNotFoundException e) {
            LOG.warning("I can't find the class definition: " + e);
        } catch (IOException e) {
            LOG.warning("Problem with read file : " + e);
        }
        return game;
    }

}
