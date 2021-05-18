package cz.cvut.fel.pjv.ControlerUtils;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class PanePiece extends Pane {
    private int cordX;
    private int cordY;

    public PanePiece(int x, int y) {
        this.cordX = x;
        this.cordY = y;
    }

    public PanePiece(int x, int y, Node... nodes) {
        super(nodes);
        this.cordX = x;
        this.cordY = y;
    }

    public int getCordX() {
        return cordX;
    }

    public int getCordY() {
        return cordY;
    }

    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    public void setCordY(int cordY) {
        this.cordY = cordY;
    }
}
