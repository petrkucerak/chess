package cz.cvut.fel.pjv.model.Pieces;

import cz.cvut.fel.pjv.model.Board;
import cz.cvut.fel.pjv.model.Spot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static cz.cvut.fel.pjv.model.Pieces.Piece.BLACK;
import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    private final Knight kingDark = new Knight(false);
    private final Knight kingWhite = new Knight(true);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void canMove() {
        assertEquals(true, kingDark.canMove(new Board(), new Spot(new Knight(false), 0, 1), new Spot(null, 2, 0)));
    }

    @Test
    void getPieceSymbol() {
        assertEquals("♞", kingDark.getPieceSymbol());
    }

    @Test
    void testToString() {
        assertEquals(BLACK + "♘", kingWhite.toString());
    }
}