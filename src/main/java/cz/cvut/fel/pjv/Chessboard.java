package cz.cvut.fel.pjv;


import cz.cvut.fel.pjv.Figurines.Figurine;

/**
 * Class representations Chessboard as a 2D array.
 */
public class Chessboard implements java.io.Serializable {
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
     * Switch player color on the Chessboard
     */
    private void switch_color() {
        if (this.playerColor == 'B') this.playerColor = 'W';
        if (this.playerColor == 'W') this.playerColor = 'B';
    }

    /**
     * Move the figurine
     *
     * @param x
     * @param y
     * @param new_x
     * @param new_y
     */
    public void moveFigurine(int x, int y, int new_x, int new_y) {
        this.board[new_y][new_x] = this.board[y][x];
        this.board[y][x] = null;
        this.gameRound++;
        switch_color();
    }

    /**
     * Function print possible moves
     * @param x
     * @param y
     */
    public void printPossibleMoves(int x, int y){
        boolean[][] tmp = new boolean[8][8];
        tmp = this.board[y][x].possibleMoves(x,y);
        Utilities.print_2d_array(tmp);
        System.out.println();
    }

    /**
     * Export Chessboard as PGN
     */
    public void exportPNGChessboard() {

    }

    /**
     * Import Chessboard as PGN
     */
    public void importPNGChessboard() {

    }

}
