package cz.cvut.fel.pjv;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class PanePiece extends Pane {
    private int x;
    private int y;

    public PanePiece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PanePiece(int x, int y, Node... nodes) {
        super(nodes);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
