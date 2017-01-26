package com.greenfox.fedex;

import com.greenfox.fedex.IO.Puzzlereader;
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

    JTable crosswordTable = new JTable(new NTableModel(10, 10));
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
        try {
            Puzzlereader.readFile("default.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        pack();
    }

    public void setupTable() {

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(crosswordTable, constraints);
        TableColumnModel columns = crosswordTable.getColumnModel();
        int numberOfColumns = 10; //TODO siwtch for non-static

        for (int i = 0; i < numberOfColumns; i++) {
            setCrosswordColumnProperty(columns.getColumn(i));
        }
    }

    private void setCrosswordColumnProperty(TableColumn column) {
        column.setCellRenderer(nTableCellRenderer);
        column.setMinWidth(20);
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
