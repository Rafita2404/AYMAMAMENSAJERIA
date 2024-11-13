/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Mensaje;
import Model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class MensajeRepository {
  
    private final DatabaseConnection dbConnection;

    public MensajeRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void guardarMensaje(Mensaje mensaje) throws SQLException {
        String query = "INSERT INTO mensajes (id_emisor, id_receptor, mensaje) VALUES (?, ?, ?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, mensaje.getIdEmisor());
            statement.setInt(2, mensaje.getIdReceptor());
            statement.setString(3, mensaje.getMensaje());
            statement.executeUpdate();
        }
    }

    public ResultSet obtenerMensajesPorUsuario(int usuarioId) throws SQLException {
        String query = "SELECT * FROM mensajes WHERE id_emisor = ? OR id_receptor = ?";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
        statement.setInt(1, usuarioId);
        statement.setInt(2, usuarioId);
        return statement.executeQuery();
    }

    private Mensaje mapearMensaje(ResultSet resultSet) throws SQLException {
        Mensaje mensaje = new Mensaje();
        mensaje.setIdMensaje(resultSet.getInt("id_mensaje"));
        mensaje.setIdEmisor(resultSet.getInt("id_emisor"));
        mensaje.setIdReceptor(resultSet.getInt("id_receptor"));
        mensaje.setMensaje(resultSet.getString("mensaje"));
        mensaje.setEnviadoEn(resultSet.getTimestamp("enviado_en"));
        mensaje.setLeido(resultSet.getBoolean("leido"));
        return mensaje;
    }
}
