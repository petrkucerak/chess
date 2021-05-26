package cz.cvut.fel.pjv.PGN;

import cz.cvut.fel.pjv.Utils.Log;
import cz.cvut.fel.pjv.Utils.Utilities;
import cz.cvut.fel.pjv.model.Game;
import cz.cvut.fel.pjv.model.Pieces.*;
import cz.cvut.fel.pjv.model.Spot;

import java.util.logging.Logger;

import static cz.cvut.fel.pjv.PGN.PGNFormatter.PGN_CASTLE_K;
import static cz.cvut.fel.pjv.PGN.PGNFormatter.PGN_CASTLE_Q;

/**
 * Class includes method to find possible move from PGN data.
 */
public class ToFindPossibleMove {

    private static final Logger LOG = Logger.getLogger(Game.class.getName());

    private static int endX;
    private static int endY;
    private static int startX;
    private static int startY;
    private static char type;
    private static boolean longCastling = false;
    private static boolean shortCastling = false;
    private static boolean isKillingMove = false;

    final static private char[] cordY = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    final static private char[] cordX = {'8', '7', '6', '5', '4', '3', '2', '1'};

    public static int[] returnPossiblePNGmove(PGNGame game, String pgnCurrentMove) throws Exception {

        Log.turnLogOff();

        int[] moves = new int[4];

        startX = -1;
        startY = -1;
        endX = -1;
        endY = -1;

        isKillingMove = false;

        Utilities.savePGNChessboard(game, "PGN-load.bin");

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

            if (startY != -1) {
                getStartCordsWithY(game, endX, endY, startY);
                System.out.println("Yo");
            } else {
                getStartCords(game, endX, endY);
            }

        }

        if (longCastling) {
            startY = 4;
            endY = 2;
            if (game.getCurrentTurn().isWhiteSide()) {
                startX = 7;
                endX = 7;
            } else {
                startX = 0;
                endX = 0;
            }
            longCastling = false;
        }
        if (shortCastling) {
            startY = 4;
            endY = 6;
            if (game.getCurrentTurn().isWhiteSide()) {
                startX = 7;
                endX = 7;
            } else {
                startX = 0;
                endX = 0;
            }
            shortCastling = false;
        }

        moves[0] = startX;
        moves[1] = startY;
        moves[2] = endX;
        moves[3] = endY;

        if (longCastling || shortCastling) {
            // Log.turnLogOn();
            LOG.info("Game chords are: " + startX + startY + endX + endY);
            // Log.turnLogOff();
        }


        PGNFileRead.setGame(Utilities.loadPGNChessboard("PGN-load.bin"));

        // Log.turnLogOn();
        LOG.info(pgnCurrentMove);
        // Log.turnLogOff();
        Log.turnLogOn();

        return moves;
    }

    private static void checkKillingStatus(String input) {
        if (input.charAt(1) == 'x') {
            isKillingMove = true;
        } else if (input.length() > 2) {
            if (input.charAt(2) == 'x') {
                isKillingMove = true;
            }
        } else {
            isKillingMove = false;
        }

    }

    private static void getCoords(String input) {
        // set position of first coords
        int index;
        if(type == 'p'){
            if(isKillingMove){
                index = 2;
                for(int i = 0; i < cordY.length; i++){
                    if(input.charAt(0) == cordY[i]){
                        startY = i;
                    }
                }
            } else {
                index = 0;
            }
        } else {
            if (isKillingMove){
                if(input.length() == 5){
                    index = 3;
                    for(int i = 0; i < cordY.length; i++){
                        if(input.charAt(1) == cordY[i]){
                            startY = i;
                        }
                    }
                } else {
                    index = 2;
                }
            } else {
                if(input.length() == 4){
                    index = 2;
                    for(int i = 0; i < cordY.length; i++){
                        if(input.charAt(1) == cordY[i]){
                            startY = i;
                        }
                    }
                } else {
                    index = 1;
                }
            }
        }

        for (int i = 0; i < cordY.length; i++) {
            if (input.charAt(index) == cordY[i]) {
                endY = i;
            }
        }
        for (int i = 0; i < cordX.length; i++) {
            if (input.charAt(index + 1) == cordX[i]) {
                endX = i;
            }
        }
        // Log.turnLogOn();
        LOG.info("X chord is: " + endX);
        LOG.info("y chord is: " + endY);
        LOG.info("start y chord is: " + startY);
        LOG.info("start x chord is: " + startX);
        // Log.turnLogOff();
    }

    private static boolean isShortCastling(String string) {
        if (string.contains(PGN_CASTLE_K)) return true;
        return false;
    }

    private static boolean isLongCastling(String string) {
        if (string.contains(PGN_CASTLE_Q)) return true;
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

    static void getStartCords(PGNGame game, int endX, int endY) throws Exception {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // if spot isn't null
                if (!game.getBoard().getBox(i, j).isSpotNull()) {
                    Piece startPiece = game.getBoard().getBox(i, j).getPiece();
                    // if piece has right color
                    if (startPiece.isWhite() == game.getCurrentTurn().isWhiteSide()) {
                        // if piece has a right type
                        if (isRightTypeOfPiece(startPiece)) {
                            Spot startSpot = game.getBoard().getBox(i, j);
                            Spot endSpot = game.getBoard().getBox(endX, endY);
                            if (startPiece.canMove(game.getBoard(), startSpot, endSpot)) {
                                startX = i;
                                startY = j;
                                // LOG.info("Start x cord is: " + startX);
                                // LOG.info("Start y cord is: " + startY);
                            }
                        }
                    }
                }
            }
        }
    }

    static void getStartCordsWithY(PGNGame game, int endX, int endY, int startY) throws Exception {
        for (int i = 0; i < 8; i++) {
            // if spot isn't null
            if (!game.getBoard().getBox(i, startY).isSpotNull()) {
                Piece startPiece = game.getBoard().getBox(i, startY).getPiece();
                // if piece has right color
                if (startPiece.isWhite() == game.getCurrentTurn().isWhiteSide()) {
                    // if piece has a right type
                    if (isRightTypeOfPiece(startPiece)) {
                        Spot startSpot = game.getBoard().getBox(i, startY);
                        Spot endSpot = game.getBoard().getBox(endX, endY);
                        if (startPiece.canMove(game.getBoard(), startSpot, endSpot)) {
                            startX = i;
                            // Log.turnLogOn();
                            LOG.info("Start x cord is: " + startX);
                            LOG.info("Start y cord is: " + startY);
                            // Log.turnLogOff();
                        }
                    }
                }
            }
        }
    }

    /**
     * If instanceof startPiece == type, return true.
     *
     * @param startPiece
     * @return
     */
    private static boolean isRightTypeOfPiece(Piece startPiece) {
        if (type == 'K' && startPiece instanceof King) return true;
        if (type == 'Q' && startPiece instanceof Queen) return true;
        if (type == 'N' && startPiece instanceof Knight) return true;
        if (type == 'B' && startPiece instanceof Bishop) return true;
        if (type == 'R' && startPiece instanceof Rook) return true;
        if (type == 'p' && startPiece instanceof Pawn) return true;
        return false;
    }


}
