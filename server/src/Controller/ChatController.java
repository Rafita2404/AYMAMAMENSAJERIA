/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.ChatClientGUI;
import server.ChatService;

/**
 *
 * @author Hp
 */
public class ChatController {
    
    private final ChatClientGUI view;
    private final ChatService chatService;

    public ChatController(ChatClientGUI view) {
        this.view = view;
        this.chatService = new ChatService(view);  // Pasar la vista al servicio
    }
    public void sendMessage() {
        String message = view.getMessageField().getText();
        if (!message.isEmpty()) {
            chatService.sendMessage(message);  // Enviar mensaje al servicio
            view.getMessageField().setText("");  // Limpiar el campo de texto
        }
    }

    public void start() {
        chatService.start();  // Iniciar la conexión al servidor
        view.setVisible(true);
    }
}
