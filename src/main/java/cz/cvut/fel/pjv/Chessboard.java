package cz.cvut.fel.pjv;


import cz.cvut.fel.pjv.Figurines.Figurine;

import java.util.Arrays;

/**
 * Class representations Chessboard as a 2D array.
 */
public class Chessboard {
    private Figurine board[][];
    private int gameRound;
    private char playerColor;

    public Chessboard(Figurine[][] board, int gameRound, char playerColor) {
        this.board = board;
        this.gameRound = gameRound;
        this.playerColor = playerColor;
    }

    @Override
    public String toString() {
        String tmp = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                tmp += board[i][j];
                tmp += ", ";
            }
            tmp += "\n";
        }
        return "Round: " + gameRound + "\n"
                + "Player color: " + playerColor + "\n"
                + tmp;
    }

    /**
     * Load Chessboard from the file.
     *
     * @param filepath name of the file with figurines positions
     */
    public void loadChessboard(String filepath) {

    }

    /**
     * Save Chessboard to the file.
     */
    public void saveChessboard() {

    }

    /**
     * Create all figurines
     */
    public void createAllFigurines() {

    }

    /**
     * Export Chessboard as PNG
     */
    public void exportPNGChessboard() {

    }

    /**
     * Import Chessboard as PNG
     */
    public void importPNGChessboard() {

    }
}
