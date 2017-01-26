package com.greenfox.fedex.model;

import javax.swing.table.DefaultTableModel;

/**
 * Created by Zsuzska on 2017. 01. 26..
 */
public class NBlocksTableModel extends DefaultTableModel {

    public NBlocksTableModel(int numberOfRows, int numberOfColumns) {
        super(numberOfRows, numberOfColumns);

		/* Initialize blocks cells. */
        for (int row = 0; row < numberOfRows; row++)
            for (int column = 0; column < numberOfColumns; column++)
                setValueAt(new Integer(0), row, column);
    }

    public NBlocksTableModel(int numberOfRows, int numberOfColumns, NPuzzle puzzle, Boolean rows) {
        super(numberOfRows, numberOfColumns);

		/* Initialize blocks cells. */
        for (int row = 0; row < numberOfRows; row++)
            for (int column = 0; column < numberOfColumns; column++) {
                if (rows)
                    setValueAt(0, row, column);
                else
                    setValueAt(0, row, column);
            }
    }

}
