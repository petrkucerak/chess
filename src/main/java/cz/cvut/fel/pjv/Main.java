package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.Player.ComputerPlayer;
import cz.cvut.fel.pjv.Player.HumanPlayer;
import cz.cvut.fel.pjv.Player.Player;

import java.util.Scanner;

public class Main {
    static public void main(String[] args) throws Exception {
        System.out.println("Testing has been started!");

        // inti game
        Game game = new Game();
        Player human = new HumanPlayer(true);
        Player computer = new ComputerPlayer(false);
        game.initGame(human, computer);

        game.printGameInfo();


        // init Scanner for testing
        Scanner sc = new Scanner(System.in);

        // run round
        while (true){
            // play white
            int startX, startY, endX, endY;

            System.out.println("Set origin coords");
            System.out.println("x:");
            startX = sc.nextInt();
            System.out.println("y:");
            startY = sc.nextInt();

            System.out.println("Set new coords");
            System.out.println("x:");
            endX = sc.nextInt();
            System.out.println("y:");
            endY = sc.nextInt();

            game.playerMove(human, startX, startY, endX, endY);

            // print game status
            game.printGameInfo();

            // play black player
        }
    }
}
