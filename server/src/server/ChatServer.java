/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class ChatServer {
//    private static final int PORT = 12345; // Puerto del servidor
//    private static Set<PrintWriter> clientWriters = new HashSet<>(); // Lista de clientes conectados
//
//    public static void main(String[] args) {
//        System.out.println("Servidor de chat iniciado en el puerto " + PORT);
//        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
//            while (true) {
//                // Espera a que un cliente se conecte y lanza un nuevo hilo para manejarlo
//                Socket clientSocket = serverSocket.accept();
//                System.out.println("Nuevo cliente conectado: " + clientSocket);
//                new ClientHandler(clientSocket).start();
//            }
//        } catch (IOException e) {
//            System.out.println("Error en el servidor: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    // Clase interna para manejar la conexión de cada cliente
//    private static class ClientHandler extends Thread {
//        private Socket socket;
//        private PrintWriter out;
//        private BufferedReader in;
//
//        public ClientHandler(Socket socket) {
//            this.socket = socket;
//        }
//
//        public void run() {
//            try {
//                // Inicializa los flujos de entrada y salida del cliente
//                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                out = new PrintWriter(socket.getOutputStream(), true);
//
//                // Agrega el escritor de este cliente a la lista de escritores
//                synchronized (clientWriters) {
//                    clientWriters.add(out);
//                }
//
//                // Lee los mensajes del cliente y los reenvía a todos los clientes
//                String message;
//                while ((message = in.readLine()) != null) {
//                    System.out.println("Mensaje recibido: " + message);
//                    broadcastMessage(message);
//                }
//            } catch (IOException e) {
//                System.out.println("Error con el cliente: " + e.getMessage());
//                e.printStackTrace();
//            } finally {
//                // Limpia la conexión cuando el cliente se desconecta
//                if (out != null) {
//                    synchronized (clientWriters) {
//                        clientWriters.remove(out);
//                    }
//                }
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("Cliente desconectado: " + socket);
//            }
//        }
//
//        // Método para enviar un mensaje a todos los clientes conectados
//        private void broadcastMessage(String message) {
//            synchronized (clientWriters) {
//                for (PrintWriter writer : clientWriters) {
//                    writer.println(message);
//                }
//            }
//        }
//    }
    
    private static final int SERVER_PORT = 12345;
    private static final List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Servidor de chat en ejecución...");
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            while (true) {
                new ClientHandler(serverSocket.accept()).start();  // Aceptar nuevos clientes
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Clase para manejar la comunicación con cada cliente
    private static class ClientHandler extends Thread {
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                synchronized (clientWriters) {
                    clientWriters.add(out);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    // Enviar el mensaje a todos los clientes
                    synchronized (clientWriters) {
                        for (PrintWriter writer : clientWriters) {
                            writer.println(message);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    synchronized (clientWriters) {
                        clientWriters.remove(out);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}