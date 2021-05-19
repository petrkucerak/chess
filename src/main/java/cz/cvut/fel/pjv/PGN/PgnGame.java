package cz.cvut.fel.pjv.PGN;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Model of pgn data.
 */
public class PgnGame {
    private String whitePlayerName;
    private String blackPlayerName;
    private Date gameDate;
    private String result;
    private String event;
    private String site;
    private String round;

    private List<String> moves;

    public PgnGame(String whitePlayerName, String blackPlayerName, Date gameDate, String results, String event, String site, String round, List<String> moves) {
        this.whitePlayerName = whitePlayerName;
        this.blackPlayerName = blackPlayerName;
        this.gameDate = gameDate;
        this.result = results;
        this.event = event;
        this.site = site;
        this.round = round;
        this.moves = moves;
    }

    public String getWhitePlayerName() {
        return whitePlayerName;
    }

    public String getBlackPlayerName() {
        return blackPlayerName;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public String getResult() {
        return result;
    }

    public String getEvent() {
        return event;
    }

    public String getSite() {
        return site;
    }

    public String getRound() {
        return round;
    }

    public List<String> getMoves() {
        return Collections.unmodifiableList(moves);
    }

    public void setWhitePlayerName(String whitePlayerName) {
        this.whitePlayerName = whitePlayerName;
    }

    public void setBlackPlayerName(String blackPlayerName) {
        this.blackPlayerName = blackPlayerName;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public void setMoves(List<String> moves) {
        this.moves = moves;
    }

    @Override
    public String toString() {
        return String.format("%s vs. %s (%d moves, result: %s)", whitePlayerName, blackPlayerName, moves.size(), result);
    }
}
