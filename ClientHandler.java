package server;

import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                out.println("LOGIN_OR_REGISTER");
                String action = in.readLine();
                String uname = in.readLine();
                String pwd = in.readLine();

                boolean success = false;
                if ("LOGIN".equalsIgnoreCase(action)) {
                    success = Server.loginUser(uname, pwd);
                } else if ("REGISTER".equalsIgnoreCase(action)) {
                    success = Server.registerUser(uname, pwd);
                }

                if (success) {
                    out.println("SUCCESS");
                    this.username = uname;
                    Server.clients.add(this);
                    Server.broadcast("[ " + username + " joined the chat ]", this);
                    break;
                } else {
                    out.println("FAIL");
                }
            }

            String msg;
            while ((msg = in.readLine()) != null) {
                Server.broadcast(username + ": " + msg, this);
            }

            System.out.println("Message received from " + username + ": " + msg);

        } catch (IOException e) {
            System.out.println(username + " disconnected.");
        } finally {
            try {
                Server.clients.remove(this);
                Server.broadcast("[ " + username + " left the chat ]", this);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
