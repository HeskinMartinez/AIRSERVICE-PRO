/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author ACER1
 */
public class UsuarioReporte extends Usuario_mio{
    private String correo;

    public UsuarioReporte(String correo, String codigo, String nombre, String Documento, String fecha, String hora) {
        super(codigo, nombre, Documento, fecha, hora);
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
}
