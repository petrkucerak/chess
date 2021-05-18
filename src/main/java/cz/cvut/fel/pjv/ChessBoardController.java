package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.ControlerUtils.PanePiece;
import cz.cvut.fel.pjv.ControlerUtils.TextPiece;
import cz.cvut.fel.pjv.model.Player.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * Class to representation game controller.
 */
public class ChessBoardController {

    private int[] turnPositions;

    @FXML
    private GridPane grid;

    /**
     * Initialize windows with the Chess
     *
     * @throws Exception
     */
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
     * Action when user click on the pane with the piece.
     */
    EventHandler<MouseEvent> piecePaneClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            PanePiece pane = (PanePiece) mouseEvent.getSource();
            System.out.println(pane.getCordX() + " " + pane.getCordY());
            if (turnPositions[0] == -1) {
                turnPositions[0] = pane.getCordX();
                turnPositions[1] = pane.getCordY();
            } else {
                try {
                    playerMove(turnPositions[0], turnPositions[1], pane.getCordX(), pane.getCordY());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                annularMovesArray();
                try {
                    MainApp.updateScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * Action when user click on the text element with piece.
     */
    EventHandler<MouseEvent> pieceTextClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            TextPiece piece = (TextPiece) mouseEvent.getSource();
            System.out.println(piece.getCordX() + " " + piece.getCordY());
            if (turnPositions[0] == -1) {
                turnPositions[0] = piece.getCordX();
                turnPositions[1] = piece.getCordY();
            } else {
                try {
                    playerMove(turnPositions[0], turnPositions[1], piece.getCordX(), piece.getCordY());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                annularMovesArray();
                try {
                    MainApp.updateScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * Add custom pane with cords to grid.
     *
     * @param colIndex
     * @param rowIndex
     */
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
        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, piecePaneClickHandler);
        // add pane to the grid
        grid.add(pane, colIndex, rowIndex);
    }

    /**
     * Methode sets icons of pieces to the chessboard.
     *
     * @throws Exception
     */
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

    /**
     * Methode add current piece with color formate into the chessboard with event handler.
     *
     * @param colIndex
     * @param rowIndex
     * @param name
     * @throws Exception
     */
    private void addPiece(int colIndex, int rowIndex, String name) throws Exception {
        // create custom text instance
        TextPiece piece = new TextPiece(name, rowIndex, colIndex);
        piece.setText(name);
        // format
        piece.setStyle("-fx-font-size: 40px;");
        if (MainApp.getGame().getBoard().getBox(rowIndex, colIndex).getPiece().isWhite()) {
            piece.setFill(Color.GREEN);
            piece.setStroke(Color.BLACK);
        } else {
            piece.setFill(Color.RED);
            piece.setStroke(Color.BLACK);
        }
        // add event
        piece.addEventFilter(MouseEvent.MOUSE_CLICKED, pieceTextClickHandler);
        // add pane to the grid
        grid.add(piece, colIndex, rowIndex);

    }

    /**
     * Method processed player move.
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @throws Exception
     */
    private void playerMove(int startX, int startY, int endX, int endY) throws Exception {
        System.out.println("All move is: " + startX + " " + startY + " " + endX + " " + endY);

        Player player = MainApp.getGame().getCurrentTurn();
        MainApp.getGame().playerMove(player, startX, startY, endX, endY);
    }

    /**
     * Button to start new game.
     * @throws IOException
     */
    @FXML
    private void newGame() throws IOException {
        System.out.println("\n ==================================== \n\n");
        System.out.println("NEW GAME");
        MainApp.newGame();
        MainApp.updateScene();
    }

    @FXML
    private void saveGame(ActionEvent actionEvent) throws IOException {
        System.out.println("SAVE GAME");
    }

    @FXML
    private void loadGame(ActionEvent actionEvent) throws IOException {
        System.out.println("LOAD GAME");
    }

    /**
     * Internal support methode for annulation tmp string with moves.
     */
    private void annularMovesArray() {
        for (int i = 0; i < turnPositions.length; i++) {
            turnPositions[i] = -1;
        }
    }

}
