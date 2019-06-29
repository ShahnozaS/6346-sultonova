package ru.cft.focusstart.server;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class ClientHandler {

    private Server server;
    private Socket socket;
    private BufferedReader bReader;
    private PrintWriter pWriter;
    private String name;
    private static final Logger log = LoggerFactory.getLogger(ClientHandler.class);

    ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.pWriter = new PrintWriter(socket.getOutputStream());
            this.name = "";
            new ReadMessage().start();

        } catch (IOException e) {
            log.info("Проблемы при создании обработчика клиента");
        }
    }

    private class ReadMessage extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    String str = bReader.readLine();
                    if (str.startsWith("/auth")) {
                        String userName = str.split("\\s")[1];
                        if (!server.isNameBusy(userName)) {
                            sendMsg("/authok " + userName);
                            name = userName;
                            server.broadcastMsg(">>> " + name + " присоединился к чату");
                            server.subscribe(ClientHandler.this);
                            break;
                        } else sendMsg("/reject" + " Имя уже используется");
                    }
                }
                while (true) {
                    String str = bReader.readLine();
                    if (str.equals("/end")) {
                        sendMsg(str);
                        break;
                    }
                    server.broadcastMsg(name + ": " + str);
                }
            } catch (IOException e) {
                log.info("Не удалось прочитать входящее сообщение");
            } finally {
                server.unsubscribe(ClientHandler.this);
                server.broadcastMsg(">>> " + name + " вышел из чата");
                try {
                    socket.close();
                } catch (IOException e) {
                    log.info("Закрытие сокета");
                }
                log.info("Соединение закрыто");
            }
        }
    }

    public void sendMsg(JSONObject msg) {
        pWriter.println(msg);
        pWriter.flush();
    }

    public void sendMsg(String msg) {
        pWriter.println(msg);
        pWriter.flush();
    }

    public String getName() {
        return name;
    }
}
