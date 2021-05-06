package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.Figurines.*;

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

    /**
     * Generate classic chessboard
     * @param filepath
     */
    public void generateClassicChessboard(String filepath){
        Figurine figurines[][] = new Figurine[8][8];

        // add figurines
        Pawn bp1, bp2, bp3, bp4, bp5, bp6, bp7, bp8 = new Pawn('B');
        Pawn wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8 = new Pawn('W');
        Knight bk1, bk2 = new Knight('B');
        Knight wk1, wk2 = new Knight('W');
        Bishop bb1, bb2 = new Bishop('B');
        Bishop wb1, wb2 = new Bishop('W');
        Rook br1, br2 = new Rook('B');
        Rook wr1, wr2 = new Rook('W');
        Queen bq1 = new Queen('B');
        Queen wq1 = new Queen('W');
        King bi1 = new King('B');
        King wi1 = new King('W');


        Chessboard board = new Chessboard(figurines, -1, 'N');
        // board.saveChessboard(filepath);
        System.out.println(board);
    }
}
