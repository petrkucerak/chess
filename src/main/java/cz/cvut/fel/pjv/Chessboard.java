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

}
