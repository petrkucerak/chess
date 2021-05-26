package cz.cvut.fel.pjv.PGN;

import cz.cvut.fel.pjv.model.Game;
import cz.cvut.fel.pjv.model.Player.Player;

import java.util.ArrayList;
import java.util.Date;

public class PGNGame extends Game {

    private GameStatus finalStatus;
    private int finalGameRound;

    public void initGame(Player p1, Player p2, Date startDate) {
        super.initGame(p1, p2);
        this.setStartDate(startDate);
    }

    public GameStatus getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(GameStatus finalStatus) {
        this.finalStatus = finalStatus;
    }

    public int getFinalGameRound() {
        return finalGameRound;
    }

    public void setFinalGameRound(int finalGameRound) {
        this.finalGameRound = finalGameRound;
    }
}
