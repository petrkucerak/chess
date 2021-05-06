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
        Chessboard board = new Chessboard(figurines, 10, 'B');
        board.loadChessboard("src/main/resources/initClassicGame");
        System.out.println(board);

    }
}
