package com.greenfox.fedex;

import com.greenfox.fedex.listeners.NTableListener;
import com.greenfox.fedex.model.NPuzzle;
import com.greenfox.fedex.model.NTableModel;
import com.greenfox.fedex.render.NTableCellRenderer;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Viktor on 2017.01.26..
 */
public class NonogramMain extends JFrame {
    JMenuBar menubar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenuItem menuItem = new JMenuItem("Exit");

    JTable puzzleTable = new JTable(new NTableModel(10, 10));
    private GridBagConstraints constraints = new GridBagConstraints();

    GridBagLayout gridBagLayout = new GridBagLayout();
    NTableCellRenderer nTableCellRenderer;
    Toolkit tk = Toolkit.getDefaultToolkit();
    JPanel mainPanel = new JPanel(gridBagLayout);

    public NonogramMain() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setResizable(false);
        Dimension dim = tk.getScreenSize();
        int xPos = (dim.width / 2) - (this.getWidth() / 2);
        int yPos = (dim.height / 2) - (this.getHeight() / 2);
        this.setLocation(xPos, yPos);
        this.add(mainPanel);
        nTableCellRenderer = new NTableCellRenderer(this);
        setupTable();
        setJMenuBar(menubar);
        menubar.add(menu);
        menu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        pack();
    }

    private void addCrosswordTableListener() {
        NTableListener nTableListener = new NTableListener(this, puzzleTable);
        puzzleTable.addMouseListener(nTableListener);
        puzzleTable.addMouseMotionListener(nTableListener);
    }


    public void setupTable() {
        NPuzzle nPuzzle = new NPuzzle(10, 10);
        try {
            nPuzzle.load("default.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        puzzleTable = new JTable(new NTableModel(10, 10, nPuzzle.getPicture()));
        mainPanel.add(puzzleTable, constraints);
        TableColumnModel columns = puzzleTable.getColumnModel();
        int numberOfColumns = 10; //TODO switch for non-static

        for (int i = 0; i < numberOfColumns; i++) {
            setCrosswordColumnProperty(columns.getColumn(i));
        }

        addCrosswordTableListener();
    }

    public int getCurrentMouseRow() {
        Point mousePosition = puzzleTable.getMousePosition();

        return ((mousePosition == null) ? -1 : puzzleTable.rowAtPoint(mousePosition));
    }

    public int getCurrentMouseColumn() {
        Point mousePosition = puzzleTable.getMousePosition();

        return ((mousePosition == null) ? -1 : puzzleTable.columnAtPoint(mousePosition));
    }

    private void setCrosswordColumnProperty(TableColumn column) {
        column.setCellRenderer(nTableCellRenderer);
        column.setMaxWidth(20);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NonogramMain();
            }
        });
    }
}
