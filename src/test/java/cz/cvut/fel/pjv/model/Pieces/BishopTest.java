package cz.cvut.fel.pjv.model.Pieces;

import cz.cvut.fel.pjv.model.Board;
import cz.cvut.fel.pjv.model.Spot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void canMoveSamePosition() throws Exception {
        Board board = new Board(true);
        Piece sourcePiece = board.getBox(4,4).getPiece();

        Spot startSpot = board.getBox(4,4);
        Spot endSpot = board.getBox(4,4);

        boolean ret = sourcePiece.canMove(board, startSpot, endSpot);

        assertEquals(false, ret);
    }

    @Test
    void canMoveDiagonalLeftUp() throws Exception {
        Board board = new Board(true);
        Piece sourcePiece = board.getBox(4,4).getPiece();

        Spot startSpot = board.getBox(4,4);
        Spot endSpot = board.getBox(2,2);

        boolean ret = sourcePiece.canMove(board, startSpot, endSpot);

        assertEquals(true, ret);
    }

    @Test
    void canMoveDiagonalLeftDown() throws Exception {
        Board board = new Board(true);
        Piece sourcePiece = board.getBox(4,4).getPiece();

        Spot startSpot = board.getBox(4,4);
        Spot endSpot = board.getBox(6,2);

        boolean ret = sourcePiece.canMove(board, startSpot, endSpot);

        assertEquals(true, ret);
    }

    @Test
    void canMoveDiagonalRightUp() throws Exception {
        Board board = new Board(true);
        Piece sourcePiece = board.getBox(4,4).getPiece();

        Spot startSpot = board.getBox(4,4);
        Spot endSpot = board.getBox(1,7);

        boolean ret = sourcePiece.canMove(board, startSpot, endSpot);

        assertEquals(true, ret);
    }

    @Test
    void canMoveDiagonalRightDown() throws Exception {
        Board board = new Board(true);
        Piece sourcePiece = board.getBox(4,4).getPiece();

        Spot startSpot = board.getBox(4,4);
        Spot endSpot = board.getBox(7,7);

        boolean ret = sourcePiece.canMove(board, startSpot, endSpot);

        assertEquals(true, ret);
    }

    @Test
    void canMoveUp() throws Exception {
        Board board = new Board(true);
        Piece sourcePiece = board.getBox(4,4).getPiece();

        Spot startSpot = board.getBox(4,4);
        Spot endSpot = board.getBox(2,4);

        boolean ret = sourcePiece.canMove(board, startSpot, endSpot);

        assertEquals(false, ret);
    }

    @Test
    void canMoveDown() throws Exception {
        Board board = new Board(true);
        Piece sourcePiece = board.getBox(4,4).getPiece();

        Spot startSpot = board.getBox(4,4);
        Spot endSpot = board.getBox(7,4);

        boolean ret = sourcePiece.canMove(board, startSpot, endSpot);

        assertEquals(false, ret);
    }

    @Test
    void canMoveLeft() throws Exception {
        Board board = new Board(true);
        Piece sourcePiece = board.getBox(4,4).getPiece();

        Spot startSpot = board.getBox(4,4);
        Spot endSpot = board.getBox(4,1);

        boolean ret = sourcePiece.canMove(board, startSpot, endSpot);

        assertEquals(false, ret);
    }

    @Test
    void canMoveRight() throws Exception {
        Board board = new Board(true);
        Piece sourcePiece = board.getBox(4,4).getPiece();

        Spot startSpot = board.getBox(4,4);
        Spot endSpot = board.getBox(4,7);

        boolean ret = sourcePiece.canMove(board, startSpot, endSpot);

        assertEquals(false, ret);
    }

}