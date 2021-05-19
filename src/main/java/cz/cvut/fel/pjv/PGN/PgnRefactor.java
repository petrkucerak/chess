package cz.cvut.fel.pjv.PGN;

import cz.cvut.fel.pjv.model.Game;
import cz.cvut.fel.pjv.model.Move;

import java.util.ArrayList;
import java.util.Collection;

public interface PgnRefactor {

    Collection<String> convertMoveToPgn(ArrayList<Move> movesPlayed);

    String exportGame(Game game);

}
