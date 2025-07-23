package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 1234;
    public static Set<ClientHandler> clients = Collections.synchronizedSet(new HashSet<>());
    public static Map<String, String> userMap = new HashMap<>();

    public static void main(String[] args) {
        loadUsers();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started...");
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket);
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public static synchronized boolean registerUser(String username, String password) {
        if (userMap.containsKey(username)) return false;
        userMap.put(username, password);
        try (FileWriter fw = new FileWriter("users.txt", true)) {
            fw.write(username + ":" + password + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static synchronized boolean loginUser(String username, String password) {
        return userMap.containsKey(username) && userMap.get(username).equals(password);
    }

    private static void loadUsers() {
        File file = new File("users.txt");
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    userMap.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
