package cz.cvut.fel.pjv.model.Player;

import java.io.Serializable;

/**
 * Class representation abstract definition of players.
 */
public abstract class Player implements Serializable {
    public boolean whiteSide;
    public boolean humanPlayer;

    public boolean isWhiteSide() {
        return whiteSide;
    }

    public boolean isHumanPlayer() {
        return humanPlayer;
    }

    public void setWhiteSide(boolean whiteSide) {
        this.whiteSide = whiteSide;
    }

    public void setHumanPlayer(boolean humanPlayer) {
        this.humanPlayer = humanPlayer;
    }
}
