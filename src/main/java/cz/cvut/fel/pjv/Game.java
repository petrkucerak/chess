package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.Figurines.Figurine;

/**
 * Includes mechanism of the game
 * controler
 */
public class Game {

    public void testRun(){
        Figurine figurines[][] = new Figurine[8][8];
        Chessboard board = new Chessboard(figurines, 0, 'B');

        System.out.println(board);

    }
}
