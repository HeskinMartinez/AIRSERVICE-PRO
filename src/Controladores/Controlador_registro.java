package Controladores;


import Modelos.Usuarios;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Controlador_registro {
    
    private ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
            
        private DefaultTableModel modelo;
        private int  contador = 1;

    public Controlador_registro() {
        
        
    }
   
    public String generarCodigo(){
        String codigo = "AIR-" + String.format("%03d", contador);
        contador ++;
        return codigo;
    }
    
    public void agregarUsuario(String nombre, String documento, String hora, String fecha, JTable tabla ){
        
        String codigo = generarCodigo();
        
        Usuarios u = new Usuarios(codigo,nombre,documento,hora,fecha);
        
        listaUsuarios.add(u);
        
        modelo =(DefaultTableModel) tabla.getModel();
       
        
        modelo.addRow(new Object[]{
            u.getCodigo(), u.getNombre(), u.getDocumento(), u.getHora(), u.getFecha()
        });
        
        
       }
    
    public void eliminarUsuario(int filaseleccionada, JTable tabla){
        if(filaseleccionada >=0 ){
            listaUsuarios.remove(filaseleccionada);
            
            modelo = (DefaultTableModel) tabla.getModel();
            modelo.removeRow(filaseleccionada);
        }
    }
    public void editarUsuario(int filaseleccionada, String nombre, String documento, String hora, String fecha, JTable tabla){
        if(filaseleccionada >= 0){
            Usuarios u = listaUsuarios.get(filaseleccionada);
            u.setNombre(nombre);
            u.setDocumento(documento);
            u.setHora(hora);
            u.setFecha(fecha);
            
            modelo = (DefaultTableModel)tabla.getModel();
            modelo.setValueAt(nombre, filaseleccionada, 1);
            modelo.setValueAt(documento, filaseleccionada, 2);
            modelo.setValueAt(fecha, filaseleccionada, 3);
            modelo.setValueAt(hora, filaseleccionada, 4);
            
        }
    }
    
     public boolean validardatos(String nombre, String documento, String hora, java.util.Date fecha, java.awt.Component vista){
              
              if(nombre.trim().isEmpty() || documento.trim().isEmpty() || fecha == null){
                  javax.swing.JOptionPane.showMessageDialog(vista, "Por favor rellene todos los campos es obligatorio", "Campos vacios",javax.swing.JOptionPane.WARNING_MESSAGE);
                  return false;
              }
              
              if(hora.equals("Seleccione hora.")|| hora.trim().isEmpty()){
                  javax.swing.JOptionPane.showMessageDialog(vista, "Por favor seleccione una hora valida de la lista","Hora no seleccionada",javax.swing.JOptionPane.WARNING_MESSAGE);
              }
              
              return true;
     }
}
    
    
        
    
       

