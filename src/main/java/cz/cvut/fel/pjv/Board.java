package cz.cvut.fel.pjv;

/**
 * Class representations Chessboard as a 2D array.
 */
public class Board {
    Spot[][] boxes;

    public Board() {
        this.newBoard();
    }

    public Spot getBox(int x, int y) throws Exception {
        if(x < 0 || x > 7 || y < 0 || y > 7){
            throw new Exception("Index out of Chessboard!");
        }
        return this.boxes[x][y];
    }

    public void newBoard(){

    }
}
