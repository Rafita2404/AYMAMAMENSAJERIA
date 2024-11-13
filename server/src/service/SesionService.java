/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import Model.DatabaseConnection;
import Model.SesionActiva;
import Repository.SesionRepository;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author Hp
 */
public class SesionService {
    
    private final SesionRepository sesionRepository;

    public SesionService(DatabaseConnection dbConnection) {
        this.sesionRepository = new SesionRepository(dbConnection);
    }

    public void guardarSesion(SesionActiva sesion) throws SQLException {
        sesionRepository.guardarSesion(sesion);
    }

    public ResultSet obtenerSesionPorToken(String tokenSesion) throws SQLException {
        return sesionRepository.obtenerSesionPorToken(tokenSesion);
    }
}
