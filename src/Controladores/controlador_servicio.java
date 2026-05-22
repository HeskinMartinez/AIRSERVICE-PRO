
package Controladores;

import Modelos.Servicios;
import java.util.ArrayList;

public class controlador_servicio {
    
    private ArrayList<Servicios> listaServicios;

    public controlador_servicio() {
        this.listaServicios = new ArrayList<>();
    }

    public boolean validarCamposVacios(String nombreC, String docC, String fecha, String tipoS, String desc) {
        if (nombreC.isEmpty() || docC.isEmpty() || fecha.isEmpty() || desc.isEmpty()) {
            return false;
        }
        if (tipoS.equals("Seleccione una opcion") || tipoS.equals("Selecciona una opcion")) {
            return false; // No seleccionó tipo de servicio en el combo
        }
        return true;
    }

    public boolean registrarServicio(Servicios nuevoServicio) {
        // En servicios se permite repetir documento (un cliente puede pedir varios mantenimientos)
        listaServicios.add(nuevoServicio);
        return true; 
    }

    // R - Read: Buscar servicios de un cliente (devuelve una sublista con todos sus mantenimientos)
    public ArrayList<Servicios> buscarServiciosPorCliente(String documento) {
        ArrayList<Servicios> serviciosEncontrados = new ArrayList<>();
        for (Servicios s : listaServicios) {
            if (s.getDocumentoCliente().equals(documento)) {
                serviciosEncontrados.add(s);
            }
        }
        return serviciosEncontrados; 
    }

    // U - Update: Modificar una orden de servicio existente
    public boolean actualizarServicio(String documentoCliente, Servicios servicioModificado) {
        for (int i = 0; i < listaServicios.size(); i++) {
            if (listaServicios.get(i).getDocumentoCliente().equals(documentoCliente)) {
                listaServicios.set(i, servicioModificado);
                return true; 
            }
        }
        return false; 
    }
    
    // D - Delete: Eliminar una orden de servicio
    public boolean eliminarServicio(String documentoCliente) {
        for (int i = 0; i < listaServicios.size(); i++) {
            if (listaServicios.get(i).getDocumentoCliente().equals(documentoCliente)) {
                listaServicios.remove(i);
                return true; 
            }
        }
        return false; 
    }

    // Getter para la lista completa
    public ArrayList<Servicios> getListaServicios() {
        return listaServicios;
    }

}
