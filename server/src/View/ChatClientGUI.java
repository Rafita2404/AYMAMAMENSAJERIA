/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import javax.swing.*;
import java.awt.*;
import Controller.ChatController;

public class ChatClientGUI extends JFrame {

    private ChatController controller;
    private JTextArea chatArea;       // Área de texto para mostrar el historial de mensajes
    private JTextField messageField;  // Campo de texto para escribir el mensaje
    private JButton sendButton;       // Botón para enviar el mensaje

    // Constructor que recibe el controlador como parámetro
    public ChatClientGUI(ChatController controller) {
        this.controller = controller;
        initComponents();  // Inicializa la interfaz gráfica
    }

    // Método para inicializar los componentes de la interfaz gráfica
    private void initComponents() {
        // Configuración de la ventana principal
        setTitle("Chat Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración del área de chat
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);

        // Configuración del campo de mensaje y botón de envío
        messageField = new JTextField(25);
        sendButton = new JButton("Send");

        // Asigna el evento de clic al botón de envío
       // sendButton.addActionListener(e -> controller.sendMessage());

        // Layout de la parte inferior (campo de mensaje + botón de envío)
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());
        messagePanel.add(messageField, BorderLayout.CENTER);
        messagePanel.add(sendButton, BorderLayout.EAST);

        // Layout principal de la ventana
        setLayout(new BorderLayout());
        add(chatScrollPane, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);
    }

    // Método para mostrar la ventana de chat
    public void showView() {
        this.setVisible(true);
    }

    // Método para obtener el campo de mensaje (usado por el controlador)
    public JTextField getMessageField() {
        return messageField;
    }

    // Método para añadir mensajes al área de chat
    public void appendMessage(String message) {
        chatArea.append(message + "\n");
    }

    public void setController(ChatController controller) {
        this.controller = controller;
    }

    
}
