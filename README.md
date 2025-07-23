# 💬 Java Multithreaded Chat Application (Swing GUI + Login/Registration)

This is a Java-based multithreaded chat application using **Swing GUI** for the client side. It supports:
- Multiple clients connecting to a server
- Real-time messaging
- User login and registration system
- Message broadcasting to all clients

---

## 🚀 Features

- Multithreaded chat server using sockets
- Swing-based GUI for chat and login/register
- Message broadcast to all connected users
- Basic authentication with file-based storage (`users.txt`)
- Displays join/leave notifications

---

## 📁 Project Structure

MultithreadedChatApp/
├── server/
│ ├── Server.java
│ └── ClientHandler.java
├── client/
│ ├── LoginFrame.java
│ ├── ChatFrame.java
│ └── ChatClient.java
├── users.txt # Stores registered users (username:password)
└── README.md

---

## 🧪 Sample Output

### ✅ Terminal (Server Side)

Server started...
New client connected.
User ekta registered.
[ ekta joined the chat ]
User rahul registered.
[ rahul joined the chat ]



### ✅ Client Chat Window

[ ekta joined the chat ]
[ rahul joined the chat ]
rahul: Hi Ekta!
ekta: Hello Rahul!

---

## 🛠️ Requirements

- Java 8 or above
- An IDE like IntelliJ or Eclipse (optional)
- No external libraries required

---

## 🏁 How to Run

### 1. Compile
javac server/.java client/.java


### 2. Run Server
java server.Server


### 3. Run Client(s)
java client.ChatClient


> ✅ Open multiple clients to simulate a real chat!


---

## 📌 Notes

- All users are stored in `users.txt` in the format `username:password`.
- Messages are broadcast to all connected users except the sender.
- Chat frame displays live messages, join/leave notices.
- Uses Java's `Socket`, `ServerSocket`, and `Swing`.

---

## 👤 Author

**Ekta Naresh Chandak**  
B.Tech | Artificial Intelligence & Data Science  

---

## 📃 License

This project is for educational/demo purposes. Feel free to use and modify.
