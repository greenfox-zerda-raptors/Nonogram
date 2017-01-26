package com.greenfox.fedex.render;

import com.greenfox.fedex.NonogramMain;
import com.greenfox.fedex.model.NTableCell;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by Zsuzska on 2017. 01. 26..
 */
public class NTableCellRenderer extends DefaultTableCellRenderer {

    private final NonogramMain mainFrame;
    private final ImageIcon shoudntBePaintedIcon;
    private final ImageIcon questionIcon;

    public NTableCellRenderer(NonogramMain mainFrame) {
        this.mainFrame = mainFrame;
        shoudntBePaintedIcon = new ImageIcon("Images/" + "shouldnt be painted.gif");
        questionIcon = new ImageIcon("Images/" + "dont care.gif");

        setOpaque(true);
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof NTableCell) {
            int cellState = ((NTableCell)value).getState();

            switch (cellState) {
                case NTableCell.ON: setBackground(Color.black); setIcon(null); break;
                case NTableCell.OFF: setBackground(Color.white); setIcon(shoudntBePaintedIcon); break;
                case NTableCell.EMPTY: setBackground(Color.white); setIcon(null); break;
                case NTableCell.DONT_CARE: setBackground(Color.white); setIcon(questionIcon); break;
                default: setBackground(Color.gray); setIcon(null); break;
            }
        }
        return this;
    }
}
