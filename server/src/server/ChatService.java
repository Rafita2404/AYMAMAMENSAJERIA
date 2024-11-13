package server;

import View.ChatClientGUI;
import java.io.*;
import java.net.Socket;

public class ChatService {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private final ChatClientGUI view;

    public ChatService(ChatClientGUI view) {
        this.view = view;
    }

    public void start() {
        try {
            // Conexión al servidor
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Hilo para recibir mensajes del servidor
            new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = in.readLine()) != null) {
                        view.appendMessage(serverMessage);  // Mostrar mensaje en la vista
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        if (!message.isEmpty()) {
            out.println(message);  // Enviar mensaje al servidor
        }
    }
}
