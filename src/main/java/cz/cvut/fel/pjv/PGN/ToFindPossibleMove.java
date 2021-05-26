package cz.cvut.fel.pjv.PGN;

import cz.cvut.fel.pjv.Utils.Utilities;
import cz.cvut.fel.pjv.model.Game;
import cz.cvut.fel.pjv.model.Spot;

import java.util.logging.Logger;

import static cz.cvut.fel.pjv.PGN.PGNFormatter.PGN_CASTLE_K;
import static cz.cvut.fel.pjv.PGN.PGNFormatter.PGN_CASTLE_Q;

/**
 * Class includes method to find possible move from PGN data.
 */
public class ToFindPossibleMove {

    private static final Logger LOG = Logger.getLogger(Game.class.getName());

    private static int x;
    private static int y;
    private static char type;
    private static boolean longCastling = false;
    private static boolean shortCastling = false;
    private static boolean isKillingMove = false;

    final static private char[] cordY = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    final static private char[] cordX = {'8', '7', '6', '5', '4', '3', '2', '1'};

    public static int[] returnPossiblePNGmove(PGNGame game, String pgnCurrentMove) {
        int[] moves = new int[4];
        Utilities.saveChessboard(game, "PGN-load.bin");

        // check castling
        if (isLongCastling(pgnCurrentMove)) {
            longCastling = true;
        } else if (isShortCastling(pgnCurrentMove)) {
            shortCastling = true;
        } else {
            // else check type of piece
            getPieceType(pgnCurrentMove);

            // check killing status
            checkKillingStatus(pgnCurrentMove);

            // get target coords
            getCoords(pgnCurrentMove);
        }

        moves[2] = x;
        moves[3] = y;

        getStartCords(game, x, y);

        return moves;
    }

    private static void checkKillingStatus(String input) {
        if (input.charAt(1) == 'x') {
            isKillingMove = true;
        }

    }

    private static void getCoords(String input) {
        // set position of first coords
        int index = 1;
        if (isKillingMove) {
            index = 2;
        } else if (type == 'p') {
            index = 0;
        }
        for (int i = 0; i < cordY.length; i++) {
            if (input.charAt(index) == cordY[i]) {
                y = i;
            }
        }
        for (int i = 0; i < cordX.length; i++) {
            if (input.charAt(index + 1) == cordX[i]) {
                x = i;
            }
        }
        LOG.info("X chord is: " + x);
        LOG.info("y chord is: " + y);
    }

    private static boolean isShortCastling(String string) {
        if (string == PGN_CASTLE_K) return true;
        return false;
    }

    private static boolean isLongCastling(String string) {
        if (string == PGN_CASTLE_Q) return true;
        return false;
    }

    private static void getPieceType(String input) {
        char tmp = input.charAt(0);
        if (tmp == 'K') {
            type = 'K';
        } else if (tmp == 'Q') {
            type = 'Q';
        } else if (tmp == 'N') {
            type = 'N';
        } else if (tmp == 'B') {
            type = 'B';
        } else if (tmp == 'R') {
            type = 'R';
        } else {
            type = 'p';
        }
    }

    static void getStartCords(PGNGame game, int endX, int endY){

    }


}
