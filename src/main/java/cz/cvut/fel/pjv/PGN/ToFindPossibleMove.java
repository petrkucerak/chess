package cz.cvut.fel.pjv.PGN;

import cz.cvut.fel.pjv.Utils.Utilities;
import cz.cvut.fel.pjv.model.Spot;

import static cz.cvut.fel.pjv.PGN.PGNFormatter.PGN_CASTLE_K;
import static cz.cvut.fel.pjv.PGN.PGNFormatter.PGN_CASTLE_Q;

/**
 * Class includes method to find possible move from PGN data.
 */
public class ToFindPossibleMove {

    private static int x;
    private static int y;
    private static char type;
    private static boolean longCastling = false;
    private static boolean shortCastling = false;

    final static private char[] cordY = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    public static int[] returnPossiblePNGmove(PGNGame game, String pgnCurrentMove){
        int[] moves = new int[4];
        Utilities.saveChessboard(game, "PGN-load.bin");

        // check castling


        return moves;
    }

    private static void getCoords(String input){

    }

    private static boolean isShortCastling(String string){
        if(string == PGN_CASTLE_K) return true;
        return false;
    }

    private static boolean isLongCastling(String string){
        if(string == PGN_CASTLE_Q) return true;
        return false;
    }

    private static void getPieceType(String input){
        char tmp = input.charAt(0);
        if(tmp=='K'){
            type = 'K';
        } else if(tmp == 'Q'){
            type = 'Q';
        } else if(tmp == 'N'){
            type = 'N';
        } else if(tmp == 'B'){
            type = 'B';
        } else if(tmp == 'R'){
            type = 'R';
        } else {
            type = 'p';
        }
    }


}
