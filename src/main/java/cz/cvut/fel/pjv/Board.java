package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.Piece.*;

/**
 * Class representations Chessboard as a 2D array.
 */
public class Board {
    Spot[][] boxes;

    public Board() {
        this.newBoard();
    }

    public Spot getBox(int x, int y) throws Exception {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new Exception("Index out of Chessboard!");
        }
        return this.boxes[x][y];
    }

    public void newBoard() {
        this.boxes = new Spot[8][8];
        // place white pieces to spots
        boxes[0][0] = new Spot(new Rook(true), 0, 0);
        boxes[0][1] = new Spot(new Knight(true), 0, 1);
        boxes[0][2] = new Spot(new Bishop(true), 0, 2);
        boxes[0][3] = new Spot(new Queen(true), 0, 3);
        boxes[0][4] = new Spot(new King(true), 0, 4);
        boxes[0][5] = new Spot(new Bishop(true), 0, 5);
        boxes[0][6] = new Spot(new Knight(true), 0, 6);
        boxes[0][7] = new Spot(new Rook(true), 0, 7);
        for (int i = 0; i < boxes[1].length; i++) {
            boxes[1][i] = new Spot(new Pawn(true), 1, i);
        }
        // place black pieces to spots
        boxes[7][0] = new Spot(new Rook(false), 7, 0);
        boxes[7][1] = new Spot(new Knight(false), 7, 1);
        boxes[7][2] = new Spot(new Bishop(false), 7, 2);
        boxes[7][3] = new Spot(new Queen(false), 7, 3);
        boxes[7][4] = new Spot(new King(false), 7, 4);
        boxes[7][5] = new Spot(new Bishop(false), 7, 5);
        boxes[7][6] = new Spot(new Knight(false), 7, 6);
        boxes[7][7] = new Spot(new Rook(false), 7, 7);
        for (int i = 0; i < boxes[1].length; i++) {
            boxes[6][i] = new Spot(new Pawn(false), 6, i);
        }

    }
}
