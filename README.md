# 🗨️ Multithreaded Chat Application (Java + Swing)

A **multithreaded client-server chat application** built using **Java Sockets** and **Swing GUI**. This app supports multiple clients communicating in real-time, with login and registration features, and individual chat windows per user.

---

## 💡 Features

- 🔒 **Login and Registration** system
- 💬 **Real-time group chat** using sockets
- 🧵 **Multithreading** on the server to handle multiple clients simultaneously
- 🖥️ **Swing-based GUI** for an interactive chat interface
- 🔔 **System messages** (e.g., user joined/left notifications)
- ❌ Graceful client disconnection without affecting other users

---

## 🏗️ Technologies Used

- Java SE
- Java Swing (GUI)
- Java Socket Programming
- Multithreading (via `Thread` and `Runnable`)

---

## 📁 Project Structure

MultithreadedChatApp/
-├── server/
-│ ├── Server.java
-│ └── ClientHandler.java
-├── client/
-│ ├── ChatClient.java
-│ └── ChatFrame.java
-└── README.md

---

## 🚀 How to Run

### 🖥️ Start the Server
```bash
javac server/*.java
java server.Server
```

## 👥 Start Multiple Clients

```bash
javac client/*.java
java client.ChatClient
```

Each client window will prompt for login or registration, and open a chat GUI upon successful authentication.

## 📸 Sample Output

[ Ekta joined the chat ]
[ Rahul joined the chat ]
Ekta: Hello!
Rahul: Hi Ekta!

## 🛠️ To-Do / Future Enhancements
🗃️ Store registered users in a database (e.g., MySQL)

⏰ Add timestamps to messages

📬 Implement private messaging (DMs)

🌐 Add deployment support for running over a network/internet

## 📚 License
This project is open-source and free to use for learning and educational purposes.

## 🙋‍♀️ Author
Ekta Naresh Chandak
📧 [ektachandak.edu@gmail.com]

