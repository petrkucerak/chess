package cz.cvut.fel.pjv.PGN;

import cz.cvut.fel.pjv.model.Game;
import cz.cvut.fel.pjv.model.Move;

import java.util.ArrayList;

public interface PgnRefactor {

    String convertMoveToPgn(ArrayList<Move> movesPlayed);

    String exportMetadata(Game game);

    String exportGame(Game game);

}
