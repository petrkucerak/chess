package cz.cvut.fel.pjv.Figurines;

import cz.cvut.fel.pjv.Chessboard;

/**
 * Class for manipulations with Figurines.
 */
public abstract class Figurine {
    private char color;
    private String codeName;

    public Figurine(char color, String codeName) {
        this.color = color;
        this.codeName = codeName;
    }
}

