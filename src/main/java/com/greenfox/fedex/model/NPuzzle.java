package com.greenfox.fedex.model;

import java.io.*;

/**
 * Created by Viktor on 2017.01.26..
 */
public class NPuzzle {

    public static final String dirPath = System.getProperty("user.dir");
    int numOfRows, numOfColumns, maxNumOfBlocksInRow, maxNumOfBlocksInColumn, timeElapsed;
    int[][] picture, rows, columns;

    public NPuzzle(int numOfRows, int numOfColumns, int[][] picture) {
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.picture = picture;
    }

    public int getMaxNumOfBlocksInRow() {
        return maxNumOfBlocksInRow;
    }

    public int getMaxNumOfBlocksInColumn() {
        return maxNumOfBlocksInColumn;
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

    public int getPictureCell(int r, int c) {
        return picture[r][c];
    }

    public int getRowsBlocksTableCell(int r, int c) {
        return rows[r][c];
    }

    public int getColumnsBlocksTableCell(int r, int c) {
        return columns[r][c];
    }

    public void setPicture(int[][] picture) {
        this.picture = picture;
    }

    public void load(String filename, boolean loadPicture) throws IOException {
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
        String readTime = output.split("<")[1];
        output = output.split("<")[0];
        this.timeElapsed = (readTime.equals("null")) ? 0 : Integer.parseInt(readTime);

        String[] blocks = output.split("}");
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = blocks[i].substring((i > 0) ? 3 : 2, blocks[i].length());
        }
        String[] rowsBlocksArray = blocks[0].split("]");
        String[] columnsBlocksArray = blocks[1].split("]");
        for (int i = 0; i < rowsBlocksArray.length; i++) {
            rowsBlocksArray[i] = rowsBlocksArray[i].substring(1, rowsBlocksArray[i].length());
            String[] tempBlockRow = rowsBlocksArray[i].split(",");
            for (int j = 0; j < tempBlockRow.length; j++) {
                this.rows[i][j] = Integer.parseInt(tempBlockRow[j]);
            }
        }
        for (int i = 0; i < columnsBlocksArray.length; i++) {
            columnsBlocksArray[i] = columnsBlocksArray[i].substring(1, columnsBlocksArray[i].length());
            String[] tempBlockColumn = columnsBlocksArray[i].split(",");
            for (int j = 0; j < tempBlockColumn.length; j++) {
                this.columns[i][j] = Integer.parseInt(tempBlockColumn[j]);
            }
        }
        if (loadPicture) {
            String[] puzzleArray = blocks[2].split("\r\n");
            String[] tempLine = puzzleArray[0].substring(1, puzzleArray[0].length() - 1).split(",");
            outArray = new int[puzzleArray.length][tempLine.length];
            for (int i = 0; i < puzzleArray.length; i++) {
                tempLine = (puzzleArray[i].substring(1, puzzleArray[i].length() - 1).split(","));
                for (int j = 0; j < tempLine.length; j++) {
                    outArray[i][j] = Integer.parseInt(tempLine[j]);
                }
            }
            this.picture = outArray;
        }
    }

    public void save(String filename) {
        try {
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
