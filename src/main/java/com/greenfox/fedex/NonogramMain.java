package com.greenfox.fedex;

import com.greenfox.fedex.listeners.NBlocksTableListener;
import com.greenfox.fedex.listeners.NTableListener;
import com.greenfox.fedex.model.*;
import com.greenfox.fedex.render.NBlocksRenderer;
import com.greenfox.fedex.render.NTableCellRenderer;
import com.greenfox.fedex.tools.NToolkit;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Viktor on 2017.01.26..
 */
public class NonogramMain extends JFrame {
    private final int defaultNumberOfRows = 10;
    private final int defaultNumberOfColumns = 10;


    private JMenuBar menubar = new JMenuBar();
    private JMenu menu = new JMenu("File");
    private JMenuItem calculateBlocksMenu = new JMenuItem("Calculate blocks from image");
    private JMenuItem exitMenu = new JMenuItem("Exit");
    private JMenuItem saveMenu = new JMenuItem("Save game");
    private JMenuItem loadMenu = new JMenuItem("Continue game");

    private static final String dirPath = System.getProperty("user.dir");
    private String puzzlePath = dirPath + "/Puzzles/";
    private JFileChooser puzzleFileChooser = new JFileChooser(puzzlePath);

    private JTable puzzleTable = new JTable(new NTableModel(10, 10));
    private JTable columnsBlocksTable = new NBlocksTable(new NBlocksTableModel((defaultNumberOfRows + 1) >> 1, defaultNumberOfColumns));
    private JTable rowsBlocksTable = new NBlocksTable(new NBlocksTableModel(defaultNumberOfRows, (defaultNumberOfColumns + 1) >> 1));
    private GridBagConstraints constraints = new GridBagConstraints();

    private GridBagLayout gridBagLayout = new GridBagLayout();
    private NTableCellRenderer nTableCellRenderer;
    private NPuzzle current;
    private NBlocksRenderer rowsBlocksRenderer = new NBlocksRenderer(this, true);
    private NBlocksRenderer columnsBlocksRenderer = new NBlocksRenderer(this, false);

    private Toolkit tk = Toolkit.getDefaultToolkit();
    private JPanel mainPanel = new JPanel(gridBagLayout);
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
        setupTable(puzzlePath + "default.txt", true);
        setJMenuBar(menubar);
        menubar.add(menu);
        menu.add(calculateBlocksMenu);
        calculateBlocksMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NToolkit.calculateBlocks(puzzleTable, rowsBlocksTable, columnsBlocksTable);
            }
        });
        menu.add(saveMenu);
        saveMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = puzzleFileChooser.showSaveDialog(menubar);

                if (returnValue == JFileChooser.APPROVE_OPTION)
                    savePuzzle(puzzleFileChooser.getSelectedFile().getAbsolutePath(), current);


            }
        });
        menu.add(loadMenu);
        loadMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = puzzleFileChooser.showOpenDialog(menubar);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = puzzleFileChooser.getSelectedFile();
                    String filename = file.getName();
                    String absolutePath = file.getAbsolutePath();
                    loadPanel(absolutePath, true);
                }
            }
        });
        menu.add(exitMenu);
        exitMenu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        pack();
    }

    private void savePuzzle(String absolutePath, NPuzzle current) {
        int numberOfRows = getNumberOfRows();
        int numberOfColumns = getNumberOfColumns();
        int maxNumOfBlocksInRow = getMaxNumOfBlocksInRow();
        int maxNumOfBlocksInColumn = getMaxNumOfBlocksInColumn();

        for (int r = 0; r < numberOfRows; r++)
            for (int c = 0; c < numberOfColumns; c++)
                current.setPictureCell(r, c, ((NTableCell) (puzzleTable.getModel().getValueAt(r, c))).getState());

        for (int r = 0; r < numberOfRows; r++)
            for (int c = 0; c < maxNumOfBlocksInRow; c++)
                current.setRowsBlocksTableCell(r, c, (Integer) rowsBlocksTable.getModel().getValueAt(r, c));

        for (int r = 0; r < maxNumOfBlocksInColumn; r++)
            for (int c = 0; c < numberOfColumns; c++)
                current.setColumnsBlocksTableCell(r, c, (Integer) columnsBlocksTable.getModel().getValueAt(r, c));

        current.save(absolutePath);
    }

    private void addCrosswordTableListener() {
        NTableListener nTableListener = new NTableListener(this, puzzleTable, rowsBlocksTable, columnsBlocksTable, current);
        puzzleTable.addMouseListener(nTableListener);
        puzzleTable.addMouseMotionListener(nTableListener);
    }


    public void setupTable(String filePath, boolean loadPicture) {
        NPuzzle nPuzzle = new NPuzzle(10, 10);
        try {
            nPuzzle.load(filePath, loadPicture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        current = nPuzzle;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        puzzleTable = new JTable(new NTableModel(10, 10, nPuzzle.getPicture()));
        mainPanel.add(puzzleTable, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 0, spaceBetweenTables);
        rowsBlocksTable = new JTable(new NBlocksTableModel(nPuzzle.getNumOfRows(), nPuzzle.getMaxNumOfBlocksInRow(), nPuzzle, true));
        mainPanel.add(rowsBlocksTable, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, spaceBetweenTables, 0);
        columnsBlocksTable = new JTable(new NBlocksTableModel(nPuzzle.getMaxNumOfBlocksInColumn(), nPuzzle.getNumOfColumns(), nPuzzle, false));
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


    private void loadPanel(String absolutePath, boolean loadPicture) {
        mainPanel.removeAll();
        setupTable(absolutePath, loadPicture);
        mainPanel.revalidate();
        mainPanel.repaint();

    }

    private void dontAutoCreateColumnsFromModel() {
        puzzleTable.setAutoCreateColumnsFromModel(false);
        rowsBlocksTable.setAutoCreateColumnsFromModel(false);
        columnsBlocksTable.setAutoCreateColumnsFromModel(false);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NonogramMain();
            }
        });
    }

    public int getNumberOfRows() {
        return puzzleTable.getRowCount();
    }

    public int getNumberOfColumns() {
        return puzzleTable.getColumnCount();
    }

    public int getMaxNumOfBlocksInRow() {
        return Math.floorDiv(getNumberOfColumns() + 1, 2);
    }

    public int getMaxNumOfBlocksInColumn() {
        return Math.floorDiv(getNumberOfRows() + 1, 2);
    }
}
