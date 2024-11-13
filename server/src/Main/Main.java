/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Controller.ChatController;
import Model.DatabaseConnection;
import View.ChatClientGUI;
import View.InicioSesion;
import View.VistaCliente;
import server.ChatServer;
import server.ChatService;
import service.MensajeService;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author Hp
 */
public class Main {

//    public static void main(String[] args) {
//        InicioSesion inicioSesion = new InicioSesion();
//        ChatClientGUI clientGUI = new ChatClientGUI();
//        clientGUI.ShowView();
//        inicioSesion.setVisible(true);
//    }
    public static void main(String[] args) {
        // Inicializar el servidor en un hilo separado
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Iniciar el servidor en el puerto 12345
                ChatServer chatServer = new ChatServer(12345); // Cambiar el puerto si es necesario
                chatServer.startServer();  // Iniciar el servidor
            }
        });
        serverThread.start();  // Comienza el servidor

        // Espera un momento para dar tiempo al servidor de iniciar (ajusta el tiempo según sea necesario)
        try {
            Thread.sleep(1000); // Espera 1 segundo para que el servidor se inicie
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Crear la ventana de inicio de sesión
        InicioSesion inicioSesion = new InicioSesion();  // Suponiendo que tienes una ventana de inicio de sesión

        // Mostrar la vista de inicio de sesión y esperar que el usuario ingrese sus credenciales
        inicioSesion.setVisible(true);

        // Asumir que el usuario ha ingresado correctamente sus credenciales y obtener el nombre de usuario y contraseña
        String username = inicioSesion.getjTextFieldNombre().getText(); // Método que debería devolver el nombre de usuario ingresado
        String password = inicioSesion.getjPasswordFieldContraseña().getText(); // Método que debería devolver la contraseña ingresada

        try {
            // Obtener la conexión a la base de datos con las credenciales proporcionadas
            DatabaseConnection dbConnection = DatabaseConnection.getInstance(username, password);

            // Crear el servicio de mensajes con la conexión de base de datos
            MensajeService mensajeService = new MensajeService(dbConnection);

            // Asumir que el idUsuario se obtiene desde la base de datos (en el ejemplo se usa un id ficticio)
            // Esto se debe hacer después de verificar el usuario en la base de datos
            int idUsuario = obtenerIdUsuario(dbConnection, username);  // Método para obtener el id del usuario desde la BD

            // Crear la vista del cliente
            VistaCliente vistaCliente = new VistaCliente();

            // Crear el controlador de chat, pasando la vista, idUsuario y mensajeService
            ChatController chatController = new ChatController(vistaCliente, idUsuario, mensajeService);

            // Mostrar la vista del cliente
            vistaCliente.setVisible(true);

            // Inicializar el controlador y comenzar la comunicación
            chatController.mostrarMensajes();  // Cargar mensajes iniciales (si es necesario)

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al conectar con la base de datos o al iniciar sesión.");
        }
    }

    // Método para obtener el idUsuario desde la base de datos, dado el nombre de usuario
    private static int obtenerIdUsuario(DatabaseConnection dbConnection, String username) throws SQLException {
        // Realizamos una consulta para obtener el ID del usuario
        String query = "SELECT id_usuario FROM usuarios WHERE nombre_usuario = ?";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        // Si el usuario existe, retornamos el id_usuario
        if (resultSet.next()) {
            return resultSet.getInt("id_usuario");
        } else {
            throw new SQLException("Usuario no encontrado.");
        }
    }
}
