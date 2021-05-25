package cz.cvut.fel.pjv.PGN;

import cz.cvut.fel.pjv.MainApp;

import java.io.*;

public class PGNFileSave {
    public static void savePGNGame() {
        String str = "";
        str += MainApp.getGame().getPgnHeader();
        str += "\n";

        String line = "";
        String[] words = MainApp.getGame().getPgnMoves().split(" ");
        for (int i = 0; i < words.length; i++) {
            line += words[i];
            line += " ";
            if(line.length() > 70 && i % 3 == 2){
                line += "\n";
                str += line;
                line = "";
            }
        }
        str += line;

        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.pgn"), "UTF-8"));
            out.write(str);
            out.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
