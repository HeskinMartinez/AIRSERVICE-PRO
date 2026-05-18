package Modelos;

public class Tecnico {

    private String id;
    private String nombre;
    private String correo;
    private String telefono;
    private String ciudad;
    private String contrasena;
    private String rol;

    private static int contadorId = 1;

    public static Tecnico[] tecnicos = new Tecnico[50];
    public static int contadorTecnicos = 0;

    public Tecnico() {
    }

    public Tecnico(String nombre, String correo, String telefono, String ciudad, String contrasena) {
        this.id = generarId();
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.contrasena = contrasena;
        this.rol = "Tecnico";
    }

    public Tecnico(String correo, String contrasena, String rol) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    private static String generarId() {
        String id = "TEC-" + String.format("%03d", contadorId);
        contadorId++;
        return id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public static int getContadorTecnicos() {
        return contadorTecnicos;
    }

    @Override
    public String toString() {
        return id + "|" + nombre + "|" + correo + "|" + contrasena + "|" + telefono + "|" + ciudad;
    }
}
