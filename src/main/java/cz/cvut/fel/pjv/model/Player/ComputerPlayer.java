package cz.cvut.fel.pjv.model.Player;

/**
 * Class representation of the computer player.
 */
public class ComputerPlayer extends Player {


    public ComputerPlayer(boolean whiteSide) {
        this.whiteSide = whiteSide;
        this.humanPlayer = false;
        this.setName("Computer, Player");
    }

    @Override
    public String toString() {
        return "Computer, Player";
    }

}
