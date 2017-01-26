package com.greenfox.fedex.listeners;

import com.greenfox.fedex.NonogramMain;
import com.greenfox.fedex.model.NTableCell;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Zsuzska on 2017. 01. 26..
 */
public class NTableListener extends MouseInputAdapter {

    private NonogramMain mainFrame;
    private JTable puzzleTable;

    public NTableListener(NonogramMain mainFrame, JTable puzzleTable) {
        this.mainFrame = mainFrame;
        this.puzzleTable = puzzleTable;
    }

    public void mousePressed(MouseEvent e) {
        int row = mainFrame.getCurrentMouseRow();
        int column = mainFrame.getCurrentMouseColumn();
        int buttonPressed = e.getButton();

        if (buttonPressed == MouseEvent.BUTTON1) {
            ((NTableCell)(puzzleTable.getValueAt(row, column))).switchOnOff();
            puzzleTable.repaint();
        }

        if (buttonPressed == MouseEvent.BUTTON3) {
            ((NTableCell)(puzzleTable.getValueAt(row, column))).switchEmptyDontcare();
            puzzleTable.repaint();
        }
    }
}
