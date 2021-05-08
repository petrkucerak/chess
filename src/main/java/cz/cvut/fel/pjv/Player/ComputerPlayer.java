package cz.cvut.fel.pjv.Player;

public class ComputerPlayer extends Player {

    public ComputerPlayer(boolean whiteSide) {
        this.whiteSide = whiteSide;
        this.humanPlayer = false;
    }

    @Override
    public String toString() {
        return "ComputerPlayer{" +
                "whiteSide=" + whiteSide +
                ", humanPlayer=" + humanPlayer +
                '}';
    }
}
