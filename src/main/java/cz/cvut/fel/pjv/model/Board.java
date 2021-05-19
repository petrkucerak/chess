package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.model.Pieces.*;

import static cz.cvut.fel.pjv.model.Pieces.Piece.BACK;
import static cz.cvut.fel.pjv.model.Pieces.Piece.RESET;

/**
 * Class representations Chessboard and support methods
 */
public class Board {
    Spot[][] boxes;
    private boolean activeCheckingIsKingInDanger;

    /**
     * For create new chessboard use newBoard function
     */
    public Board() {
        this.newBoard(); // chose the chessboard
        this.activeCheckingIsKingInDanger = false;
    }

    public void setActiveCheckingIsKingInDanger(boolean activeCheckingIsKingInDanger) {
        this.activeCheckingIsKingInDanger = activeCheckingIsKingInDanger;
    }

    public boolean isActiveCheckingIsKingInDanger() {
        return activeCheckingIsKingInDanger;
    }

    /**
     * Create new chessboard.
     *
     * @param board
     */
    public Board(Spot[][] board) {
        this.boxes = new Spot[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() == null) {
                    this.boxes[i][j] = new Spot(null, i, j);
                } else {
                    this.boxes[i][j] = new Spot(board[i][j].getPiece(), i, j);
                }
            }
        }
    }

    public Spot[][] getBoxes() {
        return boxes;
    }

    public void setBoxes(Spot[][] boxes) {
        this.boxes = boxes;
    }

    /**
     * Return spot on chessboard if exist.
     *
     * @param x coordinate
     * @param y coordinate
     * @return
     * @throws Exception
     */
    public Spot getBox(int x, int y) throws Exception {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new Exception("Index out of Chessboard!");
        }
        return this.boxes[x][y];
    }

    /**
     * Equal pieces in 2 chessboards.
     *
     * @param board
     * @return
     * @throws Exception
     */
    public boolean isBoardEqual(Board board) throws Exception {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.boxes[i][j].getPiece() == null && board.getBox(i, j).getPiece() == null) {
                    continue;
                } else if (this.boxes[i][j].getPiece() == null && board.getBox(i, j).getPiece() != null) {
                    return false;
                } else if (this.boxes[i][j].getPiece() != null && board.getBox(i, j).getPiece() == null) {
                    return false;
                } else if (this.boxes[i][j].getPiece().getClass().getName().equals(board.getBox(i, j).getPiece().getClass().getName())) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Initialization new chessboard with classic pieces position.
     */
    private void newBoard() {
        this.boxes = new Spot[8][8];
        // place white pieces to spots
        boxes[0][0] = new Spot(new Rook(false), 0, 0);
        boxes[0][1] = new Spot(new Knight(false), 0, 1);
        boxes[0][2] = new Spot(new Bishop(false), 0, 2);
        boxes[0][3] = new Spot(new Queen(false), 0, 3);
        boxes[0][4] = new Spot(new King(false), 0, 4);
        boxes[0][5] = new Spot(new Bishop(false), 0, 5);
        boxes[0][6] = new Spot(new Knight(false), 0, 6);
        boxes[0][7] = new Spot(new Rook(false), 0, 7);
        for (int i = 0; i < boxes[1].length; i++) {
            boxes[1][i] = new Spot(new Pawn(false), 1, i);
        }
        // place black pieces to spots
        boxes[7][0] = new Spot(new Rook(true), 7, 0);
        boxes[7][1] = new Spot(new Knight(true), 7, 1);
        boxes[7][2] = new Spot(new Bishop(true), 7, 2);
        boxes[7][3] = new Spot(new Queen(true), 7, 3);
        boxes[7][4] = new Spot(new King(true), 7, 4);
        boxes[7][5] = new Spot(new Bishop(true), 7, 5);
        boxes[7][6] = new Spot(new Knight(true), 7, 6);
        boxes[7][7] = new Spot(new Rook(true), 7, 7);
        for (int i = 0; i < boxes[1].length; i++) {
            boxes[6][i] = new Spot(new Pawn(true), 6, i);
        }
        // place other spots as null
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < boxes[i].length; j++) {
                boxes[i][j] = new Spot(null, i, j);
            }
        }

    }

    private void testBoardPromotion() {
        this.boxes = new Spot[8][8];
        // place other spots as null
        for (int i = 0; i < 8; i++) {
            if (i == 1) {
                for (int j = 0; j < boxes[i].length; j++) {
                    boxes[i][j] = new Spot((new Pawn(true)), i, j);
                }
            } else {
                for (int j = 0; j < boxes[i].length; j++) {
                    boxes[i][j] = new Spot(null, i, j);
                }
            }

        }

    }

    private void testBoardCastling() {
        this.boxes = new Spot[8][8];
        // place white pieces to spots
        boxes[0][0] = new Spot(new Rook(false), 0, 0);
        boxes[0][1] = new Spot(null, 0, 1);
        boxes[0][2] = new Spot(null, 0, 2);
        boxes[0][3] = new Spot(null, 0, 3);
        boxes[0][4] = new Spot(new King(false), 0, 4);
        boxes[0][5] = new Spot(null, 0, 5);
        boxes[0][6] = new Spot(null, 0, 6);
        boxes[0][7] = new Spot(new Rook(false), 0, 7);
        for (int i = 0; i < boxes[1].length; i++) {
            boxes[1][i] = new Spot(new Pawn(false), 1, i);
        }
        // place black pieces to spots
        boxes[7][0] = new Spot(new Rook(true), 7, 0);
        boxes[7][1] = new Spot(null, 7, 1);
        boxes[7][2] = new Spot(null, 7, 2);
        boxes[7][3] = new Spot(null, 7, 3);
        boxes[7][4] = new Spot(new King(true), 7, 4);
        boxes[7][5] = new Spot(null, 7, 5);
        boxes[7][6] = new Spot(null, 7, 6);
        boxes[7][7] = new Spot(new Rook(true), 7, 7);
        for (int i = 0; i < boxes[1].length; i++) {
            boxes[6][i] = new Spot(new Pawn(true), 6, i);
        }
        // place other spots as null
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < boxes[i].length; j++) {
                boxes[i][j] = new Spot(null, i, j);
            }
        }

    }

    public void testRepetition() {
        this.boxes = new Spot[8][8];
        // place other spots as null
        for (int i = 0; i < 8; i++) {
            if (i == 1) {
                for (int j = 0; j < boxes[i].length - 2; j++) {
                    boxes[i][j] = new Spot(null, i, j);
                }
            } else {
                for (int j = 0; j < boxes[i].length; j++) {
                    boxes[i][j] = new Spot(null, i, j);
                }
            }
            boxes[1][6] = new Spot(new Queen(true), 1, 6);
            boxes[1][7] = new Spot(new Queen(false), 1, 7);

        }

    }

    public void testKingInDanger() {
        this.boxes = new Spot[8][8];
        // place other spots as null
        boxes[2][6] = new Spot(new Queen(true), 2, 6);
        boxes[1][7] = new Spot(new King(true), 1, 7);
        boxes[3][5] = new Spot(new Bishop(false), 3, 5);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boxes[i][j] == null) {
                    boxes[i][j] = new Spot(null, i, j);
                }
            }
        }

    }

    /**
     * Print the chessboard
     */
    public void printBoard() {
        for (int i = 0; i < boxes.length; i++) {
            System.out.print(i + ". ");
            for (int j = 0; j < boxes[i].length; j++) {
                if (boxes[i][j].getPiece() != null) {
                    System.out.print(boxes[i][j].getPiece());
                } else {
                    System.out.print(BACK + "♟");
                }
                if (j - 1 != boxes[i].length) {
                    System.out.print(" ");
                }
            }
            System.out.println(RESET);
        }
    }
}
