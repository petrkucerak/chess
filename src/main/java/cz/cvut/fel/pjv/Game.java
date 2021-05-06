package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.Figurines.Bishop;
import cz.cvut.fel.pjv.Figurines.Figurine;

/**
 * Includes mechanism of the game
 * controller
 */
public class Game {

    public void testRun(){
        Figurine figurines[][] = new Figurine[8][8];
        Bishop bishop = new Bishop('B', "bishop");
        figurines[0][0] = bishop;
        Chessboard board = new Chessboard(figurines, 0, 'B');

        System.out.println(board);

    }
}
