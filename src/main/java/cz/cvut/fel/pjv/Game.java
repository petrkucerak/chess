package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.Figurines.*;

/**
 * Includes mechanism of the game
 * controller
 */
public class Game {

    public void testRun(){
        // crate native board
        this.generateClassicChessboard("src/main/resources/initClassicGame");
        // load native board
        Chessboard board = Utilities.loadChessboard("src/main/resources/initClassicGame");
        System.out.println(board);
        board.moveFigurine(0,0,2,0);
        System.out.println(board);
    }

    /**
     * Generate classic chessboard
     * @param filepath
     */
    public void generateClassicChessboard(String filepath){
        Figurine figurines[][] = new Figurine[8][8];

        // PLACE FIGURINES
        // pawns
        for(int i = 0; i < figurines[1].length; i++){
            figurines[1][i] = new Pawn('B');
        }
        for(int i = 0; i < figurines[1].length; i++){
            figurines[6][i] = new Pawn('W');
        }
        // Kings
        figurines[0][4] = new King('B');
        figurines[7][4] = new King('W');
        // Queens
        figurines[0][3] = new Queen('B');
        figurines[7][3] = new Queen('W');
        // Rooks
        figurines[0][0] = new Rook('B');
        figurines[0][7] = new Rook('B');
        figurines[7][0] = new Rook('W');
        figurines[7][7] = new Rook('W');
        // Bishops
        figurines[0][2] = new Rook('B');
        figurines[0][5] = new Rook('B');
        figurines[7][5] = new Rook('W');
        figurines[7][2] = new Rook('W');
        // Knights
        figurines[0][1] = new Rook('B');
        figurines[0][6] = new Rook('B');
        figurines[7][1] = new Rook('W');
        figurines[7][6] = new Rook('W');

        Chessboard board = new Chessboard(figurines, 0, 'W');
        Utilities.saveChessboard(board, filepath);
        // board = Utilities.loadChessboard(filepath);
        // System.out.println(board);
    }
}
