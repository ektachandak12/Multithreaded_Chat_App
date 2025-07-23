package client;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class ChatFrame extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private BufferedReader in;
    private PrintWriter out;
    private String username;

    public ChatFrame(Socket socket, BufferedReader in, PrintWriter out, String username) {
        this.in = in;
        this.out = out;
        this.username = username;

        setTitle("Chat - " + username);
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Chat display area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Input field
        inputField = new JTextField();
        inputField.addActionListener(e -> {
            String msg = inputField.getText().trim();
            if (!msg.isEmpty()) {
                out.println(msg);
                chatArea.append("Me: " + msg + "\n");  // Optional: show own message immediately
                inputField.setText("");
            }
        });


        add(scrollPane, BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);
        setVisible(true);

        // Start thread to receive messages
        new Thread(() -> {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    String finalLine = line;
                    SwingUtilities.invokeLater(() -> {
                        chatArea.append(finalLine + "\n");
                    });
                }
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> {
                    chatArea.append("Disconnected from server.\n");
                });
            }
        }).start();

    }

    private void sendMessage() {
        String msg = inputField.getText().trim();
        if (!msg.isEmpty() && out != null) {
            out.println(username + ": " + msg);  // include username
            inputField.setText("");
        }
    }

    private void receiveMessages() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                chatArea.append(line + "\n");
                chatArea.setCaretPosition(chatArea.getDocument().getLength()); // auto-scroll
            }
        } catch (IOException e) {
            chatArea.append("Disconnected from server.\n");
        }
    }


}
