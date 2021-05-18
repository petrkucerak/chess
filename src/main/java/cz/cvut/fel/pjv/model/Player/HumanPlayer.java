package cz.cvut.fel.pjv.model.Player;

/**
 * Class representation of the human player.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(boolean whiteSide) {
        this.whiteSide = whiteSide;
        this.humanPlayer = true;
    }

    @Override
    public String toString() {
        return "HumanPlayer{" +
                "whiteSide=" + whiteSide +
                ", humanPlayer=" + humanPlayer +
                '}';
    }
}
