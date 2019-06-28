package ru.cft.focusstart.client.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.client.controller.ClientController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClientWindow extends JFrame {
    private ClientController controller;
    private static final Logger log = LoggerFactory.getLogger(ClientWindow.class);
    private JButton sendBtn;
    private JTextField textField;
    private JTextArea textArea;
    private DefaultListModel<String> listModel;
    private JList<String> onlineUsersList;

    public ClientWindow() {
        this.listModel = new DefaultListModel<>();
        this.onlineUsersList = new JList<>(listModel);
    }

    public void initComponents() {
        Font font = new Font("Times New Roman", Font.ITALIC, 16);
        File imageFile = new File(getClass().getResource("/image/sea.jpg").getFile());
        Color backColor = new Color(224, 255, 255);

        setPreferredSize(new Dimension(700, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Чат");
        setFont(font);
        setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.setClientWindowAvailable(false);
            }
        });

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());

        sendBtn = new JButton("Отправить");
        sendBtn.setFont(font);
        sendBtn.setPreferredSize(new Dimension(120, 27));

        textArea = new JTextArea();
        initTextArea(font);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        textField = new JTextField();
        textField.setBackground(backColor);

        JScrollPane usersPane = new JScrollPane();
        onlineUsersList.setBackground(backColor);
        onlineUsersList.setFont(font);

        usersPane.setPreferredSize(new Dimension(120, 0));
        usersPane.setViewportView(onlineUsersList);

        southPanel.add(textField, BorderLayout.CENTER);
        southPanel.add(sendBtn, BorderLayout.EAST);

        BufferedImage backgroundImg = null;
        try {
            backgroundImg = ImageIO.read(imageFile);
        } catch (IOException e) {
            log.info("Не удалось загрузить изображение");
        }
        Icon backgroundIcon = new ImageIcon(backgroundImg);
        JLabel contentLabel = new JLabel(backgroundIcon);

        contentLabel.setLayout(new BorderLayout());
        contentLabel.add(scrollPane, BorderLayout.CENTER);
        contentLabel.add(southPanel, BorderLayout.SOUTH);
        contentLabel.add(usersPane, BorderLayout.EAST);

        setContentPane(contentLabel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void setSendBtnListener() {
        sendBtn.addActionListener(controller);
        sendBtn.setActionCommand("BTN_SEND");
    }

    private void initTextArea(Font font) {
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setForeground(new Color(255, 255, 255));
        textArea.setFont(font);
        textArea.setColumns(40);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        updateCaret();
    }

    private void updateCaret() {
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    public void setFieldListener() {
        textField.setActionCommand("BTN_SEND");
        textField.addActionListener(controller);
    }

    public void showMessage(String message) {
        String dtime = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss").format(new Date());
        textArea.append("[" + dtime + "] " + message + "\n");
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public synchronized void setOnlineUsersList(List<String> onlineUsersList) {
        DefaultListModel<String> listModel = (DefaultListModel<String>) this.onlineUsersList.getModel();
        listModel.removeAllElements();
        for (int i = 0; i < onlineUsersList.size(); i++) {
            listModel.addElement(onlineUsersList.get(i));
        }
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }

    public String getFieldMessage() {
        return textField.getText();
    }

    public void setTextFieldEmpty() {
        textField.setText("");
    }

    public JTextField getTextField() {
        return textField;
    }
}
