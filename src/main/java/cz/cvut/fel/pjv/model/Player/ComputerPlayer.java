package cz.cvut.fel.pjv.model.Player;

/**
 * Class representation of the computer player.
 */
public class ComputerPlayer extends Player {

    private String name;

    public ComputerPlayer(boolean whiteSide) {
        this.whiteSide = whiteSide;
        this.humanPlayer = false;
        this.name = "Computer, Player";
    }

    @Override
    public String toString() {
        return "Computer, Player";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
