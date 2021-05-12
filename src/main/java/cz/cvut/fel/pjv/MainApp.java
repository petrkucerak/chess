package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.model.Board;
import cz.cvut.fel.pjv.model.Game;
import cz.cvut.fel.pjv.model.Player.ComputerPlayer;
import cz.cvut.fel.pjv.model.Player.HumanPlayer;
import cz.cvut.fel.pjv.model.Player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * JavaFX Chess App
 */
public class MainApp extends Application {

    private static Scene scene;
    private static Game game;

    public static void main(String[] args) {
        // launch visible scene
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {

        // set game parameters
        System.out.println("Testing has been started!");

        // inti game
        game = new Game();
        Player human = new HumanPlayer(true);
        Player computer = new ComputerPlayer(false);
        game.initGame(human, computer);

        game.printGameInfo();

        scene = new Scene(loadFXML("ChessBoard"), 640, 800);
        stage.setScene(scene);
        stage.show();

    }

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
}