/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DatabaseConnection;
import Model.Usuario;
import View.ChatClientGUI;
import View.InicioSesion;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Hp
 */
public class InicioSesionControlador {

    private final InicioSesion objSesion;
    private Usuario objUsuarios;
    private DatabaseConnection objConexionBD;

    public InicioSesionControlador(InicioSesion objSesion) {
        this.objSesion = objSesion;
        objSesion.getjButtonOK().addActionListener(this::eventosBtnOk);
        configureEvnts();
    }

private void eventosBtnOk(ActionEvent e) {
     // Obtener credenciales de los campos de la vista
     String username = objSesion.getjTextFieldNombre().getText();
     char[] passwordArray = objSesion.getjPasswordFieldContraseña().getPassword();
     String password = new String(passwordArray);

     try {
         // Crear una nueva conexión con las credenciales proporcionadas
         objConexionBD = DatabaseConnection.getInstance(username, password);

         // Verificar si la conexión es válida
         if (objConexionBD.getConnection() != null && !objConexionBD.getConnection().isClosed()) {
             System.out.println("Inicio de sesión exitoso.");

             // Obtener los datos del usuario
             ResultSet rs = objConexionBD.obtenerDatosUsuario(username);
             if (rs.next()) {
                 objUsuarios = new Usuario();
                 objUsuarios.setIdUsuario(rs.getInt("id_usuario"));
                 objUsuarios.setNombreUsuario(rs.getString("nombre_usuario"));
                 objUsuarios.setContrasenaHash(rs.getString("contrasena_hash"));
                 objUsuarios.setCreadoEn(rs.getTimestamp("creado_en"));

                 System.out.println("Usuario conectado: " + objUsuarios.getNombreUsuario());

                 // Redirigir a la vista de ChatClientGUI
                 objSesion.setVisible(false);  // Ocultar la vista de inicio de sesión
                 new ChatClientGUI(objUsuarios.getIdUsuario()).setVisible(true);  // Mostrar la vista de chat
             } else {
                 System.out.println("El usuario no existe en la base de datos.");
             }
         }
     } catch (SQLException ex) {
         System.err.println("Error al intentar iniciar sesión: " + ex.getMessage());
     }
 }

    private void configureEvnts() {
        objSesion.setVisible(true);
    }
}

