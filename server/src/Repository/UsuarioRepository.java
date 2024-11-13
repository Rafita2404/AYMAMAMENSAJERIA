/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Usuario;
import Model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class UsuarioRepository {
    
    private final DatabaseConnection dbConnection;

    public UsuarioRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public Usuario obtenerUsuarioPorNombre(String nombreUsuario) throws SQLException {
        Usuario usuario = null;
        String query = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombreUsuario);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                    usuario.setContrasenaHash(rs.getString("contrasena_hash"));
                    usuario.setCreadoEn(rs.getTimestamp("creado_en"));
                }
            }
        }
        return usuario;
    }
    public void agregarUsuario(Usuario usuario) {
        try (Connection connection = dbConnection.getConnection()) {
            String query = "INSERT INTO usuarios (nombre_usuario, contrasena_hash) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, usuario.getNombreUsuario());
            statement.setString(2, usuario.getContrasenaHash());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> obtenerTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection()) {
            String query = "SELECT * FROM usuarios";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                usuarios.add(mapearUsuario(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    private Usuario mapearUsuario(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(resultSet.getInt("id_usuario"));
        usuario.setNombreUsuario(resultSet.getString("nombre_usuario"));
        usuario.setContrasenaHash(resultSet.getString("contrasena_hash"));
        usuario.setCreadoEn(resultSet.getTimestamp("creado_en"));
        return usuario;
    }
}
