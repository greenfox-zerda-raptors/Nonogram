package com.greenfox.fedex.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Viktor on 2017.01.26..
 */
public class NPuzzle {

    public static final String dirPath = System.getProperty("user.dir");
    int numOfRows, numOfColumns, maxNumOfBlocksInRow, maxNumOfBlocksInColumn;
    int[][] picture, rows, columns;

    public NPuzzle(int numOfRows, int numOfColumns, int[][] picture) {
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.picture = picture;
    }

    public NPuzzle(int r, int c) {
        numOfRows = r;
        numOfColumns = c;
        maxNumOfBlocksInRow = Math.floorDiv((c + 1), 2);
        maxNumOfBlocksInColumn = Math.floorDiv((r + 1), 2);

        rows = new int[numOfRows][maxNumOfBlocksInRow];
        columns = new int[maxNumOfBlocksInColumn][numOfColumns];
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public int getNumOfColumns() {
        return numOfColumns;
    }

    public void setNumOfColumns(int numOfColumns) {
        this.numOfColumns = numOfColumns;
    }

    public int[][] getPicture() {
        return picture;
    }

    public void setPicture(int[][] picture) {
        this.picture = picture;
    }

    public void load(String filename) throws IOException {
        int[][] outArray;
        StringBuffer buf = new StringBuffer();
        File filepath = new File(dirPath + "/Puzzles/" + filename);
        FileReader fr = null;
        String output;
        try {
            fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            char[] cbuf = new char[(int) filepath.length()];
            br.read(cbuf);
            buf.append(cbuf);
            output = buf.toString();
            br.close();
        } finally {
            if (fr != null) {
                fr.close();
            }
        }
        String[] lineArray = output.substring(1, output.length() - 1).split("\r\n");
        String[] tempLine = (lineArray[0].substring(1, lineArray[0].length() - 1).split(","));
        outArray = new int[lineArray.length][tempLine.length];
        for (int i = 0; i < lineArray.length; i++) {
            tempLine = (lineArray[i].substring(1, lineArray[i].length() - 1).split(","));
            for (int j = 0; j < tempLine.length; j++) {
                outArray[i][j] = Integer.parseInt(tempLine[j]);
            }
        }
        this.picture = outArray;
    }

}
