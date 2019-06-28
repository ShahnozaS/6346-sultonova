package ru.cft.focusstart.client.controller;

import ru.cft.focusstart.client.gui.ClientWindow;
import ru.cft.focusstart.client.gui.LoginDialog;
import ru.cft.focusstart.client.model.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientController implements ActionListener {
    private ClientWindow clientWindow;
    private LoginDialog loginDialog;
    private Client client;
    private boolean isClientWindowAvailable;

    public ClientController(ClientWindow clientWindow, Client client, LoginDialog loginDialog) {
        this.clientWindow = clientWindow;
        this.loginDialog = loginDialog;
        this.client = client;
        isClientWindowAvailable = true;
    }

    public void start() {
        loginDialog.setOkBtnListener();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "BTN_SEND":
                sendMessage();
                break;
            case "BTN_OK":
                String userName = loginDialog.getUserNameChooser().getText();
                String server = loginDialog.getServerChooser().getText();
                if (userName.length() < 1) {
                    showAlert("Введите имя");
                } else if (server.length() < 1) {
                    showAlert("Введите адрес сервера");
                } else {
                    client.start(server);
                    if (client.getSocket() != null) {
                        client.sendMessage("/auth " + userName);
                    } else {
                        showAlert("Не удалось подключиться к серверу");
                    }
                }
        }
    }

    private void sendMessage() {
        if (clientWindow.getFieldMessage().length() > 0) {
            client.sendMessage(clientWindow.getFieldMessage());
            clientWindow.setTextFieldEmpty();
            clientWindow.getTextField().grabFocus();
        } else {
            clientWindow.getTextField().grabFocus();
        }
    }

    public void showClientWindow(String userName) {
        loginDialog.setVisible(false);
        clientWindow.initComponents();
        clientWindow.getTextField().grabFocus();
        clientWindow.setSendBtnListener();
        clientWindow.setFieldListener();
    }

    public void rejectLogin(String message) {
        clientWindow.showAlert(message);
        loginDialog.setNameTextFieldEmpty();
        loginDialog.getUserNameChooser().grabFocus();
    }

    public void setOnlineUsersList(List<String> onlineUsersList) {
        clientWindow.setOnlineUsersList(onlineUsersList);
    }

    public void showMessage(String message) {
        clientWindow.showMessage(message);
    }

    public void showAlert(String message) {
        clientWindow.showAlert(message);
    }

    public void exit() {
        clientWindow.dispose();
    }

    public void setClientWindowAvailable(boolean clientWindowAvailable) {
        isClientWindowAvailable = clientWindowAvailable;
    }

    public boolean isClientWindowAvailable() {
        return isClientWindowAvailable;
    }
}
