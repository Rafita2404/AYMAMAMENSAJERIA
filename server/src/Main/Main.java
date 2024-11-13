/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import View.ChatClientGUI;
import View.InicioSesion;
import server.ChatServer;

/**
 *
 * @author Hp
 */
public class Main {
//    
//    public static void main(String[] args) {
//        InicioSesion inicioSesion = new InicioSesion();
//        ChatClientGUI clientGUI = new ChatClientGUI();
//        //clientGUI.ShowView();
//        inicioSesion.setVisible(true);
//    }
    
        public static void main(String[] args) {
        // Iniciar el servidor
        new Thread(() -> {
            ChatServer server = new ChatServer();
        }).start();

        // Iniciar el cliente
        javax.swing.SwingUtilities.invokeLater(() -> {
            ChatClientGUI client = new ChatClientGUI();
            client.start();
        });
    }
}
