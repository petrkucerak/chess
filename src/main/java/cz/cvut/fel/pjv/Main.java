package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.Player.ComputerPlayer;
import cz.cvut.fel.pjv.Player.HumanPlayer;
import cz.cvut.fel.pjv.Player.Player;

import java.util.Scanner;

import static cz.cvut.fel.pjv.Pieces.Piece.BLACK;
import static cz.cvut.fel.pjv.Pieces.Piece.RESET;
import static cz.cvut.fel.pjv.Pieces.Piece.WHITE;

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
        while (true) {
            int startX, startY, endX, endY;
            boolean clearMove = false;

            // play white
            while (!clearMove) {
                System.out.println(BLACK + "WHITE MOVE" + RESET);
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

                clearMove = game.playerMove(human, startX, startY, endX, endY);

                // print game status
                game.printGameInfo();
            }

            clearMove = false;
            // play black player
            while (!clearMove) {
                System.out.println(WHITE + "BLACK MOVE" + RESET);
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

                clearMove = game.playerMove(computer, startX, startY, endX, endY);

                // print game status
                game.printGameInfo();
            }
        }
    }
}
