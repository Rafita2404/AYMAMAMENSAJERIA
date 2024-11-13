/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import Model.Contacto;
import Model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Hp
 */
public class ContactoRepository {
    
    private final DatabaseConnection dbConnection;

    public ContactoRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void agregarContacto(Contacto contacto) throws SQLException {
        String query = "INSERT INTO contactos (id_usuario, id_contacto_usuario) VALUES (?, ?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, contacto.getIdUsuario());
            statement.setInt(2, contacto.getIdContactoUsuario());
            statement.executeUpdate();
        }
    }

    public void bloquearContacto(int idUsuario, int idContactoUsuario) throws SQLException {
        String query = "UPDATE contactos SET bloqueado = ? WHERE id_usuario = ? AND id_contacto_usuario = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, true);
            statement.setInt(2, idUsuario);
            statement.setInt(3, idContactoUsuario);
            statement.executeUpdate();
        }
    }

    private Contacto mapearContacto(ResultSet resultSet) throws SQLException {
        Contacto contacto = new Contacto();
        contacto.setIdContacto(resultSet.getInt("id_contacto"));
        contacto.setIdUsuario(resultSet.getInt("id_usuario"));
        contacto.setIdContactoUsuario(resultSet.getInt("id_contacto_usuario"));
        contacto.setAgregado(resultSet.getBoolean("agregado"));
        contacto.setBloqueado(resultSet.getBoolean("bloqueado"));
        return contacto;
    }
}
