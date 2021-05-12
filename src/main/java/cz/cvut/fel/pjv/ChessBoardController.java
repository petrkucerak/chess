package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.model.Board;
import cz.cvut.fel.pjv.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.IOException;

public class ChessBoardController {

    @FXML
    private GridPane grid;


    public void initialize() throws Exception {

        int numCols = 8 ;
        int numRows = 8 ;

        for (int i = 0 ; i < numCols ; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.SOMETIMES);
            grid.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0 ; i < numRows ; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            grid.getRowConstraints().add(rowConstraints);
        }

        for (int i = 0 ; i < numCols ; i++) {
            for (int j = 0; j < numRows; j++) {
                addPane(i, j);
            }
        }
        setChessBoard();

    }

    private void addPane(int colIndex, int rowIndex) {
        Pane pane = new Pane();
        if((colIndex%2 == 0 && rowIndex%2 == 0) || (colIndex%2 == 1 && rowIndex%2 == 1)){
            pane.setStyle("-fx-background-color: #F3EACF;");
        } else {
            pane.setStyle("-fx-background-color: #774E24;");
        }
        pane.setOnMouseClicked(e ->{
            System.out.printf("Mouse enetered cell [%d, %d]%n", colIndex, rowIndex);
        });
        grid.add(pane, colIndex, rowIndex);
    }

    public void setChessBoard() throws Exception {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(!MainApp.getGame().getBoard().getBox(j,i).isSpotNull()) {
                    String name = MainApp.getGame().getBoard().getBox(j, i).getPiece().toString();
                    Text piece = new Text();
                    piece.setText(name);
                    grid.add(piece, i, j);
                }
            }
        }
    }

    @FXML
    private void newGame() throws IOException {
        System.out.println("NEW GAME");
    }

    @FXML
    private void saveGame(ActionEvent actionEvent) throws IOException {
        System.out.println("SAVE GAME");
    }

    @FXML
    private void loadGame(ActionEvent actionEvent) throws IOException {
        System.out.println("LOAD GAME");
    }
}
