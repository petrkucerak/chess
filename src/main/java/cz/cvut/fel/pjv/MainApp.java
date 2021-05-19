package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.PGN.PgnFormats;
import cz.cvut.fel.pjv.PGN.PgnRefactorImplement;
import cz.cvut.fel.pjv.TimeClock.TheClock;
import cz.cvut.fel.pjv.model.Game;
import cz.cvut.fel.pjv.model.Player.ComputerPlayer;
import cz.cvut.fel.pjv.model.Player.HumanPlayer;
import cz.cvut.fel.pjv.model.Player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * JavaFX Chess App
 */
public class MainApp extends Application {

    private static Scene scene;
    private static Game game;
    private static Stage stage;
    private static TheClock timer;
    private static Thread time;

    /**
     * Void launches game.
     *
     * @param args
     */
    public static void main(String[] args) {
        // launch visible scene
        launch();
    }

    /**
     * Void launches stage with chessboard.
     *
     * @param stage
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {

        this.stage = stage;
        // set game parameters
        System.out.println("Testing has been started!");

        newGame();
        updateScene();

    }

    /**
     * Method to load FXML file.
     *
     * @param fxml
     * @return
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Scene getScene() {
        return scene;
    }

    public static Game getGame() {
        return game;
    }

    public static void setScene(Scene scene) {
        MainApp.scene = scene;
    }

    public static void setGame(Game game) {
        MainApp.game = game;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        MainApp.stage = stage;
    }

    /**
     * Methode to update scene on stage.
     *
     * @throws IOException
     */
    public static void updateScene() throws IOException {
        stage.setScene(new Scene(loadFXML("ChessBoard"), 640, 800));
        stage.show();
        game.printGameInfo();
        changeTimer();
    }

    /**
     * Methode to start new game.
     *
     * @throws IOException
     */
    public static void newGame() throws IOException {
        game = new Game();
        Player human = new HumanPlayer(true);
        Player computer = new ComputerPlayer(false);
        game.initGame(human, computer);
        game.setTimeLefts(100000);

    }

    public static void changeTimer() {
        // if process in progress, kill it
        if (time != null) {
            if (time.isAlive()) {
                if(game.getGameRound() % 2 == 1){
                    game.setTimeLefts(timer.getTimeLefts(), 1);
                } else {
                    game.setTimeLefts(timer.getTimeLefts(), 0);
                }
                timer.setTimeLefts(-1);

            }
        }

        // get current time value
        int timeLefts;
        if (game.getGameRound() % 2 == 1) {
            timeLefts = game.getTimeLefts()[0];
        } else {
            timeLefts = game.getTimeLefts()[1];
        }

        if(timeLefts > 0) {
            // init chess clock
            timer = new TheClock(timeLefts);
            time = new Thread(timer);
            time.setDaemon(true);
            time.start();
        }
    }

    public static void exportPgn(){
        // testing pgn event
        PgnRefactorImplement pgn = new PgnRefactorImplement();
        System.out.println(pgn.exportGame(game));
    }

}