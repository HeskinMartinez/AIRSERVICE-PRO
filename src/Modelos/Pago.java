/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

public class Pago {
    private String Factura;
    private String Nombre;
    private String telefono;
    private String Documento;
    private String Direccion;
    private String Correo;
    private int    Total;
    
    
   private String MetodoPago;
   private String PlanSelec;

    public Pago(String Factura, String Nombre, String telefono, String Documento, String Direccion, String Correo, int Total, String MetodoPago, String PlanSelec) {
        this.Factura = Factura;
        this.Nombre = Nombre;
        this.telefono = telefono;
        this.Documento = Documento;
        this.Direccion = Direccion;
        this.Correo = Correo;
        this.Total = Total;
        this.MetodoPago = MetodoPago;
        this.PlanSelec = PlanSelec;
    }

    public String getFactura() {
        return Factura;
    }

    public void setFactura(String Factura) {
        this.Factura = Factura;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public String getMetodoPago() {
        return MetodoPago;
    }

    public void setMetodoPago(String MetodoPago) {
        this.MetodoPago = MetodoPago;
    }

    public String getPlanSelec() {
        return PlanSelec;
    }

    public void setPlanSelec(String PlanSelec) {
        this.PlanSelec = PlanSelec;
    }

   
}
