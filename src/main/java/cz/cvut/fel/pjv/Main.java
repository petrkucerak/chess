package cz.cvut.fel.pjv;

public class Main {
    static public void main(String []args) {
        System.out.println("Hello world!");
        Game testGame = new Game();
        testGame.generateClassicChessboard("src/main/resources/initClassicGame");
    }
}
/*
ToDo:
 1. hra, kde lze vypsat pole ✅
 2. pole s umistenim figurek ✅
 4. nacteni herni situace ze souboru ✅
 5. pohyb figurek
 6. pohyb figurek pomoci terminalu
 7. check pohybu figurek
 */