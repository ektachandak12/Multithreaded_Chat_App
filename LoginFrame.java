package client;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class LoginFrame extends JFrame {
    JTextField usernameField;
    JPasswordField passwordField;
    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public LoginFrame() {
        setTitle("Login or Register");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        add(loginBtn);
        add(registerBtn);

        loginBtn.addActionListener(e -> authenticate("LOGIN"));
        registerBtn.addActionListener(e -> authenticate("REGISTER"));

        setVisible(true);
    }

    private void authenticate(String type) {
        try {
            socket = new Socket("localhost", 1234);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            if (!"LOGIN_OR_REGISTER".equals(in.readLine())) return;

            out.println(type);
            out.println(usernameField.getText());
            out.println(new String(passwordField.getPassword()));

            if ("SUCCESS".equals(in.readLine())) {
                new ChatFrame(socket, in, out, usernameField.getText());
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
