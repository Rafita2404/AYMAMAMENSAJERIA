/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import Model.DatabaseConnection;
import Repository.MensajeRepository;
import Model.Mensaje;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
/**
 *
 * @author Hp
 */
public class MensajeService {
    
    private final MensajeRepository mensajeRepository;

    public MensajeService(DatabaseConnection dbConnection) {
        this.mensajeRepository = new MensajeRepository(dbConnection);
    }

    public void guardarMensaje(Mensaje mensaje) throws SQLException {
        mensajeRepository.guardarMensaje(mensaje);
    }

    public ResultSet obtenerMensajesPorUsuario(int usuarioId) throws SQLException {
        return mensajeRepository.obtenerMensajesPorUsuario(usuarioId);
    }
}
