/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import Model.DatabaseConnection;
import Model.Contacto;
import Repository.ContactoRepository;
import java.sql.SQLException;
/**
 *
 * @author Hp
 */
public class ContactoService {
 
    private final ContactoRepository contactoRepository;

    public ContactoService(DatabaseConnection dbConnection) {
        this.contactoRepository = new ContactoRepository(dbConnection);
    }

    public void agregarContacto(Contacto contacto) throws SQLException {
        contactoRepository.agregarContacto(contacto);
    }

    public void bloquearContacto(int idUsuario, int idContactoUsuario) throws SQLException {
        contactoRepository.bloquearContacto(idUsuario, idContactoUsuario);
    }
}
