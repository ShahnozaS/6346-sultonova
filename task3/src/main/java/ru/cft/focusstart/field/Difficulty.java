package ru.cft.focusstart.field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Difficulty extends JFrame {
    private static Grid grid = null;

    public Difficulty() {
        super("Выберите уровень игры");
        chooseLvl();
    }

    private void chooseLvl() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel content = new JPanel();

        JButton btnEasy = new JButton("Новичок");
        JButton btnNormal = new JButton("Любитель");
        JButton btnHard = new JButton("Профессионал");

        btnEasy.setPreferredSize(new Dimension(140, 30));
        btnNormal.setPreferredSize(new Dimension(140, 30));
        btnHard.setPreferredSize(new Dimension(140, 30));

        btnEasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                grid = new Grid(9, 9);
                new Form(10);
            }
        });

        btnNormal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                grid = new Grid(16, 16);
                new Form(30);
            }
        });

        btnHard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                grid = new Grid(16, 30);
                new Form(60);
            }
        });
        content.add(btnEasy);
        content.add(btnNormal);
        content.add(btnHard);

        setContentPane(content);
        setSize(260, 150);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static Grid getGrid() {
        return grid;
    }
}
