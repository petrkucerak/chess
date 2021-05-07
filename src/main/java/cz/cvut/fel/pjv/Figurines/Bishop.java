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
        setAllPositionFalse(possibleMoves);
        // set positive moves
        int tmp_y = y + 1;
        int tmp_x = x + 1;
        while(tmp_x < possibleMoves.length && tmp_y < possibleMoves.length){
            possibleMoves[tmp_y][tmp_x] = true;
            tmp_x ++;
            tmp_y ++;
        }
        tmp_y = y - 1;
        tmp_x = x + 1;
        while(tmp_x < possibleMoves.length && tmp_y >= 0){
            possibleMoves[tmp_y][tmp_x] = true;
            tmp_x ++;
            tmp_y --;
        }
        tmp_y = y - 1;
        tmp_x = x - 1;
        while(tmp_x >= 0 && tmp_y >= 0){
            possibleMoves[tmp_y][tmp_x] = true;
            tmp_x --;
            tmp_y --;
        }
        tmp_y = y + 1;
        tmp_x = x - 1;
        while(tmp_x >= 0 && tmp_y < possibleMoves.length){
            possibleMoves[tmp_y][tmp_x] = true;
            tmp_x --;
            tmp_y ++;
        }
        return possibleMoves;
    }
}
