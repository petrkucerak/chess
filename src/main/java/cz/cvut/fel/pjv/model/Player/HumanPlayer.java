package cz.cvut.fel.pjv.model.Player;

/**
 * Class representation of the human player.
 */
public class HumanPlayer extends Player {

    private String name;

    public HumanPlayer(boolean whiteSide) {
        this.whiteSide = whiteSide;
        this.humanPlayer = true;
        this.name = "Human, Player";
    }

    @Override
    public String toString() {
        return "Human, Player";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
