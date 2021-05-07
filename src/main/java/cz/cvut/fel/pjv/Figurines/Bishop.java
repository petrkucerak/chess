package cz.cvut.fel.pjv.Figurines;

/**
 * Class for the Bishop figurine
 */
public class Bishop extends Figurine {

    public Bishop(char color) {
        super(color, "Bishop");
    }


    @Override
    public boolean[][] possibleMoves(int x, int y) {
        // create new board
        boolean[][] possibleMoves = new boolean[8][8];
        // set all position as empty
        return possibleMoves;
    }
}
