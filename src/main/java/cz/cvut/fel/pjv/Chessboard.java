package cz.cvut.fel.pjv;


/**
 * Class representations Chessboard as a 2D array.
 */
public class Chessboard {
    private int [][]positions;
    private int gameRound;
    private char playerColor;

    private int countOfBlackFigurines;
    private int countOfWhiteFigurines;

    /**
     * @param positions representations Chessboard as a 2D array. Load positions from a data file by type of game.
     * @param gameRound
     * @param playerColor determines which player is on the move (black player - B, white color - W).
     * @param countOfBlackFigurines
     * @param countOfWhiteFigurines
     */
    public Chessboard(int[][] positions, int gameRound, char playerColor, int countOfBlackFigurines, int countOfWhiteFigurines) {
        this.positions = positions;
        this.gameRound = gameRound;
        this.playerColor = playerColor;
        this.countOfBlackFigurines = countOfBlackFigurines;
        this.countOfWhiteFigurines = countOfWhiteFigurines;
    }


    /**
     * Load Chessboard from the file.
     * @param filepath name of the file with figurines positions
     */
    public void loadChessboard(String filepath){

    }

    /**
     * Save Chessboard to the file.
     */
    public void saveChessboard(){

    }

    /**
     * Create all figurines
     */
    public void createAllFigurines(){

    }

    /**
     * Export Chessboard as PNG
     */
    public void exportPNGChessboard(){

    }

    /**
     * Import Chessboard as PNG
     */
    public void importPNGChessboard(){

    }
}
