package ru.cft.focusstart.field;

import ru.cft.focusstart.gameprocess.CustomListener;
import ru.cft.focusstart.gameprocess.GameProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JFrame {
    private static int mines;
    private static Grid grid;
    private static Cell[][] cells;

    Form(int mines) {
        Form.mines = mines;
        initForm();
    }

    //инициализация формы
    private void initForm() {
        grid = Difficulty.getGrid();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JMenu jMenu = new JMenu("File");
        jMenu.add(exitItem);

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);

        JPanel centerPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();

        initCenterPanel(centerPanel);
        eastPanel.setPreferredSize(new Dimension(20, 20));
        westPanel.setPreferredSize(new Dimension(20, 20));
        northPanel.setPreferredSize(new Dimension(20, 20));
        southPanel.setPreferredSize(new Dimension(20, 20));

        setJMenuBar(jMenuBar);
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //инициализация игрового поля
    private void initCenterPanel(JPanel centerPanel) {
        centerPanel.setLayout(new GridLayout(grid.getGridX(), grid.getGridY()));
        cells = new Cell[grid.getGridX()][grid.getGridY()];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        GameProcess.initField();
        addListenerToCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                centerPanel.add(cells[i][j]);
            }
        }
    }

    private void addListenerToCells() {
        for (Cell[] cell : cells) {
            for (int i = 0; i < cell.length; i++) {
                cell[i].addMouseListener(new CustomListener());
            }
        }
    }

    public static Cell[][] getCells() {
        return cells;
    }

    public static Grid getGrid() {
        return grid;
    }

    public static int getMinesCount() {
        return mines;
    }
}
