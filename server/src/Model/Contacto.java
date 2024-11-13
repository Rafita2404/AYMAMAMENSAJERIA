/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Hp
 */
public class Contacto {
    private int idContacto;
    private int idUsuario;
    private int idContactoUsuario;
    private boolean agregado;
    private boolean bloqueado;

    // Constructor vacío
    public Contacto() {
    }

    // Constructor con argumentos
    public Contacto(int idContacto, int idUsuario, int idContactoUsuario, boolean agregado, boolean bloqueado) {
        this.idContacto = idContacto;
        this.idUsuario = idUsuario;
        this.idContactoUsuario = idContactoUsuario;
        this.agregado = agregado;
        this.bloqueado = bloqueado;
    }

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdContactoUsuario() {
        return idContactoUsuario;
    }

    public void setIdContactoUsuario(int idContactoUsuario) {
        this.idContactoUsuario = idContactoUsuario;
    }

    public boolean isAgregado() {
        return agregado;
    }

    public void setAgregado(boolean agregado) {
        this.agregado = agregado;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
}
