/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
import Modelos.UsuarioReporte;
import Modelos.Usuario_mio;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER1
 */
public class Controlador_reporte {
     private ArrayList<Usuario_mio> listaUsuarios = new ArrayList<>();
            
        private DefaultTableModel modelo;
        private int  contador = 1;

    public Controlador_reporte() {
    }
        
        public String generarCodigo(){
        String codigo = "AIR-" + String.format("%03d", contador);
        contador ++;
        return codigo;
        
      
    }
        
          public boolean validardatosrepo(String nombre, String documento,String correo, java.awt.Component vista){
              
              if(nombre.trim().isEmpty() || documento.trim().isEmpty()){
                  javax.swing.JOptionPane.showMessageDialog(vista, "Por favor rellene todos los campos es obligatorio", "Campos vacios",javax.swing.JOptionPane.WARNING_MESSAGE);
                  return false;
              }
              
              if( correo.trim().isEmpty()){
                  javax.swing.JOptionPane.showMessageDialog(vista, "Por favor Ingrese un Correo valido ","Campos Vacios",javax.swing.JOptionPane.WARNING_MESSAGE);
                  return false;
              }
              
              if(!correo.contains("@") || !correo.contains(".")){
                  javax.swing.JOptionPane.showMessageDialog(vista, "Por favor Ingrese un Correo valido (ejemplo@correo.com)","Correo Invalido",javax.swing.JOptionPane.ERROR_MESSAGE);
                  return false;
              }
              
              return true;
}
      
          
         public void agregarReporte(String nombre, String documento, String hora,String fecha, String correo, JTable tabla ){
        
        String codigo = generarCodigo();
        
        UsuarioReporte Reporte = new UsuarioReporte(correo,codigo,nombre,documento, " "," ");
        
        listaUsuarios.add(Reporte);
        
        modelo =(DefaultTableModel) tabla.getModel();
       
        
        modelo.addRow(new Object[]{
            Reporte.getCodigo(), Reporte.getNombre(),Reporte.getDocumento(),Reporte.getCorreo()
        });
        
        
      
        
       }
           public void eliminarReporte(int filaseleccioanda, JTable tabla){
               
               if(filaseleccioanda >= 0){
                   listaUsuarios.remove(filaseleccioanda);
                   
                   modelo = (DefaultTableModel ) tabla.getModel();
                   modelo.removeRow(filaseleccioanda);
               }
            
        }
           
           public void EditarReporte(int filaseleccionada, String nombre, String documento,String correo, JTable tabla){
               
               if(filaseleccionada >= 0){
                   UsuarioReporte  reporte = (UsuarioReporte) listaUsuarios.get(filaseleccionada);
                   
                   reporte.setNombre(nombre);
                   reporte.setDocumento(documento);
                   reporte.setCorreo(correo);
                   
                   modelo = (DefaultTableModel) tabla.getModel();
                   
                   modelo.setValueAt(nombre, filaseleccionada, 1);
                    modelo.setValueAt(documento, filaseleccionada, 2);
                     modelo.setValueAt(correo, filaseleccionada, 3);
               }
               
           }
    
}
