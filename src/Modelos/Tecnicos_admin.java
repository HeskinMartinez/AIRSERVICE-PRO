/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author EUMAPE
 */
public class Tecnicos_admin {
    // 1. Atributos privados (Encapsulamiento)
    private String tipoDocumento;
    private String documento; 
    private String nombre;
    private String correo;
    private String telefono;
    private String direccion; // Usado como ciudad/dirección según tu formulario
    private String nivel;
    private String contrasena;

    // 2. Almacenamiento Estático Rígido (Corregido a Tecnicos_admin)
    public static Tecnicos_admin[] tecnicos = new Tecnicos_admin[50];
    public static int contadorTecnicos = 0;

    // 3. Constructor Completo
    public Tecnicos_admin (String tipoDocumento, String documento, String nombre, String correo, 
                           String telefono, String direccion, String nivel, String contrasena) {
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nivel = nivel;
        this.contrasena = contrasena;
    }

    // 4. Métodos Getters y Setters
    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; } // Corregido: "direccion"

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    // 5. Método auxiliar
    @Override
    public String toString() {
        return documento + "|" + correo + "|" + contrasena + "|" + telefono + "|" + direccion;
    }
}
