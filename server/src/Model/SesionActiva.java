/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Hp
 */
public class SesionActiva {
    private int idSesion;
    private int idUsuario;
    private String tokenSesion;
    private java.sql.Timestamp conectadoDesde;

    // Constructor vacío
    public SesionActiva() {
    }

    // Constructor con argumentos
    public SesionActiva(int idSesion, int idUsuario, String tokenSesion, java.sql.Timestamp conectadoDesde) {
        this.idSesion = idSesion;
        this.idUsuario = idUsuario;
        this.tokenSesion = tokenSesion;
        this.conectadoDesde = conectadoDesde;
    }

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTokenSesion() {
        return tokenSesion;
    }

    public void setTokenSesion(String tokenSesion) {
        this.tokenSesion = tokenSesion;
    }

    public java.sql.Timestamp getConectadoDesde() {
        return conectadoDesde;
    }

    public void setConectadoDesde(java.sql.Timestamp conectadoDesde) {
        this.conectadoDesde = conectadoDesde;
    }
}
