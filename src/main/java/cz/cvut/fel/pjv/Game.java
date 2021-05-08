package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.Piece.Piece;
import cz.cvut.fel.pjv.Player.Player;

import java.util.List;
import java.util.Scanner;

/**
 * Includes mechanism of the game
 * controller
 */
public class Game {
    private Player[] players;
    private Board board;
    private Player currentTurn;
    private GameStatus status;
    private List<Move> movesPlayed;

    private void initGame(Player p1, Player p2){
        players[0] = p1;
        players[1] = p2;

        board.newBoard();

        if(p1.isWhiteSide()){
            this.currentTurn = p1;
        } else {
            this.currentTurn = p2;
        }

        movesPlayed.clear();
    }

    public boolean isEnd(){
        return this.getStatus() != GameStatus.ACTIVE;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) throws Exception {
        Spot startBox = board.getBox(startX, startY);
        Spot endBox = board.getBox(endX, endY);
        Move move = new Move(player, startBox, endBox);
        return this.makeMove(move, player);
    }

    private boolean makeMove(Move move, Player player) {
        Piece sourcePiece = move.getStart().getPiece();
        if (sourcePiece == null){
            return false;
        }

        // valid player
        if(player != currentTurn){
            return false;
        }
        if(sourcePiece.isWhite() != player.isWhiteSide()){
            return false;
        }

        // valid move
        if(!sourcePiece.canMove(board, move.getStart(), move.getEnd())){
            return false;
        }
    }

    public enum GameStatus {
        ACTIVE,
        BLACK_WIN,
        WHITE_WIN,
        FORFEIT,
        STALEMATE,
        RESIGNATION
    }

}
