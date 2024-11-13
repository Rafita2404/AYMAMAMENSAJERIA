package server;

import View.ChatClientGUI;
import View.VistaCliente;
import java.io.*;
import java.net.Socket;

public class ChatService {
private PrintWriter out;
    private Socket socket;
    private final VistaCliente view;

    public ChatService(VistaCliente view) {
        this.view = view;
    }

    public void start() {
        try {
            socket = new Socket("localhost", 12345);  // Connect to server on localhost and port 12345
            out = new PrintWriter(socket.getOutputStream(), true);
            view.appendMessage("Connected to the chat server.");
        } catch (IOException e) {
            view.appendMessage("Unable to connect to the server.");
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        } else {
            view.appendMessage("Error: Not connected to server.");
        }
    }

    public void close() {
        try {
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
