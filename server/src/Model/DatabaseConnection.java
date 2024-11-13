/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Hp
 */
public class DatabaseConnection {
     private static DatabaseConnection instance;
    private Connection connection;
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/mensajeria";
    private static final String DB_USERNAME = ""; // Usuario de la BD con acceso completo
    private static final String DB_PASSWORD = ""; // Contraseña de la BD

    private DatabaseConnection(String username, String password) throws SQLException {
        try {
            this.connection = DriverManager.getConnection(DATABASE_URL, username, password);
        } catch (SQLException e) {
            throw new SQLException("No se pudo conectar a la base de datos con las credenciales proporcionadas.");
        }
    }

    public static DatabaseConnection getInstance(String username, String password) throws SQLException {
        if (instance == null || instance.getConnection() == null || instance.getConnection().isClosed()) {
            instance = new DatabaseConnection(username, password);
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet obtenerDatosUsuario(String nombreUsuario) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nombreUsuario);
        return statement.executeQuery();
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
