package com.greenfox.fedex.render;

import com.greenfox.fedex.NonogramMain;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by Zsuzska on 2017. 01. 26..
 */
public class NBlocksRenderer extends DefaultTableCellRenderer {
    private final NonogramMain mainFrame;
    private final boolean isRowBlocks;

    public NBlocksRenderer(NonogramMain mainFrame, boolean isRowBlocks) {
        this.mainFrame = mainFrame;
        this.isRowBlocks = isRowBlocks;
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Integer) {
            int blockSize = ((Integer)value).intValue();

            if (blockSize == 0) {
                setBackground(Color.white);
                setText("");
            }
            else {
                setForeground(Color.yellow);
                setText(value.toString());

                if ((isRowBlocks && (row == mainFrame.getCurrentMouseRow())) ||
                        (!isRowBlocks && (column == mainFrame.getCurrentMouseColumn())))
                    setBackground(Color.red);
                else
                    setBackground(Color.blue);
            }
        }

        return this;
    }
}
