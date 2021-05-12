package cz.cvut.fel.pjv;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ChessBoardController {

    @FXML
    GridPane gridPane;

    @FXML
    private void newGame() throws IOException {
        System.out.println("NEW GAME");
    }

    @FXML
    private void getPosition() throws IOException {
        System.out.println();
    }

    @FXML
    private void saveGame(ActionEvent actionEvent) {
        System.out.println("SAVE GAME");
    }

    @FXML
    private void loadGame(ActionEvent actionEvent) {
        System.out.println("LOAD GAME");
    }
}
