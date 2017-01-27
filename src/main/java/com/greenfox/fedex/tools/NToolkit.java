package com.greenfox.fedex.tools;

import com.greenfox.fedex.model.NTableCell;

import javax.swing.*;

/**
 * Created by posam on 2017-01-27.
 * WHAAAAAAAAAAAAAAAASSSSSUUUUUP
 */
public class NToolkit {

    public static void calculateBlocks(JTable puzzleTable, JTable rowsBlocksTable, JTable columnsBlocksTable) {
        int numOfRows = puzzleTable.getRowCount();
        int numOfColumns = puzzleTable.getColumnCount();
        int lastRowsIndex = rowsBlocksTable.getColumnCount() - 1;
        int lastColumnsIndex = columnsBlocksTable.getRowCount() - 1;
        int index, size;

		/* Calculate rows blocks. */
        for (int r = 0; r < numOfRows; r++) {
            index = lastRowsIndex;
            size = 0;

            for (int c = numOfColumns - 1; c >= 0; c--) {
                if (((NTableCell) (puzzleTable.getValueAt(r, c))).getState() == NTableCell.ON)
                    size++;
                else if (size > 0) {
                    rowsBlocksTable.setValueAt(new Integer(size), r, index--);
                    size = 0;
                }
            }

            if (size > 0)
                rowsBlocksTable.setValueAt(new Integer(size), r, index--);

            while (index >= 0)
                rowsBlocksTable.setValueAt(new Integer(0), r, index--);
        }

		/* Calculate columns blocks. */
        for (int c = 0; c < numOfColumns; c++) {
            index = lastColumnsIndex;
            size = 0;

            for (int r = numOfRows - 1; r >= 0; r--) {
                if (((NTableCell) (puzzleTable.getValueAt(r, c))).getState() == NTableCell.ON)
                    size++;
                else if (size > 0) {
                    columnsBlocksTable.setValueAt(new Integer(size), index--, c);
                    size = 0;
                }
            }

            if (size > 0)
                columnsBlocksTable.setValueAt(new Integer(size), index--, c);

            while (index >= 0)
                columnsBlocksTable.setValueAt(new Integer(0), index--, c);
        }
    }
}
