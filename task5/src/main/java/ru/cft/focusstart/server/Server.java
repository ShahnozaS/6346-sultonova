package ru.cft.focusstart.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Server {

    private ServerSocket serverSocket;
    private List<ClientHandler> clients;
    private static final Logger log = LoggerFactory.getLogger(Server.class);

    Server() {
        Properties properties = new Properties();
        try (InputStream propertiesStream = Server.class.getClassLoader().getResourceAsStream("server.properties")) {
            properties.load(propertiesStream);
            serverSocket = new ServerSocket(Integer.valueOf(properties.getProperty("server.port")));
            clients = new ArrayList<>();
            Socket socket = null;

            while (true) {
                log.info("Сервер ожидает подключения");
                socket = serverSocket.accept();
                log.info("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            log.info("Ошибка при работе сервера");
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                log.info("Закрытие сокета");
            }
        }
    }

    public synchronized boolean isNameBusy(String name) {
        for (ClientHandler client : clients) {
            if (client.getName().equals(name)) return true;
        }
        return false;
    }

    private synchronized void broadcastMsg(JSONObject jsonObject) {
        for (ClientHandler client : clients) {
            client.sendMsg(jsonObject);
        }
    }

    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler client : clients) {
            client.sendMsg(msg);
        }
    }

    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
        broadcastClientList();
    }

    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastClientList();
    }

    private JSONObject getClientsJSON() {
        JSONObject jsonObject = new JSONObject();

        JSONArray jsonArray = new JSONArray();
        for (ClientHandler client : clients) {
            jsonArray.put(client.getName());
        }
        try {
            jsonObject.put("/clients", jsonArray);
        } catch (JSONException e) {
            log.info("Ошибка при формировании JSON");
        }
        return jsonObject;
    }

    private synchronized void broadcastClientList() {
        broadcastMsg(getClientsJSON());
    }

//    public int getClientsSize() {
//        return clients.size();
//    }
}
