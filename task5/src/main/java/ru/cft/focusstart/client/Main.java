package ru.cft.focusstart.client;

import ru.cft.focusstart.client.controller.ClientController;
import ru.cft.focusstart.client.gui.ClientWindow;
import ru.cft.focusstart.client.gui.LoginDialog;
import ru.cft.focusstart.client.model.Client;

public class Main {
    public static void main(String[] args) {
        ClientWindow clientWindow = new ClientWindow();
        LoginDialog loginDialog = new LoginDialog(clientWindow);
        Client client = new Client();

        ClientController controller = new ClientController(clientWindow, client, loginDialog);

        clientWindow.setController(controller);
        loginDialog.setController(controller);
        client.setController(controller);

        controller.start();
    }
}
