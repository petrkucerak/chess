package cz.cvut.fel.pjv.PGN;

import cz.cvut.fel.pjv.model.Game;
import cz.cvut.fel.pjv.model.Move;

import java.util.ArrayList;

public class PgnRefactorImplement implements PgnRefactor {

    @Override
    public String convertMoveToPgn(ArrayList<Move> movesPlayed) {
        return null;
    }

    @Override
    public String exportMetadata(Game game) {
        return null;
    }

    @Override
    public String exportGame(Game game) {
        String ret = "";
        ret += exportMetadata(game);
        ret += "\n";
        ret += convertMoveToPgn(game.getMovesPlayed());
        return ret;
    }
}
