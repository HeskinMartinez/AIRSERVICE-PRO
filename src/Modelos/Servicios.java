
package Modelos;

public class Servicios {
   // 1. Atributos Privados (Encapsulamiento)
    // Datos del Cliente
    private String nombreCliente;
    private String documentoCliente;
    private String telefonoCliente;
    private String direccionCliente;
    
    // Datos del Servicio
    private String fechaServicio;
    private String nombreTecnico; // Vinculado al nombre del técnico asignado
    private String tipoServicio;  // Mantenimiento Preventivo, Correctivo, Instalación, etc.
    private String descripcionServicio;

    // 2. Almacenamiento Estático Rígido (Base de datos temporal en memoria de Servicios)
    public static Servicios[] listaServicios = new Servicios[50];
    public static int contadorServicios = 0;

    // 3. Constructor Completo
    public Servicios(String nombreCliente, String documentoCliente, String telefonoCliente, 
                    String direccionCliente, String fechaServicio, String nombreTecnico, 
                    String tipoServicio, String descripcionServicio) {
        this.nombreCliente = nombreCliente;
        this.documentoCliente = documentoCliente;
        this.telefonoCliente = telefonoCliente;
        this.direccionCliente = direccionCliente;
        this.fechaServicio = fechaServicio;
        this.nombreTecnico = nombreTecnico;
        this.tipoServicio = tipoServicio;
        this.descripcionServicio = descripcionServicio;
    }

    // 4. Métodos Getters y Setters
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getDocumentoCliente() { return documentoCliente; }
    public void setDocumentoCliente(String documentoCliente) { this.documentoCliente = documentoCliente; }

    public String getTelefonoCliente() { return telefonoCliente; }
    public void setTelefonoCliente(String telefonoCliente) { this.telefonoCliente = telefonoCliente; }

    public String getDireccionCliente() { return direccionCliente; }
    public void setDireccionCliente(String direccionCliente) { this.direccionCliente = direccionCliente; }

    public String getFechaServicio() { return fechaServicio; }
    public void setFechaServicio(String fechaServicio) { this.fechaServicio = fechaServicio; }

    public String getNombreTecnico() { return nombreTecnico; }
    public void setNombreTecnico(String nombreTecnico) { this.nombreTecnico = nombreTecnico; }

    public String getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }

    public String getDescripcionServicio() { return descripcionServicio; }
    public void setDescripcionServicio(String descripcionServicio) { this.descripcionServicio = descripcionServicio; }

    // 5. Método auxiliar para impresión o depuración rápida
    @Override
    public String toString() {
        return documentoCliente + " | " + nombreCliente + " | " + tipoServicio + " | Asignado a: " + nombreTecnico;
    }
}

