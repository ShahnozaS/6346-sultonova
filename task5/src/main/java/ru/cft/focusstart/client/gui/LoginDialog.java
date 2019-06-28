package ru.cft.focusstart.client.gui;

import ru.cft.focusstart.client.controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JFrame {

    private ClientController controller;
    private JTextField userNameChooser;
    private JTextField serverChooser;
    private JButton btnOk;

    public LoginDialog(JFrame mainFrame) {
        mainFrame.setVisible(false);

        setTitle("Авторизация");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(null);

        Dimension btnSize = new Dimension(80, 25);
        Dimension fieldDimension = new Dimension(180, 23);

        btnOk = new JButton("OK");
        btnOk.setPreferredSize(btnSize);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(btnSize);
        btnCancel.addActionListener(e -> System.exit(0));

        JLabel chooseUserNameLabel = new JLabel("Имя:");
        JLabel chooseServerLabel = new JLabel("Сервер:");

        userNameChooser = new JTextField();
        userNameChooser.setPreferredSize(fieldDimension);

        serverChooser = new JTextField();
        serverChooser.setPreferredSize(fieldDimension);

        container.add(chooseUserNameLabel);
        container.add(userNameChooser);
        container.add(chooseServerLabel);
        container.add(serverChooser);
        container.add(btnOk);
        container.add(btnCancel);

        Insets insets = container.getInsets();
        Dimension size = chooseUserNameLabel.getPreferredSize();
        chooseUserNameLabel.setBounds(45 + insets.left, 15 + insets.top,
                size.width, size.height);

        size = userNameChooser.getPreferredSize();
        userNameChooser.setBounds(105 + insets.left, 15 + insets.top,
                size.width, size.height);

        size = chooseServerLabel.getPreferredSize();
        chooseServerLabel.setBounds(45 + insets.left, 55 + insets.top,
                size.width, size.height);

        size = serverChooser.getPreferredSize();
        serverChooser.setBounds(105 + insets.left, 55 + insets.top,
                size.width, size.height);

        size = btnOk.getPreferredSize();
        btnOk.setBounds(175 + insets.left, 100 + insets.top,
                size.width, size.height);

        size = btnCancel.getPreferredSize();
        btnCancel.setBounds(260 + insets.left, 100 + insets.top,
                size.width, size.height);

        setSize(360, 170);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setOkBtnListener() {
        btnOk.addActionListener(controller);
        btnOk.setActionCommand("BTN_OK");
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }

    public JTextField getUserNameChooser() {
        return userNameChooser;
    }

    public JTextField getServerChooser() {
        return serverChooser;
    }

    public void setNameTextFieldEmpty() {
        userNameChooser.setText("");
    }
}
