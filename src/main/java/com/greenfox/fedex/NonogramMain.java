package com.greenfox.fedex;

import com.greenfox.fedex.model.NTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setupTable();
        setJMenuBar(menubar);
        menubar.add(menu);
        menu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        pack();
    }

    public void setupTable() {

        constraints.gridx = 1; constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(crosswordTable, constraints);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NonogramMain();
            }
        });
    }}
