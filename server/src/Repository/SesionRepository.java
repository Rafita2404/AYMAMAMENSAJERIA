/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import Model.DatabaseConnection;
import Model.SesionActiva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Hp
 */
public class SesionRepository {
  
    private final DatabaseConnection dbConnection;

    public SesionRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void guardarSesion(SesionActiva sesion) throws SQLException {
        String query = "INSERT INTO sesiones_activas (id_usuario, token_sesion) VALUES (?, ?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sesion.getIdUsuario());
            statement.setString(2, sesion.getTokenSesion());
            statement.executeUpdate();
        }
    }

    public ResultSet obtenerSesionPorToken(String tokenSesion) throws SQLException {
        String query = "SELECT * FROM sesiones_activas WHERE token_sesion = ?";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
        statement.setString(1, tokenSesion);
        return statement.executeQuery();
    }

}
