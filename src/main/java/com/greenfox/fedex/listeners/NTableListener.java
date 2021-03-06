package com.greenfox.fedex.listeners;

import com.greenfox.fedex.NonogramMain;
import com.greenfox.fedex.model.NPuzzle;
import com.greenfox.fedex.model.NTableCell;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Zsuzska on 2017. 01. 26..
 */
public class NTableListener extends MouseInputAdapter {

    private NonogramMain mainFrame;
    private JTable puzzleTable, rowsBlocksTable, columnsBlocksTable;

    public NTableListener(NonogramMain mainFrame, JTable puzzleTable, JTable rowsBlocksTable, JTable columnsBlocksTable, NPuzzle current) {
        this.mainFrame = mainFrame;
        this.puzzleTable = puzzleTable;
        this.rowsBlocksTable = rowsBlocksTable;
        this.columnsBlocksTable = columnsBlocksTable;
    }

    public void mousePressed(MouseEvent e) {
        int row = mainFrame.getCurrentMouseRow();
        int column = mainFrame.getCurrentMouseColumn();
        int buttonPressed = e.getButton();

        if (buttonPressed == MouseEvent.BUTTON1) {
            ((NTableCell) (puzzleTable.getValueAt(row, column))).switchOnOff();
            puzzleTable.repaint();
        }

        if (buttonPressed == MouseEvent.BUTTON3) {
            ((NTableCell) (puzzleTable.getValueAt(row, column))).switchEmptyDontcare();
            puzzleTable.repaint();
        }
    }

    public void mouseMoved(MouseEvent e) {
        rowsBlocksTable.repaint();
        columnsBlocksTable.repaint();
    }

    public void mouseExited(MouseEvent e) {
        rowsBlocksTable.repaint();
        columnsBlocksTable.repaint();
    }
}
