package cz.cvut.fel.pjv.Pieces;

import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

public class Bishop extends Piece {

    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // protect spot with same color piece
        if (isMyPieceInTheWay(end)) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        if(x == y){
            // check no piece between start and end position
            if(isNotPiecesOnTheWay(board, start, end)){
                return true;
            }
        }

        return false;
    }

    private boolean isNotPiecesOnTheWay(Board board, Spot start, Spot end){
        // terminate arrow
        int x = start.getX() - end.getX();
        int y = start.getY() - end.getY();

        // upper left
        if(x>0 && y>0){

        }
        // down left
        if(x<0 && y>0){

        }
        // up right
        if(x>0 && y<0){

        }
        // down right
        if(x<0 && y<0){

        }

        return true;
    }

    @Override
    public String toString() {
        if(this.isWhite()){
            return BLACK + "♗";
        } else {
            return WHITE + "♗";
        }
    }

}
