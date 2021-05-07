package cz.cvut.fel.pjv.Figurines;

import cz.cvut.fel.pjv.Chessboard;

/**
 * Class for manipulations with Figurines.
 */
public abstract class Figurine implements java.io.Serializable {
    private char color;
    private String codeName;

    public Figurine(char color, String codeName) {
        this.color = color;
        this.codeName = codeName;
    }

    public String getCodeName() {
        return codeName;
    }

    public char getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color + " " + codeName;
    }

    /**
     * Function calculation possible figurine moves
     * @param x chord
     * @param y chord
     * @return true - possible move; false - not possible move
     */
    public abstract boolean[][] possibleMoves(int x, int y);

    /**
     * Set all position as false in possible moves array
     * @param array of possible moves
     */
    public static void setAllPositionFalse(boolean[][] array){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                array[i][j] = false;
            }
        }
    }
}

