
package Modelos;

public class Servicios {
    private String nombreCliente;
    private String documentoCliente;
    private String telefonoCliente;
    private String direccionCliente;
    private String fechaServicio;
    private String nombreTecnico;
    private String tipoServicio;
    private String descripcionServicio;

   public Servicios(String nombreCliente, String documentoCliente, String telefonoCliente, String direccionCliente, String fechaServicio, String nombreTecnico, String tipoServicio, String descripcionServicio) {
        this.nombreCliente = nombreCliente;
        this.documentoCliente = documentoCliente;
        this.telefonoCliente = telefonoCliente;
        this.direccionCliente = direccionCliente;
        this.fechaServicio = fechaServicio;
        this.nombreTecnico = nombreTecnico;
        this.tipoServicio = tipoServicio;
        this.descripcionServicio = descripcionServicio;
    }
   
    public String getDocumentoCliente() { return documentoCliente; }
    public String getNombreCliente() { return nombreCliente; }
    public String getNombreTecnico() { return nombreTecnico; }
    public String getFechaServicio() { return fechaServicio; }
    public String getTipoServicio() { return tipoServicio; }
    public String getDescripcionServicio() { return descripcionServicio; }
}

