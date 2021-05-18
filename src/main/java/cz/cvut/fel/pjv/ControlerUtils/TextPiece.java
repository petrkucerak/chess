package cz.cvut.fel.pjv.ControlerUtils;

import javafx.scene.text.Text;

public class TextPiece extends Text {
    private int cordX;
    private int cordY;

    public TextPiece(int cordX, int cordY) {
        this.cordX = cordX;
        this.cordY = cordY;
    }

    public TextPiece(String s, int cordX, int cordY) {
        super(s);
        this.cordX = cordX;
        this.cordY = cordY;
    }

    public TextPiece(double v, double v1, String s, int cordX, int cordY) {
        super(v, v1, s);
        this.cordX = cordX;
        this.cordY = cordY;
    }

    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    public void setCordY(int cordY) {
        this.cordY = cordY;
    }

    public int getCordX() {
        return cordX;
    }

    public int getCordY() {
        return cordY;
    }
}
