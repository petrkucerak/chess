package cz.cvut.fel.pjv.model.Pieces;

import cz.cvut.fel.pjv.model.Board;
import cz.cvut.fel.pjv.model.Spot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static cz.cvut.fel.pjv.model.Pieces.Piece.BLACK;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing rook valid moves.
 */
class RookTest {

    private final Rook rookDark = new Rook(false);
    private final Rook rookWhite = new Rook(true);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void canMove() throws Exception {
        assertEquals(false, rookWhite.canMove(new Board(), new Spot(new Rook(true), 0,0), new Spot(null, 0, 5)));
    }

    @Test
    void getPieceSymbol() {
        assertEquals("♜", rookDark.getPieceSymbol());
    }

    @Test
    void testToString() {
        assertEquals(BLACK + "♖", rookWhite.toString());
    }
}