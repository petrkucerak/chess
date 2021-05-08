package cz.cvut.fel.pjv.Piece;

import cz.cvut.fel.pjv.Board;
import cz.cvut.fel.pjv.Spot;

public class Pawn extends Piece {

    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        return false;
    }

    @Override
    public String toString() {
        if(this.isWhite() == true){
            return "*P";
        } else {
            return "+P";
        }
    }


}
