package Modelos;

public class Usuarios {

    private String email;
    private String contraseña;
    private String Ciudad;
    private String Numero;
    private String Rol;
    private String Nombre;

    public static String[] tecnicos = new String[50];
    public static String[] usuarios = new String[50];
    public static String[] admins = new String[50];

    public static int contadorTecnicos = 0;
    public static int contadorUsuarios = 0;
    public static int contadorAdmins = 0;

    public static String correoUsuarioActual = "";

    public Usuarios() {
    }

    public Usuarios(String email, String contraseña, String Ciudad, String Numero, String Rol, String Nombre) {
        this.email = email;
        this.contraseña = contraseña;
        this.Ciudad = Ciudad;
        this.Numero = Numero;
        this.Rol = Rol;
        this.Nombre = Nombre;
    }

    public Usuarios(String email, String contraseña, String Rol) {
        this.email = email;
        this.contraseña = contraseña;
        this.Rol = Rol;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

    public static String[] getTecnicos() {
        return tecnicos;
    }

    public static String[] getUsuarios() {
        return usuarios;
    }

    public static String[] getAdmins() {
        return admins;
    }

    public static int getContadorTecnicos() {
        return contadorTecnicos;
    }

    public static int getContadorUsuarios() {
        return contadorUsuarios;
    }

    public static int getContadorAdmins() {
        return contadorAdmins;
    }

    @Override
    public String toString() {
        return Nombre + "|" + email + "|" + contraseña + "|" + Numero + "|" + Ciudad;
    }
}
