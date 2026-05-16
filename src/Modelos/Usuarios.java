package Modelos;

public class Usuarios {
    private String codigo;
    private String nombre;
    private String Documento;
    private String fecha;
    private String hora;

    public Usuarios(String codigo, String nombre, String Documento, String fecha, String hora) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.Documento = Documento;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
   
    
}
