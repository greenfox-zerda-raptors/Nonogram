package com.greenfox.fedex.IO;

import com.greenfox.fedex.model.NPuzzle;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by posam on 2017-01-26.
 * WHAAAAAAAAAAAAAAAASSSSSUUUUUP
 */
public class Puzzlereader {
    public static final String dirPath = System.getProperty("user.dir");

    public static int[][] fileToPuzzle(String filename) {
        File filepath = new File(dirPath + "/Puzzles/" + filename);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), Charset.forName("UTF-8")));
            int c;
            while ((c = reader.read()) != -1) {
                char character = (char) c;
                if (character == '{') {
                    String blockstring = "";
                    while (reader.read() != '}') {
                        blockstring += reader.read();
                    }
                    String[] lineArray = blockstring.split("]");
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
