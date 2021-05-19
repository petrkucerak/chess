package cz.cvut.fel.pjv.PGN;

import cz.cvut.fel.pjv.MainApp;

import java.io.*;

public class PgnFileSaver {
    public static void saveGame(){
        PgnRefactor pgn = new PgnRefactor();
        String string = pgn.exportGame(MainApp.getGame());
        try{
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.pgn"), "UTF-8"));
            out.write(string);
            out.close();
        } catch (UnsupportedEncodingException e){
        } catch (IOException e){
        }
    }
}
