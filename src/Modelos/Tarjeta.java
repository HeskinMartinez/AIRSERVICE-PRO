/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author juank
 */
public class Tarjeta {
     private String NombreTitular;
    private String FechaVencimiento;
    private String NumeroTarjeta;
    private String CodigoS;

    public Tarjeta(String NombreTitular, String FechaVencimiento, String NumeroTarjeta, String CodigoS) {
        this.NombreTitular = NombreTitular;
        this.FechaVencimiento = FechaVencimiento;
        this.NumeroTarjeta = NumeroTarjeta;
        this.CodigoS = CodigoS;
    }

    public String getNombreTitular() {
        return NombreTitular;
    }

    public void setNombreTitular(String NombreTitular) {
        this.NombreTitular = NombreTitular;
    }

    public String getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(String FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }

    public String getNumeroTarjeta() {
        return NumeroTarjeta;
    }

    public void setNumeroTarjeta(String NumeroTarjeta) {
        this.NumeroTarjeta = NumeroTarjeta;
    }

    public String getCodigoS() {
        return CodigoS;
    }

    public void setCodigoS(String CodigoS) {
        this.CodigoS = CodigoS;
    } 
    
}
