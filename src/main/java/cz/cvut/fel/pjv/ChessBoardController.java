package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.model.Player.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class ChessBoardController {

    private int[] turnPositions;

    @FXML
    private GridPane grid;


    public void initialize() throws Exception {

        // init array with turn position
        turnPositions = new int[4];
        annularMovesArray();

        int numCols = 8;
        int numRows = 8;

        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.SOMETIMES);
            grid.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            grid.getRowConstraints().add(rowConstraints);
        }

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                addPane(i, j);
            }
        }
        grid.setMaxSize(560, 560);
        grid.setMinSize(560, 560);

        setChessBoard();

    }

    /**
     * Action when user click on the pane with the piece
     */
    EventHandler<MouseEvent> pieceClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            PanePiece pane = (PanePiece) mouseEvent.getSource();
            System.out.println(pane.getX() + " " + pane.getY());
            if (turnPositions[0] == -1) {
                turnPositions[0] = pane.getX();
                turnPositions[1] = pane.getY();
            } else {
                try {
                    playerMove(turnPositions[0], turnPositions[1], pane.getX(), pane.getY());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                annularMovesArray();
            }
        }
    };

    private void addPane(int colIndex, int rowIndex) {
        // create custom pane with info about coords
        PanePiece pane = new PanePiece(rowIndex, colIndex);
        // set style of pane
        if ((colIndex % 2 == 0 && rowIndex % 2 == 0) || (colIndex % 2 == 1 && rowIndex % 2 == 1)) {
            pane.setStyle("-fx-background-color: #F3EACF;");
        } else {
            pane.setStyle("-fx-background-color: #774E24;");
        }
        pane.setMaxSize(70, 70);
        pane.setMinSize(70, 70);
        // add event
        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, pieceClickHandler);
        // add pane to grid
        grid.add(pane, colIndex, rowIndex);
    }

    public void setChessBoard() throws Exception {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!MainApp.getGame().getBoard().getBox(j, i).isSpotNull()) {
                    String name = MainApp.getGame().getBoard().getBox(j, i).getPiece().getPieceSymbol();
                    addPiece(i, j, name);
                }
            }
        }
    }

    private void addPiece(int colIndex, int rowIndex, String name) throws Exception {
        Text piece = new Text();
        piece.setText(name);
        piece.setStyle("-fx-font-size: 40px;");
        // listen to user event
        /*piece.setOnMouseClicked(e -> {
            try {
                playerMove(colIndex, rowIndex);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });*/

        if (MainApp.getGame().getBoard().getBox(rowIndex, colIndex).getPiece().isWhite()) {
            piece.setFill(Color.GREEN);
            piece.setStroke(Color.BLACK);
        } else {
            piece.setFill(Color.RED);
            piece.setStroke(Color.BLACK);
        }
        grid.add(piece, colIndex, rowIndex);

    }

    // ToDo: validate move
    private void playerMove(int startX, int startY, int endX, int endY) throws Exception {
        System.out.println("All move is: " + startX + " " + startY + " " + endX + " " + endY);

        Player player = MainApp.getGame().getCurrentTurn();
        MainApp.getGame().playerMove(player, startX, startY, endX, endY);
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

    private void annularMovesArray(){
        for(int i = 0; i < turnPositions.length; i++){
            turnPositions[i] = -1;
        }
    }


}
