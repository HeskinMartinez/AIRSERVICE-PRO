/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proaula_3;

import Vistas.Login;
/**
 *
 * @author Ing_heskin
 */
public class ProAula_3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Ejecuta y muestra tu interfaz al presionar Play (F6)
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true); // Cambia 'Login' por el nombre de tu ventana
            }
        });
    }
    
}
