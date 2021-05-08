package cz.cvut.fel.pjv.Piece;

import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

public class Pawn extends Piece {

    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // protect spot with same color piece
        if(isMyPieceInTheWay(end)){
            return false;
        }

        /*
        ToDo:
         1. normalni krok -> nesmi tam byt nepratel
         2. krok o dva -> nesmi tam byt nepratel
         3. brani nepratele -> musi tam byt nepratel
         4. brani mimochodem
         5. projit na konec gameboard
         */

        int x;
        if(start.getPiece().isWhite()){
            x = end.getX() - start.getX();
        } else {
            x = start.getX() - end.getX();
        }
        int y = Math.abs(start.getY() - end.getY());

        // classic pawn move
        // protect spot with opposite piece
        if(isColorPiecesSame(start, end) || end.getPiece() == null){
            if(x == 1 && y == 0){
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        if(this.isWhite()){
            return BLACK + "♙";
        } else {
            return WHITE + "♙";
        }
    }


}
