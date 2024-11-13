/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Mensaje;
import View.ChatClientGUI;
import View.VistaCliente;
import java.awt.event.ActionEvent;
import server.ChatClient;
import server.ChatService;
import java.sql.ResultSet;
import service.MensajeService;
import java.sql.SQLException;

/**
 *
 * @author Hp
 */
public class ChatController {

    private final MensajeService mensajeService;
    private final int idUsuario;  // El id del usuario que está conectado
    private VistaCliente vistaCliente;

    public ChatController(VistaCliente vistaCliente, int idUsuario, MensajeService mensajeService) {
        this.vistaCliente = vistaCliente;
        this.idUsuario = idUsuario;
        this.mensajeService = mensajeService;
        
        // Asociar el botón de enviar mensaje con el manejador de evento
        vistaCliente.getBtnSend().addActionListener(this::enviarMensaje);
        
        // Llamar a la función para mostrar los mensajes cuando se cargue la vista
        mostrarMensajes();
    }

    // Maneja el evento del botón de envío
    private void enviarMensaje(ActionEvent e) {
        String mensajeTexto = vistaCliente.getjTextMensaje().getText();
        
        if (!mensajeTexto.isEmpty()) {
            try {
                // Crear el objeto mensaje
                Mensaje mensaje = new Mensaje();
                mensaje.setIdEmisor(idUsuario);
                mensaje.setIdReceptor(1);  // Establecer el receptor adecuado
                mensaje.setMensaje(mensajeTexto);

                // Guardar el mensaje usando el servicio
                mensajeService.guardarMensaje(mensaje);

                // Limpiar el campo de texto después de enviar el mensaje
                vistaCliente.getjTextMensaje().setText("");
                
                // Actualizar la vista con los mensajes nuevos
                mostrarMensajes();

            } catch (SQLException ex) {
                System.err.println("Error al enviar mensaje: " + ex.getMessage());
            }
        }
    }

    // Mostrar los mensajes obtenidos desde la base de datos
    public void mostrarMensajes() {
        try {
            // Obtener los mensajes de la base de datos para el usuario conectado
            ResultSet mensajes = mensajeService.obtenerMensajesPorUsuario(idUsuario);
            
            // Limpiar la vista antes de agregar los nuevos mensajes
            vistaCliente.getjTextArea1().setText("");  // Suponiendo que tienes un área de texto o similar para mostrar los mensajes

            // Recorrer los mensajes y agregarlos a la vista
            while (mensajes.next()) {
                String mensaje = mensajes.getString("mensaje");
                String emisor = mensajes.getString("id_emisor"); // También puedes obtener el nombre del emisor si lo deseas
                String receptor = mensajes.getString("id_receptor");
                
                // Aquí estamos agregando los mensajes al área de texto de la vista, 
                // puede que quieras formatearlo o mostrarlo de manera diferente
                vistaCliente.getjTextArea1().append("De: " + emisor + " Para: " + receptor + " - " + mensaje + "\n");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener mensajes: " + ex.getMessage());
        }
    }
}
