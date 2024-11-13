/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Hp
 */
public class Mensaje {
    private int idMensaje;
    private int idEmisor;
    private int idReceptor;
    private String mensaje;
    private java.sql.Timestamp enviadoEn;
    private boolean leido;

    // Constructor vacío
    public Mensaje() {
    }

    // Constructor con argumentos
    public Mensaje(int idMensaje, int idEmisor, int idReceptor, String mensaje, java.sql.Timestamp enviadoEn, boolean leido) {
        this.idMensaje = idMensaje;
        this.idEmisor = idEmisor;
        this.idReceptor = idReceptor;
        this.mensaje = mensaje;
        this.enviadoEn = enviadoEn;
        this.leido = leido;
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public int getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(int idEmisor) {
        this.idEmisor = idEmisor;
    }

    public int getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(int idReceptor) {
        this.idReceptor = idReceptor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public java.sql.Timestamp getEnviadoEn() {
        return enviadoEn;
    }

    public void setEnviadoEn(java.sql.Timestamp enviadoEn) {
        this.enviadoEn = enviadoEn;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }
}
