package cz.cvut.fel.pjv.Figurines;

import cz.cvut.fel.pjv.Chessboard;

/**
 * Class for manipulations with Figurines.
 * Type of figurines are represented as numbers by this key:
 * • king - 9
 * • queen - 8
 * • rook - 7
 * • bishop - 6
 * • knight - 5
 * • pawn - 4
 * Color of figurines are represented as decimal multiple by this key:
 * • black - 10
 * • white - 20
 * (For example, the black king has a number 19 or the white pawn has the number 24)
 */
public class Figurine {
    private char color;
    private int type;

    public Figurine(char color, int type) {
        this.color = color;
        this.type = type;
    }

    /**
     * @return possible moves by type of figure as a 2D array.
     */
    public int[][] getPossibleMoves(){
        return null;
    }

    /**
     * @param x coordinates of the position
     * @param y coordinates of the position
     * @param chessboard info of board
     * @return true - if the move is possible or false - if it isn't possible
     */
    public boolean pinedPeace(int x, int y, Chessboard chessboard){
        return false;
    }

    /**
     * @param x coordinates of the position
     * @param y coordinates of the position
     * @param chessboard1 info of board
     * @param chessboard2 info of board
     * @param chessboard3 info of board
     * @return true - if the move is possible or false - if it isn't possible
     */
    public boolean threefoldRepetition(int x, int y, Chessboard chessboard1, Chessboard chessboard2, Chessboard chessboard3){
        return false;
    }

}

