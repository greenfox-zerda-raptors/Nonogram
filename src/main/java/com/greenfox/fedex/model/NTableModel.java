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
                switch (inputArray[row][column]) {
                    case 0:
                        setValueAt(new NTableCell(NTableCell.OFF), row, column);
                        break;
                    case 1:
                        setValueAt(new NTableCell(NTableCell.ON), row, column);
                        break;
                    case 2:
                        setValueAt(new NTableCell(NTableCell.EMPTY), row, column);
                        break;
                    case 3:
                        setValueAt(new NTableCell(NTableCell.DONT_CARE), row, column);
                }
    }

    public String getColumnName(int column) {
        return null;
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

}
