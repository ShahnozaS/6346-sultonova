package ru.cft.focusstart.client.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.client.controller.ClientController;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Client {

    private ClientController controller;
    private static final Logger log = LoggerFactory.getLogger(Client.class);

    private Socket socket;
    private BufferedReader bReader;
    private PrintWriter pWriter;
    private String serverAddress;
    private String userName;

    public void start(String serverAddress) {
        this.serverAddress = serverAddress;
        connectToServer();
    }

    private void connectToServer() {
        Properties properties = new Properties();
        try (InputStream propertiesStream = Client.class.getClassLoader().getResourceAsStream("server.properties")) {
            properties.load(propertiesStream);
            socket = new Socket(serverAddress, Integer.valueOf(properties.getProperty("server.port")));

            createStreams();
            new ReadMessage().start();

        } catch (IOException e) {
            log.info("Не удалось подключиться к серверу");
        }
    }

    private void createStreams() {
        try {
            bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pWriter = new PrintWriter((socket.getOutputStream()));
        } catch (IOException e) {
            log.info("Ошибка при создании потоков ввода/вывода");
        }
    }

    private class ReadMessage extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    if (controller.isClientWindowAvailable()) {
                        String message = bReader.readLine();
                        if (message.startsWith("/authok")) {
                            userName = message.split("\\s")[1];
                            controller.showClientWindow(userName);
                            startReadingMsg();
                            break;
                        } else if (message.startsWith("/reject")) {
                            controller.rejectLogin("Имя уже используется");
                        }
                    } else {
                        sendMessage("/end");
                        Client.this.downService();
                    }
                }
            } catch (IOException e) {
                sendMessage("/end");
                log.info("Ошибка при чтении входящего сообщения");
                Client.this.downService();
            }
        }
    }

    private void startReadingMsg() {
        while (true) {
            if (controller.isClientWindowAvailable()) {
                try {
                    String fullStr = bReader.readLine();
                    String[] str = fullStr.split("\\s");
                    String message = str[str.length - 1];
                    if (fullStr.startsWith("{")) {
                        controller.setOnlineUsersList(jsonToList(fullStr));
                    } else if (message.equals("/end")) {
                        Client.this.downService();
                        controller.exit();
                        System.exit(0);
                        break;
                    } else {
                        controller.showMessage(fullStr);
                    }
                } catch (IOException e) {
                    log.info("Ошибка при чтении входящего сообщения");
                    controller.showAlert("Нет соединения с сервером");
                    Client.this.downService();
                    break;
                }
            } else {
                sendMessage("/end");
                Client.this.downService();
            }
        }
    }

    public void sendMessage(String message) {
        pWriter.println(message);
        pWriter.flush();
    }

    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                bReader.close();
                pWriter.close();
            }
        } catch (IOException e) {
            log.info("Закрытие сокета и потоков");
        }
    }

    private List<String> jsonToList(String message) {
        List<String> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(message);
            JSONArray jsonArray = jsonObject.getJSONArray("/clients");
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            log.info("Ошибка при обработке JSON");
        }
        return list;
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }

    public Socket getSocket() {
        return socket;
    }
}
