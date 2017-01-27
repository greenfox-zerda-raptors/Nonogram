package com.greenfox.fedex.listeners;

import com.greenfox.fedex.NonogramMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by posam on 2017-01-26.
 * WHAAAAAAAAAAAAAAAASSSSSUUUUUP
 */
public class NBlocksTableListener extends MouseAdapter {

    private NonogramMain mainFrame;
    private JTable blocksTable;

    public NBlocksTableListener(NonogramMain mainFrame, JTable blocksTable) {
        this.mainFrame = mainFrame;
        this.blocksTable = blocksTable;
    }

    public void mousePressed(MouseEvent e) {
        Point pointPressed = e.getPoint();
        int rowPressed = blocksTable.rowAtPoint(pointPressed);
        int columnPressed = blocksTable.columnAtPoint(pointPressed);
        int buttonPressed = e.getButton();

        if (buttonPressed == MouseEvent.BUTTON3)
            blocksTable.setValueAt(0, rowPressed, columnPressed);
    }

}
