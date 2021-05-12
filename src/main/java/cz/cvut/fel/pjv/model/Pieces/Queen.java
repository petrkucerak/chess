package cz.cvut.fel.pjv.model.Pieces;

import cz.cvut.fel.pjv.model.Board;
import cz.cvut.fel.pjv.model.Spot;

public class Queen extends Piece {

    public Queen(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) throws Exception {

        // protect spot with same color piece
        if (isMyPieceInTheWay(end)){
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        if(x == y){
            // check no piece between start and end position
            // use Rook validate method
            if (isNotPiecesOnTheWayBishop(board, start, end)){
                return true;
            }
        }
        if((x == 0 && y > 0) || (x > 0 && y == 0)){
            // check no piece between start and end position
            // use Bishop validate method
            if (isNotPiecesOnTheWayRook(board, start, end)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if(this.isWhite()){
            return BLACK + "♕";
        } else {
            return WHITE + "♕";
        }
    }
}
