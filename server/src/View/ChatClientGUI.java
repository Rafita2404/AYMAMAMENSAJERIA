/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.ChatController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import server.ChatServer;

public class ChatClientGUI {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private JFrame frame;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    private final ChatController chatController;

    public ChatClientGUI() {
//        chatController = new ChatController(this);
//        // Configurar la interfaz gráfica
//        frame = new JFrame("Chat en tiempo real");
//        chatArea = new JTextArea(20, 40);
//        chatArea.setEditable(false);
//        messageField = new JTextField(30);
//        sendButton = new JButton("Enviar");
//
//        // Panel para el campo de texto y botón de enviar
//        JPanel panel = new JPanel();
//        panel.setLayout(new FlowLayout());
//        panel.add(messageField);
//        panel.add(sendButton);
//
//        // Agregar componentes al frame
//        frame.getContentPane().add(new JScrollPane(chatArea), BorderLayout.CENTER);
//        frame.getContentPane().add(panel, BorderLayout.SOUTH);
//        frame.pack();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Configurar acción del botón de enviar
//        sendButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                sendMessage();
//            }
//        });
//
//        // Configurar acción para enviar mensaje al presionar Enter en el campo de texto
//        messageField.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                sendMessage();
//            }
//        });
//    }
//
//    private void start() {
//        try {
//            // Conexión al servidor
//            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
//            out = new PrintWriter(socket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            // Hilo para recibir mensajes del servidor
//            new Thread(() -> {
//                String serverMessage;
//                try {
//                    while ((serverMessage = in.readLine()) != null) {
//                        chatArea.append(serverMessage + "\n");
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//
//            frame.setVisible(true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void sendMessage() {
//        String message = messageField.getText();
//        if (!message.isEmpty()) {
//            out.println(message); // Enviar mensaje al servidor
//            messageField.setText(""); // Limpiar el campo de texto
//        }
//    }
//
//    public static void main(String[] args) {
//        ChatServer server = new ChatServer();
//        SwingUtilities.invokeLater(() -> {
//            ChatClientGUI client = new ChatClientGUI();
//            client.start();
//        });
//    }
    
// Crear el controlador, que se encargará de la lógica
        chatController = new ChatController(this);

        // Configurar la interfaz gráfica
        frame = new JFrame("Chat en tiempo real");
        chatArea = new JTextArea(20, 40);
        chatArea.setEditable(false);
        messageField = new JTextField(30);
        sendButton = new JButton("Enviar");

        // Panel para el campo de texto y botón de enviar
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(messageField);
        panel.add(sendButton);

        // Agregar componentes al frame
        frame.getContentPane().add(new JScrollPane(chatArea), BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configurar la acción del botón de enviar
        sendButton.addActionListener(e -> chatController.sendMessage());

        // Configurar la acción para enviar mensaje al presionar Enter
        messageField.addActionListener(e -> chatController.sendMessage());
    }

    // Método para mostrar los mensajes recibidos
    public void appendMessage(String message) {
        chatArea.append(message + "\n");
    }

    public JTextField getMessageField() {
        return messageField;
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
    
    public void start() {
    try {
        // Conectar al servidor
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);  // Establecer la conexión con el servidor
        out = new PrintWriter(socket.getOutputStream(), true);  // Crear el stream de salida
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  // Crear el stream de entrada

        // Crear un hilo que escuche los mensajes que vienen del servidor
        new Thread(new Runnable() {
            @Override
            public void run() {
                String serverMessage;
                try {
                    // Leer los mensajes del servidor y agregarlos al área de chat
                    while ((serverMessage = in.readLine()) != null) {
                        chatArea.append(serverMessage + "\n");  // Mostrar el mensaje en el área de texto
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();  // Ejecutar el hilo

        frame.setVisible(true);  // Mostrar la ventana de la interfaz gráfica

    } catch (IOException e) {
        e.printStackTrace();  // Manejar excepciones de red
    }
}

}
