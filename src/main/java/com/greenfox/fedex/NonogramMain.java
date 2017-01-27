package com.greenfox.fedex;

import com.greenfox.fedex.listeners.NBlocksTableListener;
import com.greenfox.fedex.listeners.NTableListener;
import com.greenfox.fedex.model.NBlocksTable;
import com.greenfox.fedex.model.NBlocksTableModel;
import com.greenfox.fedex.model.NPuzzle;
import com.greenfox.fedex.model.NTableModel;
import com.greenfox.fedex.render.NBlocksRenderer;
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
    private final int defaultNumberOfRows = 10;
    private final int defaultNumberOfColumns = 10;


    JMenuBar menubar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenuItem menuItem = new JMenuItem("Exit");

    JTable puzzleTable = new JTable(new NTableModel(10, 10));
    private JTable columnsBlocksTable = new NBlocksTable(new NBlocksTableModel((defaultNumberOfRows + 1) >> 1, defaultNumberOfColumns));
    private JTable rowsBlocksTable = new NBlocksTable(new NBlocksTableModel(defaultNumberOfRows, (defaultNumberOfColumns + 1) >> 1));
    private GridBagConstraints constraints = new GridBagConstraints();

    GridBagLayout gridBagLayout = new GridBagLayout();
    NTableCellRenderer nTableCellRenderer;
    private NBlocksRenderer rowsBlocksRenderer = new NBlocksRenderer(this, true);
    private NBlocksRenderer columnsBlocksRenderer = new NBlocksRenderer(this, false);

    Toolkit tk = Toolkit.getDefaultToolkit();
    JPanel mainPanel = new JPanel(gridBagLayout);
    private int spaceBetweenTables = 10;

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

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 0, spaceBetweenTables);
        mainPanel.add(rowsBlocksTable, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, spaceBetweenTables, 0);
        mainPanel.add(columnsBlocksTable, constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, spaceBetweenTables, spaceBetweenTables);
        TableColumnModel columns = puzzleTable.getColumnModel();
        int numberOfColumns = 10; //TODO switch for non-static

        for (int i = 0; i < numberOfColumns; i++) {
            setCrosswordColumnProperty(columns.getColumn(i));
        }
        addCrosswordTableListener();
        initBlocksTable(rowsBlocksTable, true);
        initBlocksTable(columnsBlocksTable, false);


    }

    private void initBlocksTable(JTable blocksTable, boolean rows) {
        TableColumnModel columns = blocksTable.getColumnModel();
        int numberOfColumns = blocksTable.getColumnCount();

        for (int i = 0; i < numberOfColumns; i++) {
            setBlocksColumnsProperty(columns.getColumn(i), rows);
        }
        blocksTable.setRowSelectionAllowed(false);
        blocksTable.addMouseListener(new NBlocksTableListener(this, blocksTable));
        blocksTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }

    private void setBlocksColumnsProperty(TableColumn column, boolean rows) {
        column.setCellRenderer(rows ? rowsBlocksRenderer : columnsBlocksRenderer);
        column.setMaxWidth(16);
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
        column.setMaxWidth(16);
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
