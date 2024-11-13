/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Model.DatabaseConnection;
import Repository.UsuarioRepository;
import Model.Usuario;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author Hp
 */
public class UsuarioService {
   
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(DatabaseConnection dbConnection) {
        this.usuarioRepository = new UsuarioRepository(dbConnection);
    }

    public Usuario obtenerUsuarioPorNombre(String nombreUsuario) throws SQLException {
        return usuarioRepository.obtenerUsuarioPorNombre(nombreUsuario);
    }

    public void registrarUsuario(Usuario usuario) {
        // Validación de usuario, hashing de contraseña, etc.
        usuarioRepository.agregarUsuario(usuario);
    }

    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.obtenerTodosUsuarios();
    }
}
