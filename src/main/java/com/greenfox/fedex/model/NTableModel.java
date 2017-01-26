package com.greenfox.fedex.model;

import javax.swing.table.DefaultTableModel;

/**
 * Created by Zsuzska on 2017. 01. 26..
 */
public class NTableModel extends DefaultTableModel {

    public NTableModel(int numberOfRows, int numberOfColumns) {
        super(numberOfRows, numberOfColumns);

        for (int row = 0; row < numberOfRows; row++)
            for (int column = 0; column < numberOfColumns; column++)
                setValueAt(new NTableCell(), row, column);
    }

    public NTableModel(int numberOfRows, int numberOfColumns, int[][] inputArray) {
        super(numberOfRows, numberOfColumns);

        for (int row = 0; row < numberOfRows; row++)
            for (int column = 0; column < numberOfColumns; column++)
                setValueAt(new NTableCell(inputArray[row][column] == 1 ? NTableCell.EMPTY : NTableCell.ON), row, column);
    }
}
